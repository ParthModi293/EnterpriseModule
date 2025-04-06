package com.example.enterprisemodule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ModuleMaster {

    @Id
    private int id;
    private String name;
    private String description;
    private boolean isDefault;
}
