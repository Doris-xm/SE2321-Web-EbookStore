package com.example.ebook_back.controller;

import com.example.ebook_back.constant.Constant;
import com.example.ebook_back.constant.Msg;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.service.TimeService;
import com.example.ebook_back.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //允许跨域
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    WebApplicationContext applicationContext;


    @RequestMapping("/user")
    public UserAuth getUserByName(@RequestParam("name") String name) {
        return  userService.findUserByName(name);
    }
    @RequestMapping("/resign")
    public Msg resign(@RequestBody Map<String,Object> data) {
        String name = data.get(Constant.USERNAME).toString();
        String password = data.get(Constant.PASSWORD).toString();
        String mail = data.get(Constant.EMAIL).toString();
        if (userService.resign(name,password,mail)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.RESIGN_SUCCESS_MSG);
        } else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.RESIGN_FAIL_MSG);
        }
    }

    @PostMapping("/login")
    public Msg checkLogin(@RequestBody Map<String,Object> data) {
        TimeService timeService = applicationContext.getBean(TimeService.class);
        System.out.println("login");
        System.out.println(this);
        System.out.println(timeService);
        String name = data.get(Constant.USERNAME).toString();
        String password = data.get(Constant.PASSWORD).toString();

        // 验证逻辑，如果验证成功则返回 UserAuth.User 对象，否则返回错误信息
        UserAuth userAuth = userService.findUserByName(name);
        if (userAuth == null) {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_USER_NOT_EXIST_MSG);
        }
        if ( !Objects.equals(userAuth.getPassword(), password) ) {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_PASSWORD_ERROR_MSG);
        }
        if ( (userAuth.getUserMode() == 0) && userAuth.isBan()) {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_BAN_ERROR_MSG);
        }
        User user = userAuth.getUser();
        JSONObject jsonObject = JSONObject.fromObject(user);
        jsonObject.put(Constant.USER_MODE, userAuth.getUserMode());
        timeService.TimeCount(true);

        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, jsonObject);
    }

    @PostMapping("/logout")
    public Msg Logout(@RequestBody Map<String,Object> data) {
        TimeService timeService = applicationContext.getBean(TimeService.class);
        System.out.println("logout");
        System.out.println(this);
        System.out.println(timeService);
        int userId = Integer.parseInt(data.get(Constant.USER_ID).toString());
        if( userService.logout(userId)) {
            JSONObject obj=new JSONObject();
            obj.put("duration", timeService.TimeCount(false));
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG, obj);
        } else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
        }
    }

    @PostMapping("/checkSession")
    public Msg checkSession(@RequestBody Map<String,Object> data) {
        int userId = Integer.parseInt(data.get(Constant.USER_ID).toString());
        if( userService.checkSession(userId)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.CHECK_SESSION_SUCCESS_MSG);
        } else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.CHECK_SESSION_ERR_MSG);
        }
    }

    @PostMapping("/checkName")
    public Msg checkNewName(@RequestBody Map<String,Object> data) {
        String userName = data.get(Constant.USERNAME).toString();
        if( userService.checkName(userName)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG);
        } else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.CHECK_NAME_ERR_MSG);
        }
    }
    @PostMapping("/checkEmail")
    public Msg checkNewEmail(@RequestBody Map<String,Object> data) {
        String mail = data.get(Constant.EMAIL).toString();
        if( userService.checkMail(mail)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG);
        } else {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.CHECK_MAIL_ERR_MSG);
        }
    }
    @PostMapping("/banUser")
    public Msg banUser(@RequestBody Map<String,Object> data) {
        int userId = Integer.parseInt(data.get(Constant.USER_ID).toString());
        boolean isBan = data.get(Constant.USER_BAN).toString().equals("true");
        if( userService.banUser(userId, isBan)) {
            if(isBan)
                return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.BAN_USER_SUCCESS_MSG);
            else
                return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.UNBAN_USER_SUCCESS_MSG);
        } else {
            if(isBan)
                return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.BAN_USER_ERR_MSG);
            else
                return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.UNBAN_USER_ERR_MSG);
        }
    }
    @PostMapping("/users")
    public Msg getAllUsers() {
        List<UserAuth> users = userService.getAllUsers();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.USERS, users);
        if(users == null)
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ERROR_MSG, null);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }
    @PostMapping("/userById")
    public Msg getUserById(@RequestBody Map<String,Object> data) {
        int userId = Integer.parseInt(data.get(Constant.USER_ID).toString());
        UserAuth userAuth = userService.findUserAuthById(userId);
        if(userAuth == null)
            return MsgUtil.makeMsg(MsgCode.ERROR, "用户不存在", null);
        if(userAuth.isBan())
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_BAN_ERROR_MSG, null);
        User user = userAuth.getUser();
        JSONObject jsonObject = JSONObject.fromObject(user);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);

    }
}
