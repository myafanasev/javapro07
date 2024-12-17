package ru.innotech.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {   // наименование таблицы в БД
    String value();
}
