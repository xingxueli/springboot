package com.company.project.dao;

import com.company.project.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author pzblog
 * @since 2020-06-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select c.code from tb_user a,tb_user_role b,tb_role c where a.id = b.user_id and b.role_id = c.id and a.id = #{userId}")
    List<String> getRoleCodes(Long userId);
}
