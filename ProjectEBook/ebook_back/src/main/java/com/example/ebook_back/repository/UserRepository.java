package com.example.ebook_back.repository;
import com.example.ebook_back.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<UserAuth, String>{
    UserAuth findByUserName(String name);
    UserAuth findByUserId(int id);
}
