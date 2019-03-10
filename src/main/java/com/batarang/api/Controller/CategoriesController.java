package com.batarang.api.Controller;

import com.batarang.api.Model.Category;
import com.batarang.api.Service.Concrete.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){

        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/add")
    public Category createCategory(@Valid @RequestBody Category category){

        return categoryService.Add(category);

    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Category> deleteCategory(@PathVariable(value = "id") Long id){
        Category category = categoryService.findById(id);
        categoryService.Delete(category);
        Map<String,Category> response = new HashMap<>();
        response.put("deleted",category);
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long id,@Valid @RequestBody Category updatedCategory){

        Category category = categoryService.findById(id);
        return ResponseEntity.ok(categoryService.Update(category,updatedCategory));
    }


}
