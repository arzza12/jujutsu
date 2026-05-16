package com.jujutsu.archive.model.dto;

import com.jujutsu.archive.enums.TimeOfDay;
import com.jujutsu.archive.enums.Visibility;
import com.jujutsu.archive.enums.Weather;

/**
 * DTO условий окружающей среды (EnvironmentConditions)
 */
public class EnvironmentConditionsDto {

    // Погода
    private Weather weather;

    // Время суток
    private TimeOfDay timeOfDay;

    // Видимость
    private Visibility visibility;

    // Плотность проклятой энергии
    private Double cursedEnergyDensity;

    public EnvironmentConditionsDto() {}

    public Weather getWeather() { return weather; }
    public void setWeather(Weather weather) { this.weather = weather; }

    public TimeOfDay getTimeOfDay() { return timeOfDay; }
    public void setTimeOfDay(TimeOfDay timeOfDay) { this.timeOfDay = timeOfDay; }

    public Visibility getVisibility() { return visibility; }
    public void setVisibility(Visibility visibility) { this.visibility = visibility; }

    public Double getCursedEnergyDensity() { return cursedEnergyDensity; }
    public void setCursedEnergyDensity(Double cursedEnergyDensity) { this.cursedEnergyDensity = cursedEnergyDensity; }
}