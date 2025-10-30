package com.MealMonitor.pollservice.repository;

import com.MealMonitor.pollservice.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, String> {
    Optional<Vote> findByUserIdAndPollId(String userId, String pollId);
    long countByPollIdAndOptionId(String pollId, String optionId);
}


