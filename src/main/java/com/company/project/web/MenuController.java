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

    private static final String result = "[\n" +
            "    {\n" +
            "      path: '/',\n" +
            "      component: Layout,\n" +
            "      redirect: '/index',\n" +
            "      children: [\n" +
            "        {\n" +
            "          path: 'index',\n" +
            "          name: 'Index',\n" +
            "          component: '@/views/index/index',\n" +
            "          meta: {\n" +
            "            title: '首页',\n" +
            "            icon: 'home',\n" +
            "            affix: true,\n" +
            "          },\n" +
            "        },\n" +
            "      ],\n" +
            "    },\n" +
            "    {\n" +
            "      path: '/vab',\n" +
            "      component: Layout,\n" +
            "      redirect: 'noRedirect',\n" +
            "      name: 'Vab',\n" +
            "      alwaysShow: true,\n" +
            "      meta: { title: '组件', icon: 'box-open' },\n" +
            "      children: [\n" +
            "        {\n" +
            "          path: 'permissions',\n" +
            "          name: 'Permission',\n" +
            "          component: '@/views/vab/permissions/index',\n" +
            "          meta: {\n" +
            "            title: '角色权限',\n" +
            "            permissions: ['admin', 'editor'],\n" +
            "          },\n" +
            "        },\n" +
            "        {\n" +
            "          path: 'vviews',\n" +
            "          name: 'vviews',\n" +
            "          component: '@/views/vab/variableViews/index',\n" +
            "          meta: {\n" +
            "            title: 'vviews',\n" +
            "            permissions: ['admin', 'editor'],\n" +
            "            menuId: '132233',\n" +
            "          },\n" +
            "        },\n" +
            "        {\n" +
            "          path: 'treeDemo',\n" +
            "          component: '@/views/vab/treeDemo/index',\n" +
            "          name: 'TreeDemo',\n" +
            "          meta: {\n" +
            "            title: '可拖拽树管理',\n" +
            "            permissions: ['admin'],\n" +
            "          },\n" +
            "        },\n" +
            "      ],\n" +
            "    },\n" +
            "  ]";
    /**
     * 增加权限控制
     * @param baseDto
     * @return
     */
    @PostMapping(value = "/queryMenuTree")
    public List<MenuVo> queryTreeMenu(BaseDto baseDto){
        return menuService.queryMenuTree();
    }


    @PostMapping(value = "/queryMenus")
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

    @PostMapping(value = "/navigate")
    public List<MenuVo> getRouteList(){
        return menuService.queryMenuTree();
    }

}
