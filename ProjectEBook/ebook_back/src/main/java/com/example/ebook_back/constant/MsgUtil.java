package com.example.ebook_back.constant;

import net.sf.json.JSONObject;


public class MsgUtil {

    public static final int SUCCESS = 1;
    public static final int ERROR = -1;
    public static final int LOGIN_USER_ERROR = -100;
    public static final int NOT_LOGGED_IN_ERROR = -101;

    public static final String SUCCESS_MSG = "成功！";
    public static final String ERROR_MSG = "错误！";
    /* 登录相关*/
    public static final String LOGIN_SUCCESS_MSG = "登录成功！";
    public static final String LOGOUT_SUCCESS_MSG = "登出成功！";
    public static final String RESIGN_SUCCESS_MSG = "注册成功！";
    public static final String RESIGN_FAIL_MSG = "注册失败！";
    public static final String LOGOUT_ERR_MSG = "登出异常，用户未登录！";
    public static final String LOGIN_PASSWORD_ERROR_MSG = "密码错误，请重新输入！";
    public static final String LOGIN_USER_NOT_EXIST_MSG = "用户不存在，请先注册！";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "登录失效，请重新登录！";
    public static final String LOGIN_BAN_ERROR_MSG = "登录失败，该账户已被管理员冻结！";

    /* 管理相关 */
    public static final String BAN_USER_ERR_MSG = "禁用用户失败";
    public static final String BAN_USER_SUCCESS_MSG = "禁用用户成功";
    public static final String UNBAN_USER_SUCCESS_MSG = "解禁用户成功";
    public static final String UNBAN_USER_ERR_MSG = "解禁用户失败";

    /* 修改相关*/
    public static final String UPDATE_SUCCESS_MSG = "修改成功！";
    public static final String UPDATE_ERR_MSG = "修改失败！";
    public static final String DELETE_SUCCESS_MSG = "删除成功！";
    public static final String ADD_SUCCESS_MSG = "增加成功！";
    public static final String MODIFY_SUCCESS_MSG = "修改成功！";
    public static final String BOOK_DELETED_ERR_MSG = "书籍已下架！";

    /* 订单相关*/
    public static final String ORDER_SUCCESS_MSG = "下单成功！";
    public static final String ORDER_ERR_MSG = "下单失败！";
    public static final String CHECK_SESSION_SUCCESS_MSG = "登录状态有效！";
    public static final String CHECK_SESSION_ERR_MSG = "未登录！";
    public static final String CHECK_NAME_ERR_MSG = "用户名已被注册";
    public static final String CHECK_MAIL_ERR_MSG = "邮箱已被注册";

    public static Msg makeMsg(MsgCode code, JSONObject data){
        return new Msg(code, data);
    }

    public static Msg makeMsg(MsgCode code, String msg, JSONObject data){
        return new Msg(code, msg, data);
    }

    public static Msg makeMsg(MsgCode code){
        return new Msg(code);
    }

    public static Msg makeMsg(MsgCode code, String msg){
        return new Msg(code, msg);
    }

    public static Msg makeMsg(int status, String msg, JSONObject data){
        return new Msg(status, msg, data);
    }

    public static Msg makeMsg(int status, String msg){
        return new Msg(status, msg);
    }
}
