package com.example.enterprisemodule.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ModuleResponse {
    private Integer moduleMasterId;
    private String moduleName;
    private String description;
    private Boolean isDefault;
    private List<SubModuleResponse> subModules;
    private BigDecimal totalPrice;


}