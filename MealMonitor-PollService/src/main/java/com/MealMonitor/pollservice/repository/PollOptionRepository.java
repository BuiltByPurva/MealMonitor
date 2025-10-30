package com.MealMonitor.pollservice.repository;

import com.MealMonitor.pollservice.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollOptionRepository extends JpaRepository<PollOption, String> {
    List<PollOption> findByPollId(String pollId);
}


