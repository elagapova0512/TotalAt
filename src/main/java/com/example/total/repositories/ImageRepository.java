package com.example.total.repositories;

import com.example.total.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findByProductId(int id);
    @Modifying
    @Query(value="delete from image where product_id = ?1",nativeQuery = true)
    void deleteImageFromProductById(int id);
}
