package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.News;

import java.util.List;

public interface INewsService {

        public List<News> findAll();

        public List<News> findByCategory(Long categoryId);

        public News findById(Long newsId);

        public void Add(News entity);

        public News Update(News entity);

        public void Delete(News entity);

    }

