package com.kshrd.demospringjwtpp.repository;

import com.kshrd.demospringjwtpp.model.entity.AppUser;
import com.kshrd.demospringjwtpp.model.request.RegisterRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppUserRepository {

    @Results(id = "userMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password")
    })

    @Select("""
        SELECT user_id, full_name, email, password FROM  app_users
        WHERE email = #{email}
    """)
    AppUser findUserByEmail(String email);


    @Select("""
        SELECT role_name FROM roles r
        INNER JOIN user_role ur 
        ON r.role_id = ur.role_id
        WHERE user_id = #{userId}
    """)
    List<String> getAllRolesByUserId(Integer userId);


    @Select("""
        insert into app_users(full_name, email, password)
        values (#{req.fullName}, #{req.email} ,#{req.password})
        returning user_id
    """)
    Integer registerUser(@Param("req") AppUser appUser);

    @Insert("""
    insert into user_role (user_id, role_id) 
    values (#{userId}, #{roleId})
    """)
    void insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Select("""
    SELECT role_id
    FROM roles
    WHERE role_name = #{roleName}
""")
    Integer findRoleIdByRoleName(@Param("roleName") String roleName);
}
