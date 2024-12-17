package ru.innotech.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import ru.innotech.exception.InsufficientFunds;
import ru.innotech.exception.ProductNotFound;
import ru.innotech.exception.UserNotFound;

@ControllerAdvice
public class ExceptionUserController {
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> notFoundProduct() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Продукт не найден");
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> notFoundClient() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
    }

    @ExceptionHandler(InsufficientFunds.class)
    public ResponseEntity<String> insufficientFunds() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("На продукте клиента недостаточно средств");
    }

}