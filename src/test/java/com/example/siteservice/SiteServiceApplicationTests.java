package com.example.siteservice;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.siteservice.entity.Site;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SiteServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	@DisplayName("Can view list of all sites")
	void canViewListOfSites() {
		webTestClient.get().uri("/sites")
			.exchange().expectStatus().isOk()
			.expectBody(Set.class).value(sites -> {
				assertThat(sites.size()).isEqualTo(4);
			});
	}

	@Test
	void viewSiteById() {
		webTestClient.get().uri("/sites/{id}", Map.of("id", "1"))
			.exchange().expectStatus().isOk()
			.expectBody(Site.class).value(site -> {
				assertThat(site.getId()).isEqualTo(1L);
			});
	}
}
