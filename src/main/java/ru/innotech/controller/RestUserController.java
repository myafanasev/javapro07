package ru.innotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innotech.dto.User;
import ru.innotech.dto.UserProduct;
import ru.innotech.exception.ProductNotFound;
import ru.innotech.exception.UserNotFound;
import ru.innotech.service.UserProductService;
import ru.innotech.service.UserService;

import java.util.List;

@RestController
public class RestUserController {
    UserService userService;
    UserProductService userProductService;
    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }
    @Autowired
    public void setUserProductService(UserProductService userProductService) { this.userProductService = userProductService; }

    @GetMapping("/{id}/getAllProducts")
    public List<UserProduct> getAllProducts(@PathVariable("id") int idUser) {
        return userProductService.findAllProductClient(userService.findId(idUser).getId());
    }

    // возвращает продукт с id, указанным в параметрах запроса
    @GetMapping("/getProduct")
    @ResponseStatus(HttpStatus.OK)
    public UserProduct getProduct(@RequestParam("id") int idProduct) {
        return userProductService.findId(idProduct);
    }

    @GetMapping("/changeBalance") // изменение баланса, сумму "спрячем" в теле
    public UserProduct checkBalance(@RequestParam("id") int idProduct, @RequestBody Double balance) {
        return userProductService.changeBalance(userProductService.findId(idProduct), balance);
    }
}
