package com.jujutsu.archive.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "economic_assessments")
public class EconomicAssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_damage_cost")
    private Double totalDamageCost;

    @Column(name = "infrastructure_damage")
    private Double infrastructureDamage;

    @Column(name = "commercial_damage")
    private Double commercialDamage;

    @Column(name = "transport_damage")
    private Double transportDamage;

    @Column(name = "recovery_estimate_days")
    private Double recoveryEstimateDays;

    @Column(name = "insurance_covered")
    private Boolean insuranceCovered;

    public EconomicAssessmentEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getTotalDamageCost() { return totalDamageCost; }
    public void setTotalDamageCost(Double totalDamageCost) { this.totalDamageCost = totalDamageCost; }

    public Double getInfrastructureDamage() { return infrastructureDamage; }
    public void setInfrastructureDamage(Double infrastructureDamage) { this.infrastructureDamage = infrastructureDamage; }

    public Double getCommercialDamage() { return commercialDamage; }
    public void setCommercialDamage(Double commercialDamage) { this.commercialDamage = commercialDamage; }

    public Double getTransportDamage() { return transportDamage; }
    public void setTransportDamage(Double transportDamage) { this.transportDamage = transportDamage; }

    public Double getRecoveryEstimateDays() { return recoveryEstimateDays; }
    public void setRecoveryEstimateDays(Double recoveryEstimateDays) { this.recoveryEstimateDays = recoveryEstimateDays; }

    public Boolean getInsuranceCovered() { return insuranceCovered; }
    public void setInsuranceCovered(Boolean insuranceCovered) { this.insuranceCovered = insuranceCovered; }
}