package com.jujutsu.archive.model.dto;

import com.jujutsu.archive.enums.TechniqueType;

/**
 * DTO проклятой техники (Technique)
 */
public class TechniqueDto {

    // Название техники
    private String name;

    // Тип техники
    private TechniqueType type;

    // Владелец техники
    private String owner;

    // Урон техники
    private Double damage;

    public TechniqueDto() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TechniqueType getType() { return type; }
    public void setType(TechniqueType type) { this.type = type; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public Double getDamage() { return damage; }
    public void setDamage(Double damage) { this.damage = damage; }
}