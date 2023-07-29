package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.project.core.util.ConstantUtil;
import com.company.project.entity.Routes;
import com.company.project.dao.RoutesMapper;
import com.company.project.entity.vo.RoutesMeta;
import com.company.project.entity.vo.RoutesVo;
import com.company.project.service.RoutesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author pzblog
 * @since 2023-07-27
 */
@Service
public class RoutesServiceImpl extends ServiceImpl<RoutesMapper, Routes> implements RoutesService {

    @Override
    public List<RoutesVo> queryRoutesTree() {
        Wrapper queryObj = new QueryWrapper<>().orderByAsc("level","sort");
        List<Routes> allRoutes = super.list(queryObj);
        List<RoutesVo> resultList = transferRoutesVo(allRoutes, ConstantUtil.rootParentId);
        return resultList;
    }

    /**
     * 封装菜单视图
     * @param allRoutes
     * @param parentId
     * @return
     */
    private List<RoutesVo> transferRoutesVo(List<Routes> allRoutes, Long parentId){
        List<RoutesVo> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(allRoutes)){
            for (Routes source : allRoutes) {
                if(parentId.longValue() == source.getParentId().longValue()){
                    RoutesVo menuVo = new RoutesVo();
                    BeanUtils.copyProperties(source, menuVo);
                    //set RoutesMeta
                    RoutesMeta routesMeta = new RoutesMeta();
                    routesMeta.setIcon(source.getIcon());
                    routesMeta.setTitle(source.getTitle());
                    menuVo.setMeta(routesMeta);
                    //查询子菜单，并封装
                    List<RoutesVo> childList = transferRoutesVo(allRoutes, source.getId());
                    if(!CollectionUtils.isEmpty(childList)){
                        menuVo.setChildren(childList);
                    }
                    resultList.add(menuVo);
                }
            }
        }
        return resultList;
    }
    
}
