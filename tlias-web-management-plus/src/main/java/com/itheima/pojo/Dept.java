package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * 部门实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //ID
    private String name; //部门名称
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间


}
