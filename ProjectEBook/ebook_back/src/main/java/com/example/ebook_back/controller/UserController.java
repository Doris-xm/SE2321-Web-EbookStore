package com.example.ebook_back.controller;

import com.example.ebook_back.constant.Constant;
import com.example.ebook_back.constant.Msg;
import com.example.ebook_back.constant.MsgCode;
import com.example.ebook_back.constant.MsgUtil;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //允许跨域
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/user")
    public UserAuth getUserById(@RequestParam("name") String name) {
        return  userService.findUserByName(name);
    }

    @PostMapping("/login")
    public Msg checkLogin(@RequestBody Map<String,Object> data) {
        String name = data.get(Constant.USERNAME).toString();
        String password = data.get(Constant.PASSWORD).toString();

        // 验证逻辑，如果验证成功则返回 UserAuth.User 对象，否则返回错误信息
        UserAuth userAuth = userService.findUserByName(name);
        if (userAuth == null) {
//            JSONObject jsonObjectErr1 = new JSONObject();
//            jsonObjectErr1.put("err", "用户 " + name + " 不存在");
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_USER_NOT_EXIST_MSG);
        }
        if ( !Objects.equals(userAuth.getPassword(), password) ) {
//            JSONObject jsonObjectErr2 = new JSONObject();
//            jsonObjectErr2.put("err", "密码错误");
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_PASSWORD_ERROR_MSG);
        }
        User user = userAuth.getUser();
        userService.activateUser(user.getId());
        JSONObject jsonObject = JSONObject.fromObject(user);
        jsonObject.put("userMode", userAuth.getUserMode());
        
//        JSONObject jsonObject = JSONObject.fromObject(userAuth.getUser());
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, jsonObject);
    }

    @PostMapping("/logout")
    public Msg getUserById(@RequestBody Map<String,Object> data) {
        int userId = Integer.parseInt(data.get(Constant.USER_ID).toString());
        if( userService.logout(userId)) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
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

}
