package com.github.data.analytics.service;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GithubDataAnalyticsService {

    private static final String GITHUB_REPOSITORIES_URI = "https://api.github.com/search/repositories";
    private static final String GITHUB_CONTRIBUTORS_URI_PATTERN = "https://api.github.com/repos/%s/%s/contributors";
    private static final String GITHUB_COMMITS_URI_PATTERN = "https://api.github.com/repos/%s/%s/commits";

    public String getRepositories(String keyword, String accept, String sort, String order, Integer page, Integer perPage){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GITHUB_REPOSITORIES_URI)
                .queryParam("q", keyword)
                .queryParam("sort", sort)
                .queryParam("order", order)
                .queryParam("page", page)
                .queryParam("per_page", perPage);

        RequestEntity<Void> requestEntity = accept == null ?
                RequestEntity.get(builder.build().toUri()).build() :
                RequestEntity.get(builder.build().toUri()).accept(MediaType.valueOf(accept)).build();

        return restTemplate.exchange(requestEntity, String.class).getBody();
    }

    public String getContributors(String accept, String owner, String repo){
        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format(GITHUB_CONTRIBUTORS_URI_PATTERN, owner, repo);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        RequestEntity<Void> requestEntity = accept == null ?
                RequestEntity.get(builder.build().toUri()).build() :
                RequestEntity.get(builder.build().toUri()).accept(MediaType.valueOf(accept)).build();
        return restTemplate.exchange(requestEntity, String.class).getBody();
    }

    public String getCommits(String accept, String owner, String repo){
        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format(GITHUB_COMMITS_URI_PATTERN, owner, repo);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        RequestEntity<Void> requestEntity = accept == null ?
                RequestEntity.get(builder.build().toUri()).build() :
                RequestEntity.get(builder.build().toUri()).accept(MediaType.valueOf(accept)).build();
        return restTemplate.exchange(requestEntity, String.class).getBody();
    }

}
