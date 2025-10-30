package com.MealMonitor.pollservice.repository;

import com.MealMonitor.pollservice.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, String> {
}


