package com.jujutsu.archive.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * JPA сущность события хронологии операции (Many в связи 1:N с миссией)
 */
@Entity
@Table(name = "operation_timeline_events")
public class OperationTimelineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "type")
    private String type;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private MissionEntity mission;

    public OperationTimelineEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MissionEntity getMission() { return mission; }
    public void setMission(MissionEntity mission) { this.mission = mission; }
}