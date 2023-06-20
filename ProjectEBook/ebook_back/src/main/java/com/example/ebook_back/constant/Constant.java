package com.example.ebook_back.constant;

public class Constant {
    /* 用于订单状态*/
    public static final Integer STATE_NOT_PAID = 0;
    public static final Integer STATE_PAID =1;
    public static final Integer STATE_SENT = 2;
    public static final Integer STATE_REACHED = 3;
    public static final Integer STATE_SIGNED = 4;
    public static final Integer STATE_FINISHED = 5; //commented

    /**/
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String USER_MODE = "userMode";
    public static final String USER_BAN = "isBan";
    public static final String AVATAR = "avatar";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_STATE = "state";
    public static final String PHONE = "phone";
    public static final String EMAIL = "mail";
    public static final String COMMENT = "comment";
    public static final String COMMENT_DETAIL = "commentDetail";
    public static final String COMMENT_PHOTO = "commentPhoto";
    public static final String POST_TIME = "postTime";
    public static final String USERS = "users";

    /* Book 相关*/
    public static final String BOOK_ID = "id";
    public static final String BOOK_NAME = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_ISBN = "isbn";
    public static final String BOOK_PRICE = "price";
    public static final String BOOK_STOCK = "stocks";
    public static final String BOOK_COVER = "cover";

}
