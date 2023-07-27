package com.example.siteservice.entity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SiteRepository extends ReactiveCrudRepository<Site, Integer> {
    Mono<Site> findOneByDomain(String domain);
    Flux<Site> findByAlias(String alias);
}
