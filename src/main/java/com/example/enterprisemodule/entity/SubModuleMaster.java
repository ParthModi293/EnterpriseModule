package com.example.enterprisemodule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class SubModuleMaster {

    @Id
    private int id;
    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private ModuleMaster moduleId;
}
