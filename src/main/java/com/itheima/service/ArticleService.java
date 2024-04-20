package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;

public interface ArticleService {

    // 新增文章
    void addArticle(Article article);

    // 条件分页列表查询
    PageBean<Article> getArticleList(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 文章详情
    Article findArticleById(Integer id);

    // 更新文章
    void updateArticle(Article article);

    // 删除文章
    void deleteArticleById(Integer id);
}
