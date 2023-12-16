package org.wordCount;

import net.sf.json.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.wordCount.entity.Book;
import org.wordCount.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WordCountController {

    @Autowired
    private BookService bookService;

    @PostMapping("/construct_data")
    public JSONObject Construct() throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<Book> books = bookService.findALLBooks();
        // 根据图书类型分组
        Map<String, List<String>> introByPriceRange = new HashMap<>();
        for (Book book : books) {
            Double price = book.getPrice();
            String intro = book.getIntroduce();
            String priceRange;
            if (price < 10) {
                priceRange = "Cheap";
            } else if (price >= 10 && price <= 30) {
                priceRange = "Medium";
            } else {
                priceRange = "Expensive";
            }

            introByPriceRange.computeIfAbsent(priceRange, k -> new ArrayList<>()).add(intro);
        }

        // 写入不同类型的简介到对应文件中
        for (Map.Entry<String, List<String>> entry : introByPriceRange.entrySet()) {
            String priceRange = entry.getKey();
            List<String> intros = entry.getValue();

            // 构建文件名
            String fileName = priceRange + ".txt";

            // 写入文件
            try (PrintWriter writer = new PrintWriter(fileName)) {
                for (String intro : intros) {
                    writer.println(intro);
                }
            } catch (IOException e) {
                // 处理文件写入异常
                e.printStackTrace();
            }
        }

        // 返回操作结果
        jsonObject.put("status", "Files created successfully");
        return jsonObject;


    }

    @PostMapping("/wordCount")
    public JSONObject WordCountMain() throws Exception {
        String[] args = new String[2];
        args[0] = "input";
        args[1] = "output";
        Configuration conf = new Configuration();
        GenericOptionsParser optionParser = new GenericOptionsParser(conf, args);
        String[] remainingArgs = optionParser.getRemainingArgs();
        if ((remainingArgs.length != 2) && (remainingArgs.length != 4)) {
            System.err.println("Usage: wordcount <in> <out> [-skip skipPatternFile]");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCount.TokenizerMapper.class);
        job.setCombinerClass(WordCount.IntSumReducer.class);
        job.setReducerClass(WordCount.IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        List<String> otherArgs = new ArrayList<String>();
        for (int i=0; i < remainingArgs.length; ++i) {
            if ("-skip".equals(remainingArgs[i])) {
                job.addCacheFile(new Path(remainingArgs[++i]).toUri());
                job.getConfiguration().setBoolean("wordcount.skip.patterns", true);
            } else {
                otherArgs.add(remainingArgs[i]);
            }
        }
        FileInputFormat.addInputPath(job, new Path(otherArgs.get(0)));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs.get(1)));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", job.waitForCompletion(true) ? 0 : 1);
        return jsonObject;

//        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
