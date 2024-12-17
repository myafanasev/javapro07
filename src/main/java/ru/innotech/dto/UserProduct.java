package ru.innotech.dto;

import lombok.*;
import ru.innotech.annotation.ColumnName;
import ru.innotech.annotation.TableName;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@TableName("user_product")
public class UserProduct {
    long id;
    @ColumnName("user_id")
    long userId;
    @ColumnName("acc_num")
    String accNum;
    double balance;
    String type;
}
