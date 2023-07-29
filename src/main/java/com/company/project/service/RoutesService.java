package com.company.project.service;

import com.company.project.entity.Routes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.vo.RoutesVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author pzblog
 * @since 2023-07-27
 */
public interface RoutesService extends IService<Routes> {

    List<RoutesVo> queryRoutesTree();
}
