package com.example.tmdtserver.service.category_service;

import com.example.tmdtserver.model.Category;
import com.example.tmdtserver.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List<Category> showAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findCategoryOfShop(Long id) {
        return categoryRepository.findCategoryOfShop(id);
    }

    @Override
    public Page<Category> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {

    }
}
