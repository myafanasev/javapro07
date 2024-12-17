package ru.innotech.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.innotech.annotation.ColumnName;
import ru.innotech.annotation.TableName;
import ru.innotech.dto.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public abstract class ParentDAO {
    Map<String, String> columns = new HashMap<>();  // список полей таблицы
    String tableName;   // наименование таблицы
    Connection connection;  // соединение

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ParentDAO(Class c) {
        Class<?> cls = c;

        // получим имя таблицы
        if (cls.isAnnotationPresent(TableName.class)) // если есть аннотация с именем таблицы
            tableName = cls.getAnnotation(TableName.class).value();
        else tableName = cls.getSimpleName();
        // получим информацию о колонках таблицы
        for (Field f : cls.getDeclaredFields()) {
            String nameColumn;
            if (f.isAnnotationPresent(ColumnName.class)) // если есть аннотация с именем столбца таблицы
                nameColumn = f.getAnnotation(ColumnName.class).value();
            else nameColumn = f.getName();
            columns.put(f.getName(), nameColumn);
        }
    }
}
