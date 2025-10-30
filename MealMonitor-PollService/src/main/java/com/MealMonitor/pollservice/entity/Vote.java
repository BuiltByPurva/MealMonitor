package com.MealMonitor.pollservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @Column(name = "vote_id", length = 50)
    private String voteId;

    @Column(name = "voted_at")
    private LocalDateTime votedAt = LocalDateTime.now();

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_id")
    private String userId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "poll_id")
    private String pollId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "option_id")
    private String optionId;

    // Constructors
    public Vote() {}

    public Vote(String voteId, String userId, String pollId, String optionId) {
        this.voteId = voteId;
        this.userId = userId;
        this.pollId = pollId;
        this.optionId = optionId;
    }

    // Getters and Setters
    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(LocalDateTime votedAt) {
        this.votedAt = votedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }
}
