package ru.innotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.innotech.entity.Users;
import ru.innotech.exception.UserNotFound;
import ru.innotech.repo.UsersRepo;

import java.util.List;

@Service
public class UserService {
    UsersRepo usersRepo;

    @Autowired
    public void setUserDAO(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public List<Users> findAll() { return usersRepo.findAll(); }

    public Users findId(long ident) {
        Users user = usersRepo.findFirstById(ident);
        if (user==null) throw new UserNotFound(); // если пользователь не найден, бросаем исключение
        return user;
    }

}
