package com.example.total.controller;

import com.example.total.enumm.Status;
import com.example.total.models.Cart;
import com.example.total.models.Order;
import com.example.total.models.Person;
import com.example.total.models.Product;
import com.example.total.repositories.CartRepository;
import com.example.total.repositories.OrderRepository;
import com.example.total.repositories.ProductRepository;
import com.example.total.security.PersonDetails;
import com.example.total.services.PersonService;
import com.example.total.services.ProductService;
import com.example.total.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    public MainController(PersonService personService, PersonValidator personValidator, ProductService productService, ProductRepository productRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.productService = productService;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    private final PersonService personService;
    private final PersonValidator personValidator;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/pers_account")// изначально было /index
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "index";
    }

    @GetMapping("/logIn")
    public String login() {
        return "logIn";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("newPerson") Person person) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        personService.addPerson(person);
        return "redirect:/pers_account";
    }

    @GetMapping("/pers_account/product")
    public String showAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/pers_account/product/info/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_info";
    }

    @PostMapping("/pers_account/search")
    public String search(@RequestParam(value = "sortAndFilter",required = false,defaultValue = "") String sortAndFilter, @RequestParam("optionSort") String optionSort, Model model) {
        // надо принять все параметры с формы поиска, тк радио -инпуты могут быть отмечены, а может и нет, то это необязательные параметры
        model.addAttribute("products", productRepository.findAll());
               switch (sortAndFilter){
            case "nameStartsWith":
                model.addAttribute("searchProduct",productService.findByNameStartingWith(optionSort));
                break;
            case "price":
                model.addAttribute("searchProduct",productService.findByNameStartingWithOrderByPriceAsc(optionSort));
                break;
            case "priceDesc":
                model.addAttribute("searchProduct",productService.findByNameStartingWithOrderByPriceDesc(optionSort));
                break;
        }
          return "index";
    }

        @GetMapping("/cart/add/{id}")// гет-запрос, тк нажимаем на обычную ссылку
    public String addProductToCart(@PathVariable("id") int id) {
        // определяем, какой продукт хотят добавить в таблицу по id
        Product product = productService.getProductById(id);
        // извлекаем объект аутентификации пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        // из полученного объекта Персон извлекаем id
        int personId = personDetails.getPerson().getId();
        // формируем новую корзину
        Cart cart = new Cart(personId, product.getId());
        cartRepository.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId();
        List<Cart> cartList = cartRepository.findByPersonId(personId); // метод позволяет вернуть всё содержимое корзины по id пользователя
        // создаем пустой лист продуктов
        List<Product> productListInCart = new ArrayList<>();
        // получаем продукты из корзины по id товара
        for (Cart cart : cartList) {
            productListInCart.add(productService.getProductById(cart.getProductId()));
        }
        // вычисление итоговой цены
        float totalSum = 0;
        for (Product prod: productListInCart) {
            totalSum = totalSum + prod.getPrice();
        }
        model.addAttribute("total", totalSum);
        model.addAttribute("productToCart", productListInCart);
                return "cart";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteProductFromCart(@PathVariable("id") int id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId(); // получаем id аутентифицированного пользователя из сессии
        List<Cart> cartList = cartRepository.findByPersonId(personId); // метод позволяет вернуть всё содержимое корзины по id пользователя
        List<Product> productListInCart = new ArrayList<>();
        for (Cart cart : cartList) {
            productListInCart.add(productService.getProductById(cart.getProductId()));
        }
        cartRepository.deleteProductFromCartById(id);
        return "redirect:/cart";
    }
    @GetMapping("/order/create")
   public String createOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId();
        List<Cart> cartList = cartRepository.findByPersonId(personId); // метод позволяет вернуть всё содержимое корзины по id пользователя
        // создаем пустой лист продуктов
        List<Product> productListInCart = new ArrayList<>();
        // получаем продукты из корзины по id товара
        for (Cart cart : cartList) {
            productListInCart.add(productService.getProductById(cart.getProductId()));
        }
        // вычисление итоговой цены
        float totalSum = 0;
        int count = 1;
        for (Product prod: productListInCart) {
            totalSum = totalSum + prod.getPrice();
                   }
               // получим объект Персон
        //Person person = personDetails.getPerson();
        // сгенерируем уникальный номер заказа
        String uuid = UUID.randomUUID().toString();
        Order newOrder = new Order();
        for (Product prod: productListInCart) {
            //if
            newOrder = new Order(uuid,prod,personDetails.getPerson(),1,prod.getPrice(),Status.ORDER);
            orderRepository.save(newOrder);
            cartRepository.deleteProductFromCartById(prod.getId());
        }
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        List <Order> orderList = orderRepository.findOrdersByPerson(personDetails.getPerson());
        model.addAttribute("orders",orderList);
        return "orders";
    }


}
