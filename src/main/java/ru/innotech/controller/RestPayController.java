package ru.innotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import ru.innotech.entity.Product;
import ru.innotech.service.PayService;

import java.util.List;

@RestController
public class RestPayController {
    PayService payService;

    @Autowired
    public RestPayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/{id}/getPayAllProducts")
    public List<Product> getAllProducts(@PathVariable("id") int idUser) {  //  получить список всех продуктов клиента
        return payService.findAllProductClient(idUser);
    }

    @GetMapping("/getPayProduct")
    public Product getProduct(@RequestParam("id") int idProduct) { // получить продукт по ID
        return payService.findProductId(idProduct);
    }

    @GetMapping("/checkPayProductBalance")
    // проверить баланс на продукте
    // проверяемую сумму "спрячем" в теле
    public Boolean checkProductBalance(@RequestParam("id") int idProduct, @RequestBody Double balance) {
        return payService.checkProductBalance(idProduct, balance);
    }

    @GetMapping("/changePayProductBalance") // изменение баланса, сумму "спрячем" в теле
    public Product changeProductBalance(@RequestParam("id") int idProduct, @RequestBody Double balance) {
        return payService.changeProductBalance(idProduct, balance);
    }
}

