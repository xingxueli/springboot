package com.company.project.web;


import com.company.project.core.util.JwtUtil;
import com.company.project.core.util.UserContext;
import com.company.project.dao.RoleMapper;
import com.company.project.dao.UserMapper;
import com.company.project.dao.UserRoleMapper;
import com.company.project.entity.Role;
import com.company.project.entity.User;
import com.company.project.entity.vo.UserInfoRequest;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    @PostMapping(value = "/userInfo")
    public Map<String,Object> userInfo(@RequestBody UserInfoRequest userInfoRequest){
        Preconditions.checkNotNull(userInfoRequest.getAccessToken());
        final UserContext.UserSession userSession = JwtUtil.getUser(userInfoRequest.getAccessToken());
        final User user = userMapper.selectById(userSession.getUserId());
        final List<String> roleCodes = roleMapper.getRoleCodes(user.getId());
        Map<String,Object> resMap= Maps.newHashMap();
        resMap.put("name",user.getName());
        resMap.put("avatar",user.getAvatar());
        resMap.put("permissions",roleCodes);

        return resMap;
    }
}
