package com.jujutsu.archive.model.entity;

import com.jujutsu.archive.enums.PublicExposureRisk;
import jakarta.persistence.*;

/**
 * JPA сущность влияния на мирное население (1:1 с миссией, необязательная)
 */
@Entity
@Table(name = "civilian_impacts")
public class CivilianImpactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "evacuated")
    private Integer evacuated;

    @Column(name = "injured")
    private Integer injured;

    @Column(name = "missing")
    private Integer missing;

    @Enumerated(EnumType.STRING)
    @Column(name = "public_exposure_risk")
    private PublicExposureRisk publicExposureRisk;

    public CivilianImpactEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getEvacuated() { return evacuated; }
    public void setEvacuated(Integer evacuated) { this.evacuated = evacuated; }

    public Integer getInjured() { return injured; }
    public void setInjured(Integer injured) { this.injured = injured; }

    public Integer getMissing() { return missing; }
    public void setMissing(Integer missing) { this.missing = missing; }

    public PublicExposureRisk getPublicExposureRisk() { return publicExposureRisk; }
    public void setPublicExposureRisk(PublicExposureRisk publicExposureRisk) { this.publicExposureRisk = publicExposureRisk; }
}
