package com.company.project.web;


import com.company.project.core.util.UserContext;
import com.company.project.entity.User;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@RestController
public class UserController {
    @PostMapping(value = "/userInfo")
    public Map<String,Object> userInfo(){
        Map<String,Object> resMap= Maps.newHashMap();
        resMap.put("name",UserContext.getSession().getName());
        resMap.put("permissions",new String[]{});
        return resMap;
    }
}
