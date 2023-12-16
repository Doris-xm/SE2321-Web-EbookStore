package org.wordCount.repository;

import org.wordCount.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findBooksByName(String keyword) {
        String sql = "SELECT * FROM book WHERE title LIKE ?";
        String searchTerm = "%" + keyword + "%"; // 在搜索关键词两侧添加通配符 "%" 来进行模糊匹配
        List<Book> books = jdbcTemplate.query(sql, new Object[]{searchTerm}, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setPrice(rs.getDouble("price"));
            book.setCover(rs.getString("cover"));
            book.setIntroduce(rs.getString("introduce"));
            book.setStocks(rs.getInt("stocks"));
            book.setSales(rs.getInt("sales"));
            return book;
        });

        return books; // 返回包含符合条件的书籍列表
    }
    public List<Book> findALLBooks() {
        String sql = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setPrice(rs.getDouble("price"));
            book.setCover(rs.getString("cover"));
            book.setIntroduce(rs.getString("introduce"));
            book.setStocks(rs.getInt("stocks"));
            book.setSales(rs.getInt("sales"));
            return book;
        });

        return books; // 返回包含符合条件的书籍列表
    }

}

