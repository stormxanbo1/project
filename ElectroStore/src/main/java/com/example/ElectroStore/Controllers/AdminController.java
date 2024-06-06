package com.example.ElectroStore.Controllers;
import com.example.ElectroStore.dal.DataAccessLayer;
import com.example.ElectroStore.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/admin")
public class AdminController {
    private final DataAccessLayer dataAccessLayer;
    @Autowired
    public AdminController(DataAccessLayer dataAccessLayer) {
        this.dataAccessLayer = dataAccessLayer;
    }

    @DeleteMapping("/delete/product/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") long id){
        dataAccessLayer.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
    @PutMapping("/update/product/{id}")
    public ResponseEntity updateProductById(@PathVariable("id") long id, @RequestBody Product updatedProduct){
        dataAccessLayer.updateProductById(id, updatedProduct);
        return ResponseEntity.ok("Product updated successfully!");
    }
    @PostMapping("/create/user")
    public ResponseEntity createUser(@RequestBody User user){
        dataAccessLayer.createUser(user);
        return ResponseEntity.ok("User added successfully!");
    }
    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") long id){
        dataAccessLayer.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
    @PutMapping("/update/user/{id}")
    public ResponseEntity updateUserById(@PathVariable("id") long id, @RequestBody User updatedUser){
        dataAccessLayer.updateUser(id, updatedUser);
        return ResponseEntity.ok("User updated successfully!");
    }
    @DeleteMapping("/delete/basket/{id}")
    public ResponseEntity deleteBasketById(@PathVariable("id") long id){
        dataAccessLayer.deleteBasketById(id);
        return ResponseEntity.ok("Basket deleted successfully!");
    }
    @PutMapping("/update/basket/{id}")
    public ResponseEntity updateBasketById(@PathVariable("id") long id, @RequestBody Basket updatedBasket){
        dataAccessLayer.updateBasket(id, updatedBasket);
        return ResponseEntity.ok("Basket updated successfully!");
    }

    @DeleteMapping("/delete/order/{id}")
    public ResponseEntity deleteOrderById(@PathVariable("id") long id){
        dataAccessLayer.deleteOrderById(id);
        return ResponseEntity.ok("Order deleted successfully!");
    }
    @PutMapping("/update/order/{id}")
    public ResponseEntity updateOrderById(@PathVariable("id") long id, @RequestBody Order updatedOrder){
        dataAccessLayer.updateOrder(id, updatedOrder);
        return ResponseEntity.ok("Order updated successfully!");
    }
}