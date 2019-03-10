package com.batarang.api.Repository;

import com.batarang.api.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News,Long> {

    Optional<List<News>> findByCategoryId(Long categoryId);
}
