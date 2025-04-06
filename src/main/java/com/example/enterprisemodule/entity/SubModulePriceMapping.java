package com.example.enterprisemodule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "sub_module_price_mapping", schema = "public")
public class SubModulePriceMapping {
    @EmbeddedId
    private SubModulePriceMappingId id;

    @MapsId("timePeriod")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "time_period", nullable = false)
    private BillingTimePeriod timePeriod;

    @NotNull
    @Column(name = "total_months", nullable = false)
    private Integer totalMonths;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}