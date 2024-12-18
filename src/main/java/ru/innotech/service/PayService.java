package ru.innotech.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.innotech.controller.ExchangerData;
import ru.innotech.entity.Product;
import ru.innotech.exception.InsufficientFunds;
import ru.innotech.exception.ProductNotFound;
import ru.innotech.exception.UserNotFound;

import java.net.http.HttpClient;
import java.util.List;

@Service
public class PayService {
    RestClient restClient;

    public PayService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Product> findAllProductClient(long id)  //  получить список всех продуктов клиента
    {
        List<Product> productList;
        try {
            productList = restClient.get().uri("/{id}/getAllProducts", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
        } catch (HttpClientErrorException e) {
            throw new UserNotFound();
        }
        return productList;
    }

    public Product findProductId(long idProduct)    // получить продукт по ID
    {
        Product product;
        try {
            product = restClient.get().uri("/getProduct?id={id}", idProduct)
                    .retrieve()
                    .body(Product.class);
        } catch (HttpClientErrorException e) {
            throw new ProductNotFound();
        }
        return product;
    }

    void checkBalance(double currentBalance, double change) { // проверка достаточности средств для изменения
        if (change < 0 && currentBalance < Math.abs(change)) // только если идёт уменьшение
            throw new InsufficientFunds(); // если средств недостаточно, бросаем исключение
    }

    public Boolean checkProductBalance(long idProduct, double balance)    // проверить баланс на продукте
    {
        Product product = findProductId(idProduct); // найдём продукт
        checkBalance(product.getBalance(), balance);
        return true;
    }

    public Product changeProductBalance(long idProduct, double balance)    // изменить баланс на продукте
    {
        checkProductBalance(idProduct, balance); // проверить баланс на продукте
        // здесь попробуем через интерфейс
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        ExchangerData exchangerData = factory.createClient(ExchangerData.class);

        return exchangerData.getChangeBalance(idProduct, balance);
    }
}

@Component
class RestClientUser {
    @Bean
    public RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }
}