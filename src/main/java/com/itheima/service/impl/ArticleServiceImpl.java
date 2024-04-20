package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        // 补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.addArticle(article);
    }

    /**
     *
     * @param pageNum 当前页码
     * @param pageSize  每页条数
     * @param categoryId 文章分类 ID
     * @param state 发布状态：已发布｜草稿
     * @return 文章列表
     */
    @Override
    public PageBean<Article> getArticleList(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1.创建 PageBean 对象
        PageBean<Article> pb = new PageBean<>();

        // 2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // 3.调用 mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> articleList = articleMapper.getArticleList(userId, categoryId, state);

        // Page 中提供了方法，可以获取 PageHelper 分页查询后得到的总记录条数和当前页数据。
        Page<Article> articlePage = (Page<Article>) articleList;

        // 将数据填充到 PageBean 对象中
        pb.setTotal(articlePage.getTotal());
        pb.setItems(articlePage.getResult());

        return pb;
    }

    @Override
    public Article findArticleById(Integer id) {
        Article article = articleMapper.findArticleById(id);
        return article;
    }

    @Override
    public void updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleMapper.deleteArticleById(id);
    }

}
