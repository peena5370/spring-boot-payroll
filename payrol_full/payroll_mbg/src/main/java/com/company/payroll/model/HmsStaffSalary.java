package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class HmsStaffSalary {
    private Long id;

    private BigDecimal monthlySalary;

    private BigDecimal annualSalary;

    private LocalDate updateDate;

    private Long staffId;
}