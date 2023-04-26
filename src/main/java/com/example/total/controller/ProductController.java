package com.example.total.controller;

import com.example.total.repositories.ProductRepository;
import com.example.total.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")// будет срабатывать, когда будет такой адрес
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("")// ничего не указываем, тк сверху уже есть путь
    public String showAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/info/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_info";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "sortAndFilter", required = false, defaultValue = "") String sortAndFilter, @RequestParam("optionSort") String optionSort,Model model) { // надо принять все параметры с формы поиска, тк радио -инпуты могут быть отмечены, а может и нет, то это необязательные параметры
   methodSearch(sortAndFilter,optionSort,model);
   model.addAttribute("products", productRepository.findAll());
        return "product";
    }
    public void methodSearch(String sortAndFilter, String optionSort, Model model) {
        switch (sortAndFilter) {
            case "nameStartsWith":
                model.addAttribute("searchProduct", productService.findByNameStartingWith(optionSort));
                break;
            case "price":
                model.addAttribute("searchProduct", productService.findByNameStartingWithOrderByPriceAsc(optionSort));
                break;
            case "priceDesc":
                model.addAttribute("searchProduct", productService.findByNameStartingWithOrderByPriceDesc(optionSort));
                break;
        }
    }
}

