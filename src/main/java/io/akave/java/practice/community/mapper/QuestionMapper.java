package io.akave.java.practice.community.mapper;

import io.akave.java.practice.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(creator, title, description, tag, gmt_create, gmt_modified)" +
            " values(#{creator},#{title},#{description},#{tag},#{gmtCreate},#{gmtModified})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
