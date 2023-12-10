package org.reins.se3353.book.Controller;


import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.reins.se3353.book.entity.Book;
import org.reins.se3353.book.service.BookService;
import org.reins.se3353.book.constant.Msg;
import org.reins.se3353.book.constant.MsgCode;
import org.reins.se3353.book.constant.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;


//@Slf4j
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @QueryMapping
    public List<Book> getBookByName(@Argument String title) {
        List<Book> books = bookService.findBooksByName(title);
        return books;
    }
}
