//package com.example.ebook_back.dao;
//
//import com.example.ebook_back.entity.Book;
//
//import java.util.List;
//
//public interface BookDao {
//    Book findOne(Integer id);
//    List<Book> getBooks();
//}

package com.example.ebook_back.daoImpl;
import com.alibaba.fastjson.JSON;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.controller.BookController;
import com.example.ebook_back.dao.BookDao;
import com.example.ebook_back.entity.Book;
import com.example.ebook_back.entity.BookDetail;
import com.example.ebook_back.repository.BookDetailRepository;
import com.example.ebook_back.repository.BookRepository;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDetailRepository bookDetailRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Override
    public Book findBookById(int id){

        Book book = bookRepository.findBookById(id);
        List<BookDetail> bookDetailList = bookDetailRepository.findBookDetailById(id);
        if(book == null)
            return null;
        if(bookDetailList.size() > 0)
            book.setBookDetail(bookDetailList.get(0));
        else
            book.setBookDetail(null);

        return book;
    }

    @Override
    public List<Book> findAll(){
//        try {
//            Object books_redis = redisTemplate.opsForValue().get("all_books");
//            if (books_redis != null) {
////                logger.info("从缓存中获取all_books ");
//                return JSON.parseArray((String) books_redis,Book.class);
//            }
//        }
//        catch (Exception e) {
//            logger.error("redis连接超时");
//        }
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            List<BookDetail> bookDetailList = bookDetailRepository.findBookDetailById(book.getId());
            if(bookDetailList.size() > 0)
                book.setBookDetail(bookDetailList.get(0));
            else
                book.setBookDetail(null);
        }
//        logger.info("第一次访问，从数据库中获取all_books");
//        try {
//            redisTemplate.opsForValue().set("all_books", JSON.toJSONString(books));
//        }
//        catch (Exception e) {
//            logger.error("redis连接超时");
//        }
        books.removeIf(book -> book.getStocks() < 0);
        return books;
    }

    @Override
    public void save(Book book){
        Book book_new = bookRepository.save(book);
        BookDetail bookDetail = bookDetailRepository.save(book.getBookDetail());
        book_new.setBookDetail(bookDetail);

    }

}
