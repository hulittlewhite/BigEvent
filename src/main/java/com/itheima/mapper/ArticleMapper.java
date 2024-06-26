package com.itheima.mapper;

import com.itheima.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {

    // 新增文章
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values(#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    void addArticle(Article article);

    // 查询文章列表
    List<Article> getArticleList(Integer userId, Integer categoryId, String state);

    // 根据 ID 查询文章
    @Select("select * from article where id = #{id}")
    Article findArticleById(Integer id);

    // 修改文章
    @Update("update article " +
            "set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = #{updateTime} " +
            "where id = #{id}")
    void updateArticle(Article article);

    // 删除文章
    @Delete("delete from article where id = #{id}")
    void deleteArticleById(Integer id);
}
