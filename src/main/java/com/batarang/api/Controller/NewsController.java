package com.batarang.api.Controller;

import com.batarang.api.Exceptions.ErrorResponse;
import com.batarang.api.Exceptions.NewsNotFoundException;
import com.batarang.api.Model.News;
import com.batarang.api.Service.Concrete.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    public List<News> getAll(){
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public News getById(@PathVariable(name = "id") String id){
        return newsService.findById(Long.valueOf(id));
    }

    @PostMapping("/add")
    public News createNews(@Valid @RequestBody News news){
        newsService.Add(news);
        return news;
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNewsNotFound(Exception exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.toString(),new Date());
        return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);
    }

}
