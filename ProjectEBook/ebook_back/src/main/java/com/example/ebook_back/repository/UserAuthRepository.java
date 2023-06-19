package com.example.ebook_back.repository;
import com.example.ebook_back.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, String>{
    UserAuth findByUserName(String name);
    UserAuth findByUserId(int id);
    List<UserAuth> findUserAuthByUserMode(int mode);
}
