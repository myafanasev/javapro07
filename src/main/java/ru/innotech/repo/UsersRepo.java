package ru.innotech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innotech.entity.Users;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Long> {
    List<Users> findAll(); // получение всех пользователей
    Users findFirstById(Long id); // получение пользователя по ID
}
