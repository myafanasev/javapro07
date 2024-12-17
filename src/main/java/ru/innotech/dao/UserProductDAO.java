package ru.innotech.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.innotech.dto.User;
import ru.innotech.dto.UserProduct;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserProductDAO extends ParentDAO{

    public UserProductDAO() {
        super(UserProduct.class);
    }

    @SneakyThrows
    public List<UserProduct> findAll(long idUser) { // получение всех продуктов всех клиентов или конкретного
        Statement statement = connection.createStatement();

        String query = "select " + columns.get("id") + ", " + columns.get("userId") + ", " + columns.get("accNum")  + ", " + columns.get("balance")+ ", " + columns.get("type") + " from " + tableName;

        if (idUser != 0) // если передан конкретный клиент
            query += " where " + columns.get("userId") + " = " + idUser;

        ResultSet resultSet = statement.executeQuery(query);

        List<UserProduct> userProductList = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getInt(columns.get("id"));
            long userId = resultSet.getInt(columns.get("userId"));
            String accNum = resultSet.getString(columns.get("accNum"));
            double balance = resultSet.getDouble(columns.get("balance"));
            String type = resultSet.getString(columns.get("type"));
            userProductList.add(new UserProduct(id, userId, accNum, balance,type));
        }

        statement.close();
        return userProductList;
    }
    @SneakyThrows

    public UserProduct findId(long idProduct) { // получение продукта по ID
        Statement statement = connection.createStatement();
        String query = "select " + columns.get("id") + ", " + columns.get("userId") + ", " + columns.get("accNum")  + ", " + columns.get("balance")+ ", " + columns.get("type") + " from " + tableName + " where " + columns.get("id") + " = " + idProduct;;

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            long id = resultSet.getInt(columns.get("id"));
            long userId = resultSet.getInt(columns.get("userId"));
            String accNum = resultSet.getString(columns.get("accNum"));
            double balance = resultSet.getDouble(columns.get("balance"));
            String type = resultSet.getString(columns.get("type"));
            return new UserProduct(id, userId, accNum, balance,type);
        }

        statement.close();
        return null;
    }

    @SneakyThrows
    public UserProduct changeBalance(UserProduct userProduct, double balance) { // изменение баланса на указанную сумму
        Statement statement = connection.createStatement();
        userProduct.setBalance(userProduct.getBalance() + balance); // сначала игменим в объекте
        String query = "update " + tableName + " set " + columns.get("balance") + " = " + userProduct.getBalance() + " where " + columns.get("id") + " = " + userProduct.getId();
        statement.execute(query);
        statement.close();
        return userProduct;
    }
}
