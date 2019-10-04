package io.akave.java.practice.community.mapper;

import io.akave.java.practice.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(creator, title, description, tag, gmt_create, gmt_modified)" +
            " values(#{creator},#{title},#{description},#{tag},#{gmtCreate},#{gmtModified})")
    void create(Question question);
}
