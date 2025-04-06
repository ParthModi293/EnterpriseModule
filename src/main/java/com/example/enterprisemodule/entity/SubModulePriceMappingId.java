package com.example.enterprisemodule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SubModulePriceMappingId implements Serializable {
    private static final long serialVersionUID = 7595039062316514303L;
    @NotNull
    @Column(name = "sub_module_id", nullable = false)
    private Integer subModuleId;

    @NotNull
    @Column(name = "time_period", nullable = false, length = 50)
    private String timePeriod;

    @Override
    public int hashCode() {
        return Objects.hash(subModuleId, timePeriod);
    }

}