package com.MealMonitor.pollservice.controller;

import com.MealMonitor.pollservice.entity.Poll;
import com.MealMonitor.pollservice.entity.PollOption;
import com.MealMonitor.pollservice.repository.PollOptionRepository;
import com.MealMonitor.pollservice.repository.PollRepository;
import com.MealMonitor.pollservice.repository.VoteRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
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
}


