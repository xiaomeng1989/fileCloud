package com.xm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 尹晓蒙
 * @date 2020-05-21 16:09
 */
@TableName("user")
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
}
