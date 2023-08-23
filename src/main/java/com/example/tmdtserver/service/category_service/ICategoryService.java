package com.example.tmdtserver.service.category_service;

import com.example.tmdtserver.model.Category;
import com.example.tmdtserver.service.shop_service.core.ICrudService;

import java.util.List;

public interface ICategoryService extends ICrudService<Category> {
    List<Category> showAllCategory();
    List<Category> findCategoryOfShop(Long id);
}
