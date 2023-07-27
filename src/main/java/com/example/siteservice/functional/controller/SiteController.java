package com.example.siteservice.functional.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.siteservice.entity.SiteRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component(value = "functionalSiteController")
public class SiteController {
    private SiteRepository siteRepository;

    public SiteController(SiteRepository siteRepository) {
        log.debug("Instantiation of SiteController");
        this.siteRepository = siteRepository;
    }

    public Mono<ServerResponse> view(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id"));
        log.info("Viewing site of id: {}", id);
        return siteRepository.findById(id)
            .flatMap(site -> ServerResponse.ok().body(BodyInserters.fromValue(site)))
            .switchIfEmpty(ServerResponse.notFound().build());
    }
}
