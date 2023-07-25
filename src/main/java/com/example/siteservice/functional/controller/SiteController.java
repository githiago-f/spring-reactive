package com.example.siteservice.functional.controller;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.siteservice.entity.Site;
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

    private Mono<ServerResponse> toResponse(Optional<Site> site) {
        log.info("Mono is empty: {}", site);
        if(site.isEmpty()) {
            return ServerResponse.status(404)
                .body(BodyInserters.fromValue("Site not found"));
        }
        return ServerResponse.ok().body(BodyInserters.fromValue(site.get()));
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        log.info("check list of sites on database");
        return siteRepository.findAll().flatMap(
            sites -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(sites))
        );
    }

    public Mono<ServerResponse> view(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        log.info("Viewing site of id: {}", id);
        return siteRepository.findById(id)
            .flatMap(this::toResponse);
    }
}
