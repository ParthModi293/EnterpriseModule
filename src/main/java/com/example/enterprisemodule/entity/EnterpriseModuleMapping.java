package com.example.enterprisemodule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "enterprise_module_mapping", schema = "enterprise_data")
public class EnterpriseModuleMapping {
    @EmbeddedId
    private EnterpriseModuleMappingId id;

    @Column(name = "created_at")
    private Instant createdAt;

}