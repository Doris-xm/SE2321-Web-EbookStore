package org.reins.se3353.book.Controller;


import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.reins.se3353.book.entity.Book;
import org.reins.se3353.book.service.BookService;
import org.reins.se3353.book.constant.Msg;
import org.reins.se3353.book.constant.MsgCode;
import org.reins.se3353.book.constant.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @RequestMapping("/searchAuth")
    public Msg getBookById(@RequestBody Map<String,Object> json) {
        String bookName = (String) json.get("bookName");
        List<Book> books = bookService.findBooksByName(bookName);
        if(books.size() == 0)
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ERROR_MSG, null);
        JSONObject jsonObject = new JSONObject();
        for (Book book : books) {
            jsonObject.put(book.getTitle(), book.getAuthor());
        }


        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
}
