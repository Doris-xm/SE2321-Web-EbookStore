package org.wordCount.Controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wordCount.WordCount.WordCount;
import org.wordCount.entity.Book;
import org.wordCount.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
public class WordCountController {

    @Autowired
    private BookService bookService;

    @PostMapping("/construct_data")
    public JSONObject Construct() throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<Book> books = bookService.findALLBooks();
        // 根据图书类型分组
        List<String> type_list = Arrays.asList("Literature", "Fiction", "Education", "History");
        Map<String, List<String>> introByType = new HashMap<>();
        for (Book book : books) {
            List<String> types = book.getType();
            String intro = book.getIntroduce();
            for (String type : types) {
                if (!type_list.contains(type))
                    continue;
                introByType.computeIfAbsent(type, k -> new ArrayList<>()).add(intro);
                break;
            }
        }

        // 写入不同类型的简介到对应文件中
        for (Map.Entry<String, List<String>> entry : introByType.entrySet()) {
            String type = entry.getKey();
            List<String> intros = entry.getValue();

            // 构建文件名
            String fileName = "input/"+ type + ".txt";

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
        int numMaps = job.getConfiguration().getInt("mapreduce.job.maps", -1);
        int numReduces = job.getConfiguration().getInt("mapreduce.job.reduces", -1);
        jsonObject.put("numMaps", numMaps);
        jsonObject.put("numReduces", numReduces);
        return jsonObject;

//        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
