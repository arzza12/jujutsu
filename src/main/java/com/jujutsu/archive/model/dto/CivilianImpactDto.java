package com.jujutsu.archive.model.dto;

import com.jujutsu.archive.enums.PublicExposureRisk;

/**
 * DTO влияния на мирное население (CivilianImpact)
 */
public class CivilianImpactDto {

    // Количество эвакуированных
    private Integer evacuated;

    // Количество пострадавших
    private Integer injured;

    // Количество пропавших без вести
    private Integer missing;

    // Риск огласки среди населения
    private PublicExposureRisk publicExposureRisk;

    public CivilianImpactDto() {}

    public Integer getEvacuated() { return evacuated; }
    public void setEvacuated(Integer evacuated) { this.evacuated = evacuated; }

    public Integer getInjured() { return injured; }
    public void setInjured(Integer injured) { this.injured = injured; }

    public Integer getMissing() { return missing; }
    public void setMissing(Integer missing) { this.missing = missing; }

    public PublicExposureRisk getPublicExposureRisk() { return publicExposureRisk; }
    public void setPublicExposureRisk(PublicExposureRisk publicExposureRisk) { this.publicExposureRisk = publicExposureRisk; }
}