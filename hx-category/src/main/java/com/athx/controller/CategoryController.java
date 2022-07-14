package com.athx.controller;

import com.athx.entity.ResponseResult;
import com.athx.entity.Result;
import com.athx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/14 14:17
 * @description：分类控制器
 * @modified By：
 * @version: $
 */
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public ResponseResult getCategories(@RequestHeader("Authorization") String token){
        return categoryService.getCategories();
    }

}
