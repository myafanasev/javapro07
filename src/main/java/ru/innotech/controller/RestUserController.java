package ru.innotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innotech.entity.Product;
import ru.innotech.entity.Users;
import ru.innotech.exception.ProductNotFound;
import ru.innotech.exception.UserNotFound;
import ru.innotech.service.UserProductService;
import ru.innotech.service.UserService;

import java.util.List;

@RestController
public class RestUserController {
    UserService userService;
    UserProductService userProductService;

    public RestUserController(UserService userService, UserProductService userProductService) {
        this.userService = userService;
        this.userProductService = userProductService;
    }

    //  получить все продукты клиента
    @GetMapping("/{id}/getAllProducts")
    public List<Product> getAllProducts(@PathVariable("id") int idUser) {
        return userProductService.findAllProductClient(userService.findId(idUser).getId());
    }

    // возвращает продукт с id, указанным в параметрах запроса
    @GetMapping("/getProduct")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@RequestParam("id") int idProduct) {
        return userProductService.findId(idProduct);
    }

    @GetMapping("/changeBalance") // изменение баланса, сумму "спрячем" в теле
    public Product checkBalance(@RequestParam("id") int idProduct, @RequestBody Double balance) {
        return userProductService.changeBalance(userProductService.findId(idProduct), balance);
    }
}
