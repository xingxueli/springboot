package com.company.project.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.project.core.util.JwtUtil;
import com.company.project.entity.User;
import com.company.project.service.UserService;
import io.jsonwebtoken.lang.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @PostMapping(value = "/login")
    public Map<String,String> login(@RequestBody User user, HttpServletResponse response){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername())
                .eq("password",user.getPassword());
        User dbUser = userService.getOne(queryWrapper);
        if (dbUser==null){
            throw new RuntimeException("用户名或密码错误");
        }
        return Maps.of("accessToken", JwtUtil.createAppToken(String.valueOf(dbUser.getId()),dbUser.getName())).build();
    }
}
