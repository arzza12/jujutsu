package com.jujutsu.archive.model.dto;

import java.time.LocalDateTime;

/**
 * DTO события хронологии операции (OperationTimeline)
 */
public class OperationTimelineDto {

    // Временная метка события
    private LocalDateTime timestamp;

    // Тип события
    private String type;

    // Описание события
    private String description;

    public OperationTimelineDto() {}

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
