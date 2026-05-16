package com.jujutsu.archive.model.dto;

import com.jujutsu.archive.enums.SorcererRank;

/**
 * DTO заклинателя (Sorcerer)
 */
public class SorcererDto {

    // Имя заклинателя
    private String name;

    // Ранг заклинателя
    private SorcererRank rank;

    public SorcererDto() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public SorcererRank getRank() { return rank; }
    public void setRank(SorcererRank rank) { this.rank = rank; }
}