package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {

    // 新增分类
    void addCategory(Category category);

    // 列表查询
    List<Category> getCategoryList();

    // 根据 id 查询分类信息
    Category findCategoryById(Integer id);

    // 更新文章分类
    void updateCategory(Category category);

    // 删除文章分类
    void deleteCategoryById(Integer id);
}
