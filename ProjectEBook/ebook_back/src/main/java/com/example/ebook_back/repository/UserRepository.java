package com.example.ebook_back.repository;
import com.example.ebook_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findByEmail(String mail);
}
