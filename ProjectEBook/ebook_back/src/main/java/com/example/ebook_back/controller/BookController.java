package com.example.ebook_back.controller;

import com.example.ebook_back.constant.Constant;
import com.example.ebook_back.constant.Msg;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public Book getBookById(@RequestParam("id") int id) {
        return  bookService.findBookById(id);
    }
//    @RequestMapping("/")
//    public String home() {
//        String booksString = JSON.toJSONString(getBooks(), SerializerFeature.BrowserCompatible);
//        return booksString;
//    }
    @PostMapping("/deleteBooks")
    public Msg getUserById(@RequestBody Map<String,Object> json) {
        try {
            List<Integer> bookIds = (List<Integer>) json.get("bookIds");
            bookService.deleteBooks(bookIds);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgCode.ERROR, e.toString());
        }
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.DELETE_SUCCESS_MSG);
    }
    @PostMapping("/addBook")
    public Msg AddBook(@RequestBody Map<String,Object> json) {
        try {
            Book book = new Book();
            book.setTitle(json.get(Constant.BOOK_NAME).toString());
            book.setAuthor(json.get(Constant.BOOK_AUTHOR).toString());
            book.setStocks(Integer.valueOf(json.get(Constant.BOOK_STOCK).toString()));
            book.setPrice(Double.valueOf(json.get(Constant.BOOK_PRICE).toString()));
            book.setCover(json.get(Constant.BOOK_COVER).toString());
            book.setIsbn(json.get(Constant.BOOK_ISBN).toString());
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
            Book book = new Book();
            int id = Integer.valueOf(json.get(Constant.BOOK_ID).toString());
            book = bookService.findBookById(id);
            if(book == null)
                return MsgUtil.makeMsg(MsgCode.ERROR, "书籍不存在");

            String title = json.get(Constant.BOOK_NAME).toString();
            if(title != null)
                book.setTitle(title);
            String author = json.get(Constant.BOOK_AUTHOR).toString();
            if(author != null)
                book.setAuthor(author);
            int stocks = Integer.valueOf(json.get(Constant.BOOK_STOCK).toString());
            if(stocks != -1)
                book.setStocks(stocks);
            double price = Double.valueOf(json.get(Constant.BOOK_PRICE).toString());
            if(price != -1)
                book.setPrice(price);
            String cover = json.get(Constant.BOOK_COVER).toString();
            if(cover != null)
                book.setCover(cover);
            String isbn = json.get(Constant.BOOK_ISBN).toString();
            if(isbn != null)
                book.setIsbn(isbn);
            bookService.addBook(book);
            JSONObject jsonObject = JSONObject.fromObject(book);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.MODIFY_SUCCESS_MSG, jsonObject);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgCode.ERROR, e.toString());
        }
    }
}
