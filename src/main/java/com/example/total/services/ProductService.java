package com.example.total.services;

import com.example.total.models.Product;
import com.example.total.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }
    @Transactional
    public void saveProduct(Product product){
        productRepository.save(product);
    }
    @Transactional
    public void updateProductInfo(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

//    public List<Product> findByName(String name){
//        return productRepository.findByName(name);
//    }
//    public List<Product> findByCategory(String category){
//        return productRepository.findByName(category);
//    }
    public List<Product> findByNameStartingWith(String startingWith){
        return productRepository.findByNameIgnoreCaseStartingWith(startingWith);
          }
//          public  List<Product> findByNameAndCategoryOrderByPriceAsc (String name, String category){
//        return productRepository.findByNameAndCategoryOrderByPriceAsc(name,category);
//          }
//    public  List<Product> findByNameAndCategoryOrderByPriceDesc(String name, String category){
//        return productRepository.findByNameAndCategoryOrderByPriceDesc(name,category);
//    }
//
//    public List<Product> findByNameOrderByPriceAsc (String name){
//        return productRepository.findByNameOrderByPriceAsc(name);
//    }
//    public List<Product> findByNameOrderByPriceDesc (String name){
//        return productRepository.findByNameOrderByPriceDesc(name);
//    }
    public List<Product> findByNameStartingWithOrderByPriceAsc(String startingWith){
       return productRepository.findByNameIgnoreCaseStartingWithOrderByPriceAsc(startingWith);
    }

    public List<Product> findByNameStartingWithOrderByPriceDesc(String startingWith){
        return  productRepository.findByNameIgnoreCaseStartingWithOrderByPriceDesc(startingWith);
    }
    public List<Product> findByNameAndPriceBetween(String name,float from, float to){
        return productRepository.findByNameAndPriceGreaterThanEqualAndPriceLessThanEqual(name,from,to);
    }

}
