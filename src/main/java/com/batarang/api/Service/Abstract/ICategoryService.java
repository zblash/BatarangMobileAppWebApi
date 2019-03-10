package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

    Category findById(Long categoryId);

    Category Add(Category category);

    Category Update(Category category,Category updatedCategory);

    void Delete(Category category);

}
