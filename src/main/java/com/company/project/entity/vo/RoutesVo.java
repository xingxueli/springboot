package com.company.project.entity.vo;

import com.company.project.entity.Routes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoutesVo extends Routes {
    /**
     * 子菜单集合
     */
    private List<RoutesVo> children;
    private RoutesMeta meta;
}
