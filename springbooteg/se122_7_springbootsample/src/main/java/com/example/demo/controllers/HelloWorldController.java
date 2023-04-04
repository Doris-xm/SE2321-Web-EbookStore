package com.example.demo.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @CrossOrigin
    @RequestMapping("/")
    public String home() {
        final Logger log = LoggerFactory.getLogger(HelloWorldController.class);
        List<Book> result = new ArrayList<Book>();

        log.info("Querying Books");
        result =jdbcTemplate.query(
                "SELECT * FROM book",
                (rs, rowNum) -> new Book(rs.getLong("id"), //where author = "J. R. R. Tolkien"
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("language"),
                        rs.getString("published"),
                        rs.getString("sales"))
        );//.forEach(book -> {log.info(book.toString()); result.add(book);});

        Iterator<Book> it = result.iterator();

        ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
        while (it.hasNext()) {
            Book book = (Book) it.next();
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(book.getTitle());
            arrayList.add(book.getAuthor());
            arrayList.add(book.getLanguage());
            arrayList.add(book.getPublished());
            arrayList.add(book.getSales());
            booksJson.add((JSONArray) JSONArray.toJSON(arrayList));
        }
        String  booksString = JSON.toJSONString(booksJson, SerializerFeature.BrowserCompatible);

        System.out.println(booksString);

        return booksString;
    }
}
