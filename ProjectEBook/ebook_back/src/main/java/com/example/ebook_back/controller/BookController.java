package com.example.ebook_back.controller;

import com.example.ebook_back.constant.Constant;
import com.example.ebook_back.constant.Msg;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.entity.BookDetail;
import com.example.ebook_back.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookController {
    @Resource
    private BookService bookService;
//    @RequestMapping("/books")
//    public List<Map<String, Object>> getList(){
//        return bookRepository.queryForList("Select * from ebook.book");
//    }
    @RequestMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    @RequestMapping("/book")
    public Msg getBookById(@RequestBody Map<String,Object> json) {
        int id = Integer.parseInt(json.get(Constant.BOOK_ID).toString());
        Book book = bookService.findBookById(id);
        if(book == null)
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ERROR_MSG, null);
        JSONObject jsonObject = JSONObject.fromObject(book);
        if(book.getStocks() < 0)
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.BOOK_DELETED_ERR_MSG,jsonObject);

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
//    @RequestMapping("/")
//    public String home() {
//        String booksString = JSON.toJSONString(getBooks(), SerializerFeature.BrowserCompatible);
//        return booksString;
//    }
    @PostMapping("/deleteBooks")
    /*
    * @brief 删除书籍:将书籍的库存设置为-1
    * */
    public Msg deleteBooks(@RequestBody Map<String,Object> json) {
        List<Integer> bookIds= (ArrayList<Integer>) json.get("bookIds");
        for (int bookId : bookIds) {
            Book book = bookService.findBookById(bookId);
            if(book == null)
                return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.BOOK_DELETED_ERR_MSG);
            book.setStocks(-1);
            bookService.addBook(book);
        }

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_MSG);
//        try {
//            String bookIdsString = (String) json.get("bookIds");
//            List<Integer> bookIds = Arrays.stream(bookIdsString.split(","))
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//            bookService.deleteBooks(bookIds);
//        } catch (Exception e) {
//            return MsgUtil.makeMsg(MsgCode.ERROR, e.toString());
//        }
//        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_MSG);
    }
    @PostMapping("/addBook")
    public Msg AddBook(@RequestBody Map<String,Object> json) {
        try {
            Book book = new Book();
            BookDetail bookDetail = new BookDetail();
            bookDetail.setTitle(json.get(Constant.BOOK_NAME).toString());
            bookDetail.setAuthor(json.get(Constant.BOOK_AUTHOR).toString());
            book.setStocks(Integer.valueOf(json.get(Constant.BOOK_STOCK).toString()));
            bookDetail.setPrice(Double.valueOf(json.get(Constant.BOOK_PRICE).toString()));
            bookDetail.setIsbn(json.get(Constant.BOOK_ISBN).toString());
            if(json.get(Constant.BOOK_COVER) != null)
                bookDetail.setCover(json.get(Constant.BOOK_COVER).toString());
            if(json.get(Constant.BOOK_INTRO) != null)
                bookDetail.setIntroduce(json.get(Constant.BOOK_INTRO).toString());
            book.setBookDetail(bookDetail);
            bookService.addBook(book);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgCode.ERROR, e.toString());
        }
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADD_SUCCESS_MSG);
    }
    @PostMapping("/modifyBook")
    public Msg ModifyBook(@RequestBody Map<String,Object> json) {
        try {
            System.out.println(json);
            int id = Integer.valueOf(json.get(Constant.BOOK_ID).toString());
            Book book = bookService.findBookById(id);
            BookDetail bookDetail = book.getBookDetail();
            if(book == null)
                return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.BOOK_DELETED_ERR_MSG);

            if(json.get(Constant.BOOK_NAME) != null)
                bookDetail.setTitle(json.get(Constant.BOOK_NAME).toString());
           if(json.get(Constant.BOOK_AUTHOR) != null)
               bookDetail.setAuthor(json.get(Constant.BOOK_AUTHOR).toString());
            if(json.get(Constant.BOOK_STOCK) != null)
                book.setStocks(Integer.valueOf(json.get(Constant.BOOK_STOCK).toString()));
            if(json.get(Constant.BOOK_PRICE) != null)
                bookDetail.setPrice(Double.valueOf(json.get(Constant.BOOK_PRICE).toString()));
            if(json.get(Constant.BOOK_COVER) != null)
                bookDetail.setCover(json.get(Constant.BOOK_COVER).toString());
            if(json.get(Constant.BOOK_ISBN) != null)
                bookDetail.setIsbn(json.get(Constant.BOOK_ISBN).toString());
            book.setBookDetail(bookDetail);
            bookService.addBook(book);

            JSONObject jsonObject = JSONObject.fromObject(book);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.MODIFY_SUCCESS_MSG, jsonObject);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgCode.ERROR, e.toString());
        }
    }
}
