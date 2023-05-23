package com.example.ebook_back.daoImpl;
import com.example.ebook_back.dao.CartDao;
import com.example.ebook_back.entity.Cart;
import com.example.ebook_back.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAllByUserID(int userID){
        return cartRepository.findAllByUserID(userID);
    }

    @Override
    public Cart findByBookIDAndUserID(int bookID,int userID){
        return cartRepository.findByBookIDAndUserID(bookID,userID);
    }

    @Override
    public void deleteAll(List<Cart> cart){
        cartRepository.deleteAll(cart);
    }

    @Override
    public void saveCartItem(Cart exist){
        cartRepository.save(exist);
    }
    @Override
    public void deleteCartItem(Cart exist){
        cartRepository.delete(exist);
    }
}
