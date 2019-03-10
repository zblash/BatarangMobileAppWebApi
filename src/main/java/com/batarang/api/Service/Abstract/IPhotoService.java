package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.Photo;

import java.util.List;

public interface IPhotoService {

    List<Photo> findAll();

    Photo findById(Long id);

    void Create(String fileName);

    void Delete(Photo photo);
}