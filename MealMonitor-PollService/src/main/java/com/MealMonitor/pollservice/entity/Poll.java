package com.MealMonitor.pollservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @Column(name = "poll_id", length = 50)
    private String pollId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "created_by_user_id")
    private String createdByUserId;

    @NotBlank
    @Size(max = 500)
    @Column(name = "question")
    private String question;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Poll() {}

    public Poll(String pollId, String createdByUserId, String question) {
        this.pollId = pollId;
        this.createdByUserId = createdByUserId;
        this.question = question;
    }

    // Getters and Setters
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
