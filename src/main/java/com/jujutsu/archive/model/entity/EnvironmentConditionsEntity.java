package com.jujutsu.archive.model.entity;

import com.jujutsu.archive.enums.TimeOfDay;
import com.jujutsu.archive.enums.Visibility;
import com.jujutsu.archive.enums.Weather;
import jakarta.persistence.*;

/**
 * JPA сущность условий окружающей среды (1:1 с миссией, необязательная)
 */
@Entity
@Table(name = "environment_conditions")
public class EnvironmentConditionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
    private Weather weather;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private Visibility visibility;

    @Column(name = "cursed_energy_density")
    private Double cursedEnergyDensity;

    public EnvironmentConditionsEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Weather getWeather() { return weather; }
    public void setWeather(Weather weather) { this.weather = weather; }

    public TimeOfDay getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(TimeOfDay timeOfDay) { this.timeOfDay = timeOfDay; }

    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }

    public Double getCursedEnergyDensity() { return cursedEnergyDensity; }
    public void setCursedEnergyDensity(Double cursedEnergyDensity) { this.cursedEnergyDensity = cursedEnergyDensity; }
}