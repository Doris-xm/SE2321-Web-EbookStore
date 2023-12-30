package org.wordCount.Controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wordCount.entity.Book;
import org.wordCount.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/related_type")
    public JSONObject related_type(@RequestBody Map<String,Object> json) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String type = json.get("label").toString();
        List<Book> books = bookService.findByLabel(type);
        // 返回操作结果
        jsonObject.put("books", books);
        return jsonObject;
    }
}
