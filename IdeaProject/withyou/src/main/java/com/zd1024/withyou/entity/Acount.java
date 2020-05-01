package com.zd1024.withyou.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("T_Acount")
public class Acount implements Serializable {
    private String username;
    private String password;
    @TableId
    private String userId;
    private Integer role;
}
