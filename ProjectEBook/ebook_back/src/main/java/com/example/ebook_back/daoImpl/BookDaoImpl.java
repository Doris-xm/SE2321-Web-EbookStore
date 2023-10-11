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
import com.example.ebook_back.repository.BookRepository;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Override
    public Book findBookById(int id){
        Object book_redis = redisTemplate.opsForValue().get("book"+id);
        if (book_redis != null) {
            logger.info("从缓存中获取book: " + book_redis);
            return JSON.parseObject((String) book_redis,Book.class);
        }
        Book book = bookRepository.findBookById(id);
        if(book == null)
            return null;
        logger.info("第一次访问，从数据库中获取book: " + book.getId() + book);
        redisTemplate.opsForValue().set("book"+book.getId(), JSON.toJSONString(book));
        return book;
    }

    @Override
    public List<Book> findAll(){
        Object books_redis = redisTemplate.opsForValue().get("all_books");
        if (books_redis != null) {
            logger.info("从缓存中获取all_books ");
            return JSON.parseArray((String) books_redis,Book.class);
        }
        List<Book> books = bookRepository.findAll();
        logger.info("第一次访问，从数据库中获取all_books");
        redisTemplate.opsForValue().set("all_books", JSON.toJSONString(books));
        books.removeIf(book -> book.getStocks() < 0);
        return books;
    }

    @Override
    public void save(Book book){
        redisTemplate.delete("all_books");
        logger.info("修改了book,删除all_books缓存");
        if (redisTemplate.opsForValue().get("book"+book.getId()) != null) {
            redisTemplate.delete("book"+book.getId());
            logger.info("修改了book,删除book"+book.getId()+"缓存");
        }
        Book book_new = bookRepository.save(book);
        if (book_new!=null) {
            logger.info("更新book"+book.getId()+"缓存");
            redisTemplate.opsForValue().set("book"+book.getId(), JSON.toJSONString(book_new));
        }
    }

}
