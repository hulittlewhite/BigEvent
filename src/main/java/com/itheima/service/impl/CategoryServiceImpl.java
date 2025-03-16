package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    /**
     * 添加分类
     *
     * @param category 分类对象
     */
    @Override
    public void addCategory(Category category) {
        // 补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);

        // 判断分类名称是否已存在
        Category categoryByName = findCategoryByName(category.getCategoryName());
        if (categoryByName != null) {
            throw new RuntimeException("分类名称已存在");
        }
        // 判断分类别名是否已存在
        Category categoryByAlias = findCategoryByAlias(category.getCategoryAlias());
        if (categoryByAlias != null) {
            throw new RuntimeException("分类别名已存在");
        }
        categoryMapper.addCategory(category);
    }


    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    @Override
    public List<Category> getCategoryList() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.getCategoryList(userId);
    }


    /**
     * 根据id查询分类对象
     *
     * @param id 分类id
     * @return 分类对象
     */
    @Override
    public Category findCategoryById(Integer id) {
        Category category = categoryMapper.findCategoryById(id);
        return category;
    }


    /**
     * 修改分类
     *
     * @param category 分类对象
     */
    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }


    /**
     * 根据id删除分类
     *
     * @param id 分类id
     */
    @Override
    public void deleteCategoryById(Integer id) {
        categoryMapper.deleteCategoryById(id);
    }


    /**
     * 根据分类名称查询分类对象
     *
     * @param categoryName 分类名称
     * @return 分类对象
     */
    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryMapper.findCategoryByName(categoryName);
    }


    /**
     * 根据分类别名查询分类对象
     *
     * @param categoryAlias 分类别名
     * @return 分类对象
     */
    @Override
    public Category findCategoryByAlias(String categoryAlias) {
        return categoryMapper.findCategoryByAlias(categoryAlias);
    }

}
