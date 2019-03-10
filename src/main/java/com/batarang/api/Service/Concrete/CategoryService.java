package com.batarang.api.Service.Concrete;

import com.batarang.api.Exceptions.ResourceNotFoundException;
import com.batarang.api.Model.Category;
import com.batarang.api.Repository.CategoryRepository;
import com.batarang.api.Service.Abstract.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no category with id:"+categoryId.toString()));
    }

    @Override
    public Category Add(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category Update(Category category, Category updatedCategory) {
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void Delete(Category category) {
        categoryRepository.delete(category);
    }
}
