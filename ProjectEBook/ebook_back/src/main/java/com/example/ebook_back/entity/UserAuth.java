package com.example.ebook_back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
@Entity
@Table(name = "user_auth", schema = "ebook")
public class UserAuth {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "user_mode")
    private int userMode;

    @Basic
    @Column(name = "is_ban")
    private boolean isBan;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;


//    UserAuth(String password, String userName, String email, Integer years,String introduce,String avatar ) {
//        this.password = password;
//        this.userName = userName;
//        this.user.setEmail(email);
//        this.user.setYears(years);
//        this.user.setIntroduce(introduce);
//        this.user.setAvatar(avatar);
//    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAuth userAuth = (UserAuth) o;
//        return userId == userAuth.userId && Objects.equals(password, userAuth.password) && Objects.equals(userName, userAuth.userName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, password, userName);
//    }
}
