package io.akave.java.practice.community.mapper;

import io.akave.java.practice.community.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    @Results(id = "user",
        value = {
                @Result(column = "id" , property = "id",javaType = Integer.class ,jdbcType = JdbcType.INTEGER),
                @Result(column = "account_id" , property = "accountId",javaType = String.class ,jdbcType = JdbcType.VARCHAR),
                @Result(column = "token" , property = "token",javaType = String.class ,jdbcType = JdbcType.VARCHAR),
                @Result(column = "gmt_create" , property = "gmtCreate",javaType = Long.class ,jdbcType = JdbcType.BIGINT),
                @Result(column = "gmt_modified" , property = "gmtModified",javaType = Long.class ,jdbcType = JdbcType.BIGINT),
                @Result(column = "bio" , property = "bio",javaType = String.class ,jdbcType = JdbcType.VARCHAR),
                @Result(column = "avatar_url" , property = "avatarUrl",javaType = String.class ,jdbcType = JdbcType.VARCHAR)
        }
    )
    @MapKey("user")
    User findUserByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findUserById(@Param("id") Integer id);
}
