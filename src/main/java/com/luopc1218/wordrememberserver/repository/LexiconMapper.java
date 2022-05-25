package com.luopc1218.wordrememberserver.repository;

import java.util.List;

import com.luopc1218.wordrememberserver.entity.lexicon.Lexicon;
import com.luopc1218.wordrememberserver.entity.request.Pagination;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LexiconMapper {
    @Select("<script>select id,name,ownerId,cover,description from lexicon  where ownerId IS NULL <if test=\"userId != null\">OR ownerId=#{userId}</if> limit #{pagination.limit},#{pagination.pageSize}</script>")
    List<Lexicon> getLexiconList(Integer userId, @Param("pagination") Pagination pagination);

    @Select("<script>select count(*) as count from lexicon where ownerId IS NULL <if test=\"userId != null\">OR ownerId=#{userId}</if></script>")
    Integer getLexiconListCount(Integer userId);

    @Insert("")
    void createUserLexicon(Integer userId, String name);


}
