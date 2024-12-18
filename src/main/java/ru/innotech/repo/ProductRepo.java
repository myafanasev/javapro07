package ru.innotech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innotech.entity.Product;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAll();    // получение всех продуктов
    List<Product> findAllByUserId(long userId); // получение всех продуктов клиента
    Product findFirstById(long id); // получение продукта по ID

}
