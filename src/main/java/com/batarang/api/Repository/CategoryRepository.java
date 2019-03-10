package com.batarang.api.Repository;

import com.batarang.api.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {



}
