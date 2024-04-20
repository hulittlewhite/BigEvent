package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {
//        // 验证 token
////        try {
////            Map<String, Object> claims = JwtUtil.parseToken(token);
////            return Result.success("所有的文章数据...");
////        } catch (Exception e) {
////            // http 响应状态码为 401
////            response.setStatus(401);
////            return Result.error("未登录！");
////        }
//        return Result.success("所有的文章数据...");
//    }

    @PostMapping
    public Result addArticle(@RequestBody @Validated(Article.Add.class) Article article) {
        articleService.addArticle(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> getArticleList(Integer pageNum,
                                                    Integer pageSize,
                                                    @RequestParam(required = false) Integer categoryId,
                                                    @RequestParam(required = false) String state) {
        PageBean<Article> articleList = articleService.getArticleList(pageNum, pageSize, categoryId, state);
        return Result.success(articleList);
    }

    @GetMapping("/detail")
    public Result<Article> getArticleDetail(Integer id) {
        Article article = articleService.findArticleById(id);
        return Result.success(article);
    }

    @PutMapping
    public Result updateArticle(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.updateArticle(article);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteArticle(Integer id) {
        articleService.deleteArticleById(id);
        return Result.success();
    }

}
