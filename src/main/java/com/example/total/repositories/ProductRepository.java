package com.example.total.repositories;

import com.example.total.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
       List <Product> findByNameIgnoreCaseStartingWith(String startingWith);
    List<Product> findByNameIgnoreCaseStartingWithOrderByPriceAsc(String startingWith);
    List<Product> findByNameIgnoreCaseStartingWithOrderByPriceDesc(String startingWith);

    @Query(value = "select * from product where ((lower(name) LIKE '%?1%') or (lower(name) LIKE '?1%') OR (lower(name) LIKE '%?1')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Product> findByNameAndPriceGreaterThanEqualAndPriceLessThanEqual(String name, float from, float to);
}


