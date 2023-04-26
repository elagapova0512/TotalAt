package com.example.total.services;

import com.example.total.models.Image;
import com.example.total.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    public final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    @Transactional
   public void deleteImageById(int id){
        imageRepository.deleteById(id);
   }
   @Transactional
   public void addImage(Image image){
        imageRepository.save(image);
   }
   public Image getImageById(int id){
        Optional<Image> imageOptional = imageRepository.findById(id);
        return imageOptional.orElse(null);
   }


}
