package com.company.project.web;


import com.company.project.entity.vo.MenuVo;
import com.company.project.entity.vo.RoutesVo;
import com.company.project.service.RoutesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author pzblog
 * @since 2023-07-27
 */
@RestController
@RequestMapping("/routes")
public class RoutesController {

    @Resource
    RoutesService routesService;

    @PostMapping(value = "/navigate")
    public List<RoutesVo> getRouteList(){
        return routesService.queryRoutesTree();
    }
}
