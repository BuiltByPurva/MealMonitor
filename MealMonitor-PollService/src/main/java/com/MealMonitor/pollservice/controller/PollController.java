package com.MealMonitor.pollservice.controller;

import com.MealMonitor.pollservice.entity.Poll;
import com.MealMonitor.pollservice.entity.PollOption;
import com.MealMonitor.pollservice.entity.Vote;
import com.MealMonitor.pollservice.repository.PollOptionRepository;
import com.MealMonitor.pollservice.repository.PollRepository;
import com.MealMonitor.pollservice.repository.VoteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/polls")
public class PollController {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final VoteRepository voteRepository;

    public PollController(PollRepository pollRepository,
                          PollOptionRepository pollOptionRepository,
                          VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.pollOptionRepository = pollOptionRepository;
        this.voteRepository = voteRepository;
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@Valid @RequestBody Poll poll) {
        if (poll.getPollId() == null || poll.getPollId().isBlank()) {
            poll.setPollId(UUID.randomUUID().toString());
        }
        Poll saved = pollRepository.save(poll);
        return ResponseEntity.created(URI.create("/polls/" + saved.getPollId())).body(saved);
    }

    @GetMapping
    public List<Poll> listPolls() {
        return pollRepository.findAll();
    }

    @PostMapping("/{pollId}/options")
    public ResponseEntity<PollOption> addOption(@PathVariable String pollId, @Valid @RequestBody PollOption option) {
        option.setOptionId(option.getOptionId() == null || option.getOptionId().isBlank() ? UUID.randomUUID().toString() : option.getOptionId());
        option.setPollId(pollId);
        PollOption saved = pollOptionRepository.save(option);
        return ResponseEntity.created(URI.create("/polls/" + pollId + "/options/" + saved.getOptionId())).body(saved);
    }

    @GetMapping("/{pollId}/results")
    public Map<String, Long> getResults(@PathVariable String pollId) {
        return pollOptionRepository.findByPollId(pollId).stream().collect(
                java.util.stream.Collectors.toMap(
                        PollOption::getOptionId,
                        option -> voteRepository.countByPollIdAndOptionId(pollId, option.getOptionId())
                )
        );
    }

    @PostMapping("/{pollId}/vote")
    public ResponseEntity<?> submitVote(@PathVariable String pollId, @Valid @RequestBody Vote vote) {
        // Check if user has already voted
        Optional<Vote> existingVote = voteRepository.findByUserIdAndPollId(vote.getUserId(), pollId);
        if (existingVote.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "User has already voted on this poll"));
        }

        // Verify option belongs to poll
        Optional<PollOption> option = pollOptionRepository.findById(vote.getOptionId());
        if (option.isEmpty() || !option.get().getPollId().equals(pollId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid option for this poll"));
        }

        // Create and save vote
        vote.setPollId(pollId);
        if (vote.getVoteId() == null || vote.getVoteId().isBlank()) {
            vote.setVoteId(UUID.randomUUID().toString());
        }
        Vote saved = voteRepository.save(vote);
        return ResponseEntity.created(URI.create("/polls/" + pollId + "/vote/" + saved.getVoteId())).body(saved);
    }

    @GetMapping("/active")
    public List<Poll> getActivePolls() {
        return pollRepository.findAll().stream()
                .filter(poll -> Boolean.TRUE.equals(poll.getIsActive()))
                .toList();
    }
}


