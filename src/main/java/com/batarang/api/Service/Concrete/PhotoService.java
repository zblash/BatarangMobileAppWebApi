package com.batarang.api.Service.Concrete;

import com.batarang.api.Exceptions.ResourceNotFoundException;
import com.batarang.api.Model.Photo;
import com.batarang.api.Repository.NewsRepository;
import com.batarang.api.Repository.PhotoRepository;
import com.batarang.api.Service.Abstract.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService implements IPhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Photo findById(Long id) {
        return photoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void Create(String fileName){
        Photo photo = new Photo();
        photo.setNews(newsRepository.findById(photo.getNewsId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no news with id" + photo.getNewsId())));
        photo.setPhotoLink(fileName);
        photoRepository.save(photo);
    }

    @Override
    public void Delete(Photo photo) {
        photoRepository.delete(photo);
    }
}