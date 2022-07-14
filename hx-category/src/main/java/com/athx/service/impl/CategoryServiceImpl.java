package com.athx.service.impl;

import com.athx.dao.CategoryMapper;
import com.athx.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import  com.athx.entity.Category;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
