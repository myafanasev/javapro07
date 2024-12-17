package ru.innotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innotech.dao.UserDAO;
import ru.innotech.dto.User;
import ru.innotech.exception.UserNotFound;

import java.util.List;

@Component
public class UserService {
    UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findAll() { return userDAO.findAll(); }

    public User findId(long ident) {
        User user = userDAO.findId(ident);
        if (user==null) throw new UserNotFound(); // если пользователь не найден, бросаем исключение
        return user;
    }

    public void delete(User user) {userDAO.delete(user);}

    public User save(User user) {
        if (userDAO.findId(user.getId()) != null)  // если это update
            return userDAO.update(user);
        else // значит insert
            return userDAO.insert(user);
    }
}
