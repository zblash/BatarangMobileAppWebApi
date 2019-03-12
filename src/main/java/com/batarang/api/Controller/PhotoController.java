package com.batarang.api.Controller;

import com.batarang.api.Service.Concrete.PhotoService;
import com.batarang.api.Service.Concrete.StorageService;
import com.batarang.api.Validations.ValidImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController("/api/photo")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PhotoController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private PhotoService photoService;

    @PostMapping("/photo/add")
    public ResponseEntity<?> uploadPhoto(@ValidImg @RequestParam("photo") MultipartFile uploadfile){
        String fileName = storageService.store(uploadfile);
        photoService.Create(fileName);
        return new ResponseEntity<>(fileName, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/photos")
    public ResponseEntity<?> getPhotos(){
        return new ResponseEntity<>(photoService.findAll(), new HttpHeaders(), HttpStatus.OK);
    }

}