package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("T_Acount")
public class Acount {
    private String username;
    private String password;
    private String userId;
    private Integer role;
}
