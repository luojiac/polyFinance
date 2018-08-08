package com.jnshu.dao2;

import com.jnshu.Domain2.DomainRoleBack;
import com.jnshu.Domain2.DomainRoleBackList;
import com.jnshu.entity.RoleBack;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "roleBackMapper2")
public interface RoleBackMapper2 {

    @Insert("insert into role_back(create_at, update_at, create_by,update_by,role) values (#{createAt}, #{updateAt},#{createBy},#{updateBy},#{role})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Long saveRoleBack(RoleBack roleBack) throws Exception;

    @Delete("delete from role_back where id=#{id}")
    Boolean deleteRoleBack(Long id) throws Exception;

    //没有更新
    @Select("select role from role_back where id=#{id}")
    String getRoleNameById(Long id) throws Exception;

    @Select("select distinct a.id, a.create_at, b.login_name as createBy, a.update_at, b.login_name as updateBy, a.role from role_back a , user_back b where a.create_by=b.id or a.update_by=b.id")
    List<DomainRoleBackList> getRoleBacks() throws Exception;

    //查询--根据角色查询id。
    @Select("select id from role_back where role=#{role}")
    Long getRoleIdByRole(String role) throws Exception;

    //查询--角色名
    @Select("select a.id, a.role from role_back a inner join role_user_back b on b.role_id = a.id where b.user_id=#{id}")
    RoleBack getRoleByUserId(Long id) throws Exception;
    //查询总数
    @Select("select count(*) from role_back")
    public Integer getTotal() throws Exception;

    //账户编辑的角色列表。
    @Select("select id, role from role_back")
    List<DomainRoleBack> getAll() throws Exception;
}
