package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author pzblog
 * @since 2023-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_routes")
public class Routes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * component   目前有3种 1、Layout  2、EmptyLayout  3、@/views/vab/variableViews/index
     */
    @TableField("component")
    private String component;

    /**
     * 父节点
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 节点类型，1文件夹，2页面，3按钮
     */
    @TableField("node_type")
    private Integer nodeType;

    /**
     * 图标地址    icon: 'home'  icon: 'bug'   icon: 'users-cog'   icon: 'cloud'  icon: 'shopping-cart'
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序号
     */
    @TableField("sort")
    private Integer sort;

    /**
     * redirect    '/index'    'noRedirect'   两种
     */
    @TableField("redirect")
    private String redirect;

    /**
     * 层次
     */
    @TableField("level")
    private Integer level;

    /**
     * path: '/vab' 一级路径带/   path: 'vviews'   树id的路径 整个层次上的路径id，逗号分隔，想要找父节点特别快
     */
    @TableField("path")
    private String path;

    /**
     * 是否删除 1：已删除；0：未删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 项目id
     */
    @TableField("project_id")
    private Long projectId;

    /**
     * 树id的路径 整个层次上的路径id，逗号分隔，想要找父节点特别快
     */
    @TableField("catalog")
    private String catalog;

    @TableField("title")
    private String title;

}
