package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.Category;

import java.util.List;

public interface ICategoryService {

    public List<Category> findAll();

    public Category findById(Long newsId);

    public void Add(Category entity);

    public Category Update(Category entity);

    public void Delete(Category entity);

}
