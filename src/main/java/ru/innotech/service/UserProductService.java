package ru.innotech.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.innotech.entity.Product;
import ru.innotech.exception.ProductNotFound;
import ru.innotech.repo.ProductRepo;

import java.util.List;

@Service
public class UserProductService {
    ProductRepo productRepo;

    @Autowired
    public UserProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() { return productRepo.findAll();} //  все продукты всех клиентов

    public List<Product> findAllProductClient(long id) { return productRepo.findAllByUserId(id);} //  все продукты клиента

    public Product findId(long idProduct)    // получить продукт по ID
    {
        Product product = productRepo.findFirstById(idProduct);
        if (product==null) throw new ProductNotFound(); // если продукт не найден, бросаем исключение
        return product;
    }

    @Transactional
    public Product changeBalance(Product product, double balance)    // изменить баланс
    {
        product.setBalance(product.getBalance() + balance);
        productRepo.save(product);
        return product;
    }
}
