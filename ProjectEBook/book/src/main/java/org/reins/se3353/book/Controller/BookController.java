package org.reins.se3353.book.Controller;

import net.sf.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.reins.se3353.book.entity.Book;
import org.reins.se3353.book.service.BookService;
import org.reins.se3353.book.constant.Msg;
import org.reins.se3353.book.constant.MsgCode;
import org.reins.se3353.book.constant.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @RequestMapping("/searchAuth")
    public Msg getBookById(@RequestBody Map<String,Object> json) {
        int id = Integer.parseInt(json.get("book_id").toString());
        Book book = bookService.findBookById(id);
        if(book == null)
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ERROR_MSG, null);
        JSONObject jsonObject = JSONObject.fromObject(book);
        if(book.getStocks() < 0)
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.BOOK_DELETED_ERR_MSG,jsonObject);

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
}
