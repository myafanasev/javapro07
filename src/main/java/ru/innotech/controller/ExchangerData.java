package ru.innotech.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import ru.innotech.entity.Product;

public interface ExchangerData {
    @GetExchange("/changeBalance")
    Product getChangeBalance(@RequestParam("id") long l, @RequestBody Double d);
}
