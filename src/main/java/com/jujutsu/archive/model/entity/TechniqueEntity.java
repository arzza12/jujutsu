package com.jujutsu.archive.model.entity;

import com.jujutsu.archive.enums.TechniqueType;
import jakarta.persistence.*;

/**
 * JPA сущность проклятой техники (Many в связи 1:N с миссией)
 */
@Entity
@Table(name = "techniques")
public class TechniqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TechniqueType type;

    @Column(name = "owner")
    private String owner;

    @Column(name = "damage")
    private Double damage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private MissionEntity mission;

    public TechniqueEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TechniqueType getType() { return type; }
    public void setType(TechniqueType type) { this.type = type; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public Double getDamage() { return damage; }
    public void setDamage(Double damage) { this.damage = damage; }

    public MissionEntity getMission() { return mission; }
    public void setMission(MissionEntity mission) { this.mission = mission; }
}