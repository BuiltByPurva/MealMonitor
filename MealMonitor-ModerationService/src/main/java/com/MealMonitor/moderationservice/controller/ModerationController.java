package com.MealMonitor.moderationservice.controller;

import com.MealMonitor.moderationservice.entity.FlaggedContent;
import com.MealMonitor.moderationservice.entity.IssueReport;
import com.MealMonitor.moderationservice.entity.ModerationLog;
import com.MealMonitor.moderationservice.entity.ResolutionUpdate;
import com.MealMonitor.moderationservice.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/moderation")
public class ModerationController {

    private final FlaggedContentRepository flaggedContentRepository;
    private final IssueReportRepository issueReportRepository;
    private final ModerationLogRepository moderationLogRepository;
    private final ResolutionUpdateRepository resolutionUpdateRepository;

    public ModerationController(FlaggedContentRepository flaggedContentRepository,
                                IssueReportRepository issueReportRepository,
                                ModerationLogRepository moderationLogRepository,
                                ResolutionUpdateRepository resolutionUpdateRepository) {
        this.flaggedContentRepository = flaggedContentRepository;
        this.issueReportRepository = issueReportRepository;
        this.moderationLogRepository = moderationLogRepository;
        this.resolutionUpdateRepository = resolutionUpdateRepository;
    }

    @PostMapping("/flags")
    public ResponseEntity<FlaggedContent> createFlag(@Valid @RequestBody FlaggedContent flag) {
        if (flag.getFlagId() == null || flag.getFlagId().isBlank()) {
            flag.setFlagId(UUID.randomUUID().toString());
        }
        FlaggedContent saved = flaggedContentRepository.save(flag);
        return ResponseEntity.created(URI.create("/moderation/flags/" + saved.getFlagId())).body(saved);
    }

    @GetMapping("/flags")
    public List<FlaggedContent> listFlags(@RequestParam(required = false) String status) {
        if (status == null || status.isBlank()) {
            return flaggedContentRepository.findAll();
        }
        return flaggedContentRepository.findByStatus(status);
    }

    @PostMapping("/issues")
    public ResponseEntity<IssueReport> createIssue(@Valid @RequestBody IssueReport issue) {
        if (issue.getIssueId() == null || issue.getIssueId().isBlank()) {
            issue.setIssueId(UUID.randomUUID().toString());
        }
        IssueReport saved = issueReportRepository.save(issue);
        return ResponseEntity.created(URI.create("/moderation/issues/" + saved.getIssueId())).body(saved);
    }

    @GetMapping("/issues")
    public List<IssueReport> listIssues(@RequestParam(required = false) String status) {
        if (status == null || status.isBlank()) {
            return issueReportRepository.findAll();
        }
        return issueReportRepository.findByStatus(status);
    }

    @PostMapping("/issues/{issueId}/resolutions")
    public ResponseEntity<ResolutionUpdate> addResolution(@PathVariable String issueId, @Valid @RequestBody ResolutionUpdate resolution) {
        if (resolution.getResolutionId() == null || resolution.getResolutionId().isBlank()) {
            resolution.setResolutionId(UUID.randomUUID().toString());
        }
        resolution.setIssueId(issueId);
        ResolutionUpdate saved = resolutionUpdateRepository.save(resolution);
        return ResponseEntity.created(URI.create("/moderation/issues/" + issueId + "/resolutions/" + saved.getResolutionId())).body(saved);
    }

    @PostMapping("/logs")
    public ResponseEntity<ModerationLog> addLog(@Valid @RequestBody ModerationLog log) {
        if (log.getLogId() == null || log.getLogId().isBlank()) {
            log.setLogId(UUID.randomUUID().toString());
        }
        ModerationLog saved = moderationLogRepository.save(log);
        return ResponseEntity.created(URI.create("/moderation/logs/" + saved.getLogId())).body(saved);
    }
}


