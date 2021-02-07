package com.github.data.analytics.controller;

import com.github.data.analytics.service.GithubDataAnalyticsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class GithubDataAnalyticsController {

    private GithubDataAnalyticsService githubDataAnalyticsService;

    public GithubDataAnalyticsController(GithubDataAnalyticsService githubDataAnalyticsService) {
        this.githubDataAnalyticsService = githubDataAnalyticsService;
    }

    @GetMapping("/search/repositories")
    public String getRepositories(
            @RequestParam String keyword,
            @RequestHeader(required = false) String accept,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer per_page){
        return this.githubDataAnalyticsService.getRepositories(keyword, accept, sort, order, page, per_page);
    }

    @GetMapping("/{owner}/{repo}/contributors")
    public String getContributors(@RequestHeader(required = false) String accept,
                                  @PathVariable String owner,
                                  @PathVariable String repo){
        return this.githubDataAnalyticsService.getContributors(accept, owner, repo);
    }

    @GetMapping("/{owner}/{repo}/commits")
    public String getCommits(@RequestHeader(required = false) String accept,
                                  @PathVariable String owner,
                                  @PathVariable String repo){
        return this.githubDataAnalyticsService.getCommits(accept, owner, repo);
    }

}
