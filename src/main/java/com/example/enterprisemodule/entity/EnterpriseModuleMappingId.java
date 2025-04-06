package com.example.enterprisemodule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class EnterpriseModuleMappingId implements Serializable {
    private static final long serialVersionUID = 3005545778191651690L;
    @NotNull
    @Column(name = "enterprise_id", nullable = false)
    private UUID enterpriseId;

    @NotNull
    @Column(name = "module_master_id", nullable = false)
    private Integer moduleMasterId;

    @NotNull
    @Column(name = "sub_module_id", nullable = false)
    private Integer subModuleId;
}