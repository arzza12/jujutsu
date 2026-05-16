package com.jujutsu.archive.model.dto;

import com.jujutsu.archive.enums.BehaviorType;
import com.jujutsu.archive.enums.EscalationRisk;
import com.jujutsu.archive.enums.Mobility;

import java.util.List;

/**
 * DTO активности противника (EnemyActivity)
 */
public class EnemyActivityDto {

    // Тип поведения противника
    private BehaviorType behaviorType;

    // Приоритетные цели
    private List<String> targetPriority;

    // Паттерны атак
    private List<String> attackPatterns;

    // Использованные контрмеры противника
    private List<String> countermeasuresUsed;

    // Мобильность противника
    private Mobility mobility;

    // Риск эскалации
    private EscalationRisk escalationRisk;

    public EnemyActivityDto() {}

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
