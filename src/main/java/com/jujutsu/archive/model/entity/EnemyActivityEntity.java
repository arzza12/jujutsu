package com.jujutsu.archive.model.entity;

import com.jujutsu.archive.enums.BehaviorType;
import com.jujutsu.archive.enums.EscalationRisk;
import com.jujutsu.archive.enums.Mobility;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "enemy_activities")
public class EnemyActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "behavior_type")
    private BehaviorType behaviorType;

    @ElementCollection
    @CollectionTable(name = "enemy_target_priorities", joinColumns = @JoinColumn(name = "enemy_activity_id"))
    @Column(name = "target")
    private List<String> targetPriority;

    @ElementCollection
    @CollectionTable(name = "enemy_attack_patterns", joinColumns = @JoinColumn(name = "enemy_activity_id"))
    @Column(name = "pattern")
    private List<String> attackPatterns;

    // Контрмеры противника
    @ElementCollection
    @CollectionTable(name = "enemy_countermeasures", joinColumns = @JoinColumn(name = "enemy_activity_id"))
    @Column(name = "measure")
    private List<String> countermeasuresUsed;

    @Enumerated(EnumType.STRING)
    @Column(name = "mobility")
    private Mobility mobility;

    @Enumerated(EnumType.STRING)
    @Column(name = "escalation_risk")
    private EscalationRisk escalationRisk;

    public EnemyActivityEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BehaviorType getBehaviorType() { return behaviorType; }
    public void setBehaviorType(BehaviorType behaviorType) { this.behaviorType = behaviorType; }

    public List<String> getTargetPriority() { return targetPriority; }
    public void setTargetPriority(List<String> targetPriority) { this.targetPriority = targetPriority; }

    public List<String> getAttackPatterns() { return attackPatterns; }
    public void setAttackPatterns(List<String> attackPatterns) { this.attackPatterns = attackPatterns; }

    public List<String> getCountermeasuresUsed() { return countermeasuresUsed; }
    public void setCountermeasuresUsed(List<String> countermeasuresUsed) { this.countermeasuresUsed = countermeasuresUsed; }

    public Mobility getMobility() { return mobility; }
    public void setMobility(Mobility mobility) { this.mobility = mobility; }

    public EscalationRisk getEscalationRisk() { return escalationRisk; }
    public void setEscalationRisk(EscalationRisk escalationRisk) { this.escalationRisk = escalationRisk; }
}