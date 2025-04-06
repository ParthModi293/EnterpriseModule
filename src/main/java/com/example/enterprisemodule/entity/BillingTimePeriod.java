package com.example.enterprisemodule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "billing_time_period")
public class BillingTimePeriod {
    @Id
    @Size(max = 50)
    @Column(name = "time_period", nullable = false, length = 50)
    private String timePeriod;

    @NotNull
    @Column(name = "total_months", nullable = false)
    private Integer totalMonths;

    //TODO [Reverse Engineering] generate columns from DB
}