package com.company.project.entity.vo;

import com.company.project.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuVo extends Menu {
    /**
     * 子菜单集合
     */
    private List<MenuVo> children;
    private Boolean leaf;
    private MenuMeta meta;
}
