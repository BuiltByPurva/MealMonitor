package com.MealMonitor.reviewservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column(name = "media_id", length = 50)
    private String mediaId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "review_id")
    private String reviewId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "media_type")
    private String mediaType;

    @NotBlank
    @Size(max = 500)
    @Column(name = "media_url")
    private String mediaURL;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt = LocalDateTime.now();

    // Constructors
    public Media() {}

    public Media(String mediaId, String reviewId, String mediaType, String mediaURL) {
        this.mediaId = mediaId;
        this.reviewId = reviewId;
        this.mediaType = mediaType;
        this.mediaURL = mediaURL;
    }

    // Getters and Setters
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
