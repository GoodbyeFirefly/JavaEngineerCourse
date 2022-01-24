package com.xxy.entity;

import javax.persistence.*;

// 类名和表名不一致，需要进行映射。这样才能使用tk_mybatis内置CRUD
@Table(name = "bill_type_")
public class BillType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "name_")
    private String name;

    @Override
    public String toString() {
        return "BillType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
