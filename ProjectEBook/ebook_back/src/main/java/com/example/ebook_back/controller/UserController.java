package com.example.ebook_back.controller;

import com.example.ebook_back.entity.MyOrder;
import com.example.ebook_back.entity.OrderCommit;
import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public ResponseEntity<JSONObject> checkLogin(@RequestBody String[] requestBody) {
        String name = requestBody[0];
        String password = requestBody[1];

        // 验证逻辑，如果验证成功则返回 UserAuth.User 对象，否则返回错误信息
        UserAuth userAuth = userService.findUserByName(name);
        if (userAuth == null) {
            JSONObject jsonObjectErr1 = new JSONObject();
            jsonObjectErr1.put("err", "用户 " + name + " 不存在");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonObjectErr1);
        }
        if ( !Objects.equals(userAuth.getPassword(), password) ) {
            JSONObject jsonObjectErr2 = new JSONObject();
            jsonObjectErr2.put("err", "密码错误");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonObjectErr2);
        }
        JSONObject jsonObject = JSONObject.fromObject(userAuth.getUser());
        return ResponseEntity.ok().body(jsonObject);
    }
}
