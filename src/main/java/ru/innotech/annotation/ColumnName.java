package ru.innotech.annotation;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnName { // наименование столбца таблицы в БД
    String value();
}
