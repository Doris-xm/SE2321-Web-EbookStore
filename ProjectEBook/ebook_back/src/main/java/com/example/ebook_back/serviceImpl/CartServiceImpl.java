//package com.example.ebook_back.service;
//import com.example.ebook_back.entity.Book;
//import java.util.List;
//
//
//public interface BookService {
//
//    Book findBookById(Integer id);
//    List<Book> getBooks();
//}
package com.example.ebook_back.serviceImpl;

import com.example.ebook_back.dao.CartDao;
import com.example.ebook_back.entity.Cart;
import com.example.ebook_back.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

//    public Cart findByBookId(int bookID){
//        return cartDao.findByBookID(bookID);
//    }
    @Override
    public List<Cart> getCart(int userID) {
        return cartDao.findAllByUserID(userID);
    }

    @Override
    public boolean addBook(int userID, int bookID) {
        try {
            Cart exist = cartDao.findByBookIDAndUserID(bookID,userID);
            if(exist == null) {
                cartDao.saveCartItem(new Cart(userID, bookID, 1));
            }
            else {
                exist.setQuantity(exist.getQuantity() + 1);
                cartDao.saveCartItem(exist);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean changeQuantity(int userID, int bookID,int quantity) {
        try {
            Cart exist = cartDao.findByBookIDAndUserID(bookID,userID);
            if(exist == null) {
                cartDao.saveCartItem(new Cart(userID, bookID, quantity));
            }
            else {
                if(quantity == 0)
                    cartDao.deleteCartItem(exist);
                else {
                    exist.setQuantity(quantity);
                    cartDao.saveCartItem(exist);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean clear(int userID) {
        try {
            List<Cart> cart = cartDao.findAllByUserID(userID);
            cartDao.deleteAll(cart);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
