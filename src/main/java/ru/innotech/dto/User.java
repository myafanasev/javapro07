package ru.innotech.dto;

import lombok.*;
import ru.innotech.annotation.ColumnName;
import ru.innotech.annotation.TableName;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@TableName("users")
public class User {
    long id;
    @ColumnName("username")
    String user;
}
