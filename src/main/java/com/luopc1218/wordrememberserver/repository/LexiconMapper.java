package com.luopc1218.wordrememberserver.repository;

import java.util.List;
import com.luopc1218.wordrememberserver.entity.lexicon.Lexicon;
import com.luopc1218.wordrememberserver.entity.request.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LexiconMapper {

    @Select("select id,name,onwerId from lexicon where onwerId=#{userId} limit #{pagination.limit},#{pagination.pageSize};")
    List<Lexicon> getUserLexicons(Integer userId, @Param("pagination") Pagination pagination);

    @Select("select count(*) as count from lexicon where onwerId=#{userId};")
    Integer getUserLexiconsCount(Integer userId);
}
