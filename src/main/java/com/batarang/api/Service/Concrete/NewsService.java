package com.batarang.api.Service.Concrete;

import com.batarang.api.Model.News;
import com.batarang.api.Repository.NewsRepository;
import com.batarang.api.Service.Abstract.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> findByCategory(Long categoryId) {
        return newsRepository.findByCategoryId(categoryId);
    }

    @Override
    public News findById(Long newsId) {
        return newsRepository.findById(newsId).orElseThrow(() -> new RuntimeException());


    }

    @Override
    public void Add(News entity) {
        newsRepository.save(entity);

    }

    @Override
    public News Update(News entity) {
        return newsRepository.save(entity);
    }

    @Override
    public void Delete(News entity) {
        newsRepository.delete(entity);

    }

}

