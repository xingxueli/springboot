package com.company.project.web;


import com.company.project.entity.Menu;
import com.company.project.entity.dto.BaseDto;
import com.company.project.entity.vo.MenuVo;
import com.company.project.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 增加权限控制
     * @param baseDto
     * @return
     */
    @PostMapping(value = "/listAll")
    public List<MenuVo> queryTreeMenu(BaseDto baseDto){
        return menuService.queryMenuTree();
    }


    @PostMapping(value = "/list")
    public List<MenuVo> queryMenus(BaseDto request){
        return menuService.queryMenus(request.getUserId());
    }

    @PostMapping(value = "/create")
    public MenuVo create(@RequestBody MenuVo menuVo){
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo,menu);
        final Menu menu1 = menuService.addMenu(menu);
        BeanUtils.copyProperties(menu1,menuVo);
        return menuVo;
    }

}
