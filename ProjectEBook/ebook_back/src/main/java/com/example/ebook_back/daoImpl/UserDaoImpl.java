package com.example.ebook_back.daoImpl;
import com.example.ebook_back.dao.UserDao;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAuth findByUserName(String name) {
        return userRepository.findByUserName(name);
    }
}
