package com.batarang.api.Repository;

import com.batarang.api.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {
    public List<News> findByCategoryId(Long categoryId);
}
