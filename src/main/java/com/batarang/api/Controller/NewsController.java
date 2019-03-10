package com.batarang.api.Controller;

import com.batarang.api.Model.News;
import com.batarang.api.Service.Concrete.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<News> getAll(){
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getById(@PathVariable Long id){

        return ResponseEntity.ok(newsService.findById(id));
    }

    @GetMapping("/bycategory/{id}")
    public List<News> getByCategory(@PathVariable Long id){
        return newsService.findByCategory(id);
    }

    @PostMapping("/add")
    public News createNews(@Valid @RequestBody News news){
        return newsService.Add(news);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String,News> deleteNews(@PathVariable(value = "id") Long id){
        News news = newsService.findById(id);
        newsService.Delete(news);
        Map<String,News> response = new HashMap<>();
        response.put("deleted",news);
        return response;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<News> updateNews(@PathVariable(value = "id") Long id,@Valid @RequestBody News updatednews){

        News news = newsService.findById(id);
        return ResponseEntity.ok(newsService.Update(news,updatednews));
    }


}
