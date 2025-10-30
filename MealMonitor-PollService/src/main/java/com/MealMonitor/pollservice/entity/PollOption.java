package com.MealMonitor.pollservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "poll_options")
public class PollOption {
    @Id
    @Column(name = "option_id", length = 50)
    private String optionId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "poll_id")
    private String pollId;

    @NotBlank
    @Size(max = 500)
    @Column(name = "option_text")
    private String optionText;

    // Constructors
    public PollOption() {}

    public PollOption(String optionId, String pollId, String optionText) {
        this.optionId = optionId;
        this.pollId = pollId;
        this.optionText = optionText;
    }

    // Getters and Setters
    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
