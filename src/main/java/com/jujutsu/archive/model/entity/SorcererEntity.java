package com.jujutsu.archive.model.entity;

import com.jujutsu.archive.enums.SorcererRank;
import jakarta.persistence.*;

/**
 * JPA сущность заклинателя (Many в связи 1:N с миссией)
 */
@Entity
@Table(name = "sorcerers")
public class SorcererEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    private SorcererRank rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private MissionEntity mission;

    public SorcererEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public SorcererRank getRank() { return rank; }
    public void setRank(SorcererRank rank) { this.rank = rank; }

    public MissionEntity getMission() { return mission; }
    public void setMission(MissionEntity mission) { this.mission = mission; }
}