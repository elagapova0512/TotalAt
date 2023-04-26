package com.example.total.controller;

<<<<<<< HEAD
=======
import com.example.total.enumm.Status;
>>>>>>> db66184 (SPRING total)
import com.example.total.models.Cart;
import com.example.total.models.Image;
import com.example.total.models.Order;
import com.example.total.models.Product;
<<<<<<< HEAD
import com.example.total.repositories.OrderRepository;
import com.example.total.security.PersonDetails;
=======
import com.example.total.repositories.ImageRepository;
import com.example.total.repositories.OrderRepository;
import com.example.total.services.ImageService;
>>>>>>> db66184 (SPRING total)
import com.example.total.services.OrderService;
import com.example.total.services.PersonService;
import com.example.total.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
>>>>>>> db66184 (SPRING total)
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {
    private final ProductService productService;
    private final PersonService personService;
    private final OrderRepository orderRepository;
       private final OrderService orderService;
<<<<<<< HEAD
=======
       private final ImageService imageService;
       private final ImageRepository imageRepository;
>>>>>>> db66184 (SPRING total)

    @Value("${upload.path}")
private String uploadPath;

<<<<<<< HEAD
    public AdminController(ProductService productService, PersonService personService, OrderRepository orderRepository, OrderService orderService) {
        this.productService = productService;
        this.personService = personService;
        this.orderRepository = orderRepository;

        this.orderService = orderService;
=======
    public AdminController(ProductService productService, PersonService personService, OrderRepository orderRepository, OrderService orderService, ImageService imageService, ImageRepository imageRepository) {
        this.productService = productService;
        this.personService = personService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
>>>>>>> db66184 (SPRING total)
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("products", productService.getAllProducts());// выводим на страницу все существующие продукты
        model.addAttribute("people", personService.getAllUsers());
        model.addAttribute("orders", orderService.showAllOrders());
               return "admin";
    }
    @GetMapping("/admin/product/add")
    public String addProduct(Model model){
        model.addAttribute("newProduct", new Product());
                return "product_add";
    }
    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute("newProduct") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three) throws IOException {
        if(bindingResult.hasErrors()){
              return "product_add";
        }
        if(file_one != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
        if(file_two != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image(); // пустой объект модели Имейдж
            image.setProduct(product); // к фото привязываем продукт
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
        if(file_three != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
           productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
       productService.deleteProduct(id);
       return "redirect:/admin";
    }
@GetMapping("/admin/product/update/{id}")
    public String updateProductInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("updateProduct", productService.getProductById(id));
        return "product_update";
    }
@PostMapping("/admin/product/update/{id}")
<<<<<<< HEAD
public String updateProductInfo(@PathVariable("id") int id, @ModelAttribute("updateProduct") @Valid Product product,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "product_update";
        }
        productService.updateProductInfo(id,product);
=======
public String updateProductInfo(@PathVariable("id") int id, @ModelAttribute("updateProduct") @Valid Product product,BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            return "product_update";
        }
            productService.updateProductInfo(id,product);
>>>>>>> db66184 (SPRING total)
return "redirect:/admin";
}

@GetMapping("/admin/person/role/{id}")
public String changeRole(@PathVariable("id") int id, Model model){
model.addAttribute("updatePerson", personService.getPersonById(id));
return "change_role";
}
    @PostMapping("/admin/person/role/{id}")
    public String changeRole(@PathVariable("id") int id,@RequestParam("changeRole") String role){
        if(role.equals("admin")){
            personService.changeRoleToAdmin(id);
        } else if (role.equals("user")) {
            personService.changeRoleToUser(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/person/{id}")
    public String showPerson(@PathVariable("id") int id,Model model){
        model.addAttribute("person", personService.getPersonById(id));
        return "person_info";
    }
    @GetMapping("/admin/order/{id}")
    public String showOrder(@PathVariable("id") int id,Model model){
<<<<<<< HEAD
        //model.addAttribute("order", orderService.showAllOrders());
        model.addAttribute("order", orderService.getOrderById(id));
//        String number = orderService.getOrderById().getNumber();
//        List<Order> orderList= orderService.findByNumber(number);
//        List<Product> productListInOrder = new ArrayList<>();
//        for (Order order : orderList) {
//            productListInOrder.add(productService.getProductById(order.getProduct().getId()));
//        }
//        float totalSum = 0;
//        int count = 0;
//        for (Product prod: productListInOrder) {
//            totalSum = totalSum + prod.getPrice();
//            count +=1;
//        }
//        model.addAttribute("total", totalSum);
//        model.addAttribute("count", count);
//        model.addAttribute("productToOrder", productListInOrder);
        return "order_info";
    }
//    @GetMapping("/admin/order/change/{number}")
//    public String changeStatus(@PathVariable("number") String number, Model model){
//        model.addAttribute("updateOrder",orderService.findByNumber(number));
//      return "change_status";
//    }
//    @PostMapping("/admin/order/change/{number}")
//    public String changeStatus(@PathVariable("number") String number, @ModelAttribute("updateOrder") Order order){
//orderService.updateOrderStatus(number,order);
//        return "redirect:/admin";
//    }
=======
        model.addAttribute("order", orderService.getOrderById(id));
        return "order_info";
    }
>>>>>>> db66184 (SPRING total)
    @PostMapping("/admin/order/search")
    public String searchOrder(@RequestParam("search") String endingWith, Model model){
   model.addAttribute("searchOrder",orderService.findByNumberEndingWithIgnoreCase(endingWith));
   model.addAttribute("people", personService.getAllUsers());
   model.addAttribute("products", productService.getAllProducts());
        return "admin";
    }

}
