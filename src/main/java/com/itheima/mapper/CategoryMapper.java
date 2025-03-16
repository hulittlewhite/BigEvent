package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 新增文章分类
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values(#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void addCategory(Category category);

    // 查询某用户的所有文章分类
    @Select("select * from category where create_user = #{userId}")
    List<Category> getCategoryList(Integer userId);

    // 根据 id 查询文章分类
    @Select("select * from category where id = #{id}")
    Category findCategoryById(Integer id);

    // 更新文章分类
    @Update("update category " +
            "set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = #{updateTime} " +
            "where id = #{id}")
    void updateCategory(Category category);

    // 删除文章分类
    @Delete("delete from category where id = #{id}")
    void deleteCategoryById(Integer id);

    // 根据分类名称查询分类
    @Select("select * from category where category_name = #{categoryName}")
    Category findCategoryByName(String categoryName);

    // 根据分类别名查询分类
    @Select("select * from category where category_alias = #{categoryAlias}")
    Category findCategoryByAlias(String categoryAlias);
}
