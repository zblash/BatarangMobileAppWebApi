package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.News;

import java.util.List;
import java.util.Optional;

public interface INewsService {

        List<News> findAll();

        List<News> findByCategory(Long categoryId);

        News findById(Long newsId);

        void Add(News entity);

        News Update(News news, News updatedNews);

        void Delete(News entity);

    }

