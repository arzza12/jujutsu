package com.jujutsu.archive.model.dto;

/**
 * DTO экономической оценки ущерба (EconomicAssessment)
 */
public class EconomicAssessmentDto {

    // Общий ущерб
    private Double totalDamageCost;

    // Ущерб инфраструктуре
    private Double infrastructureDamage;

    // Коммерческий ущерб
    private Double commercialDamage;

    // Транспортный ущерб
    private Double transportDamage;

    // Оценочное время восстановления в днях
    private Double recoveryEstimateDays;

    // Покрыто страховкой
    private Boolean insuranceCovered;

    public EconomicAssessmentDto() {}

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