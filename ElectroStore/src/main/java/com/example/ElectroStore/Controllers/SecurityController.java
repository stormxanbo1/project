package com.example.ElectroStore.Controllers;

import com.example.ElectroStore.ElectroStoreApplication;
import com.example.ElectroStore.dal.DataAccessLayer;
import com.example.ElectroStore.dto.SigninRequest;
import com.example.ElectroStore.dto.SignupRequest;
import com.example.ElectroStore.models.Basket;
import com.example.ElectroStore.models.Discount;
import com.example.ElectroStore.models.Product;
import com.example.ElectroStore.security.JwtCore;
import com.example.ElectroStore.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class SecurityController {
    private final UserDetailsServiceImpl userService;
    private final DataAccessLayer dataAccessLayer;
    @Autowired
    public SecurityController(UserDetailsServiceImpl userService, DataAccessLayer dataAccessLayer) {
        this.userService = userService;
        this.dataAccessLayer = dataAccessLayer;
    }
    @Autowired
    private JwtCore jwtCore;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        String serviceResult = userService.newUser(signupRequest);
        if (Objects.equals(serviceResult, "Выберите другое имя")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serviceResult);
        }
        if (Objects.equals(serviceResult, "Выберите другую почту")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serviceResult);
        }
        return ResponseEntity.ok("Вы успешно зарегистрированы. Теперь можете войти в свой аккаунт.");
    }

    @PostMapping("/signin")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        UserDetails user = userService.loadUserByUsername(signinRequest.getUserName());
        String hashedPassword = passwordEncoder.encode(signinRequest.getPassword());        // Сравниваем хешированный пароль из запроса с хешированным паролем пользователя из базы данных
        if (Objects.equals(user, null) || !passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            log.info("Ошибка авторизации пользователя " + signinRequest.getUserName());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String jwt = jwtCore.generateToken(user);
        ElectroStoreApplication.currentUser = userService.loadUserEntityByUsername(signinRequest.getUserName());
        log.info("Вход прошёл успешно");
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity getUserById(@PathVariable("id") long id){
        return ResponseEntity.ok(dataAccessLayer.getUserById(id));
    }
    @PostMapping("/create/product")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            dataAccessLayer.createProduct(product);
            return ResponseEntity.ok("Product added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product: " + e.getMessage());
        }
    }
    @GetMapping("/get/products")
    public ResponseEntity getProducts(){
        return ResponseEntity.ok(dataAccessLayer.getProducts());
    }
    @PostMapping("/create/discount")
    public ResponseEntity createDiscount(@RequestBody Discount discount){
        dataAccessLayer.createDiscount(discount);
        return ResponseEntity.ok("Discount added successfully!");
    }

    @GetMapping("/get/baskets/{userId}")
    public ResponseEntity<List<Basket>> getBasketsByUserId(@PathVariable("userId") long userId) {
        return ResponseEntity.ok(dataAccessLayer.getBasketsByUserId(userId));
    }
    @PostMapping("/create/basket")
    public ResponseEntity<String> createUser(@RequestBody Basket basket) {
        dataAccessLayer.createBasket(basket);
        return ResponseEntity.ok("Basket added successfully!");
    }
}
