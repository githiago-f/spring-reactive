package com.example.siteservice.entity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface SiteRepository extends ReactiveCrudRepository<Site, Integer> {
    Flux<Site> findByDomain(String domain);
}
