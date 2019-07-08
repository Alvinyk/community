package org.study.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.study.community.dto.QuestionDTO;
import org.study.community.model.Question;

import java.util.List;

/**
 * @author yangkai
 * @description 数据库 问题对象
 * @date 2019/6/19 12:58
 **/
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag) " +
            "values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") int offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    int count();
}
