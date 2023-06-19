package com.example.ebook_back.daoImpl;
import com.example.ebook_back.dao.UserDao;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.repository.UserAuthRepository;
import com.example.ebook_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAuth findByUserName(String name) {
        return userAuthRepository.findByUserName(name);
    }
    @Override
    public User findUserByMail(String mail) {
        return userRepository.findByEmail(mail);
    }
    @Override
    public void activateUser(int id){
        UserAuth userAuth = userAuthRepository.findByUserId(id);
        userAuth.setBan(false);
        userAuthRepository.save(userAuth);
    }
    @Override
    public boolean logout(int id){
        return true;
    }

    @Override
    public boolean checkSession(int id){
//        UserAuth userAuth = userAuthRepository.findByUserId(id);
//        if(userAuth == null){
//            return false;
//        }
//        return userAuth.isBan();
        return true;
    }
    @Override
    public boolean checkName(String name){
        UserAuth userAuth = userAuthRepository.findByUserName(name);
        return userAuth == null;
    }
    @Override
    public boolean checkMail(String mail){
        User user = userRepository.findByEmail(mail);
        return user == null;
    }

    @Override
    public boolean createUser(User user){
        try {
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    @Override
    public boolean createUserAuth(UserAuth userauth){
        try {
            userAuthRepository.save(userauth);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    @Override
    public boolean banUser(int id,boolean ban){
        UserAuth userAuth = userAuthRepository.findByUserId(id);
        if(userAuth == null){
            return false;
        }
        if(userAuth.getUserMode() == 1){
            return false;
        }
        try {
            userAuth.setBan(ban);
            userAuthRepository.save(userAuth);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    @Override
    public List<UserAuth> getAllUsers(){
        return userAuthRepository.findUserAuthByUserMode(0);
    }
    @Override
    public UserAuth findUserAuthById(int id){
        return userAuthRepository.findByUserId(id);
    }
}
