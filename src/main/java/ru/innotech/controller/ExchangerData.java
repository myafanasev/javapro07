package ru.innotech.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import ru.innotech.dto.UserProduct;

public interface ExchangerData {
    @GetExchange("/changeBalance")
    UserProduct getChangeBalance(@RequestParam("id") long l, @RequestBody Double d);
}
