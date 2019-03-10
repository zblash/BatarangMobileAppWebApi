package com.batarang.api.Service.Concrete;

import com.batarang.api.Exceptions.CategoryNotFoundException;
import com.batarang.api.Exceptions.NewsNotFoundException;
import com.batarang.api.Model.News;
import com.batarang.api.Repository.CategoryRepository;
import com.batarang.api.Repository.NewsRepository;
import com.batarang.api.Service.Abstract.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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
        Optional<News> news = newsRepository.findById(newsId);
        try {
            if (!news.isPresent())
                throw new NewsNotFoundException();
            return news.get();
        } catch (Exception ex) {
            throw new NewsNotFoundException();
        }
    }

    @Override
    public void Add(News entity) {

        entity.setCategory(categoryRepository.findById(entity.getCategory_id()).orElseThrow(CategoryNotFoundException::new));
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

