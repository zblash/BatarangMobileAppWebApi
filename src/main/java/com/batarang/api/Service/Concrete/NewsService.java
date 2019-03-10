package com.batarang.api.Service.Concrete;

import com.batarang.api.Exceptions.ResourceNotFoundException;
import com.batarang.api.Model.News;
import com.batarang.api.Repository.CategoryRepository;
import com.batarang.api.Repository.NewsRepository;
import com.batarang.api.Service.Abstract.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return newsRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no category with id:"+categoryId.toString()));
    }

    @Override
    public News findById(Long newsId) throws ResourceNotFoundException {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no news with id:"+newsId.toString()));
    }

    @Override
    public void Add(News entity) throws ResourceNotFoundException {

        entity.setCategory(categoryRepository.findById(entity.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException("There is no category with id:"+entity.getCategory_id().toString())));
        newsRepository.save(entity);

    }

    @Override
    public News Update(News news, News updatedNews) throws ResourceNotFoundException {

        news.setTitle(updatedNews.getTitle());
        news.setDescription(updatedNews.getDescription());
        news.setCategory(categoryRepository.findById(updatedNews.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException("There is no category with id:"+updatedNews.getCategory_id().toString())));
        return newsRepository.save(news);
    }

    @Override
    public void Delete(News entity) {
        newsRepository.delete(entity);

    }

}

