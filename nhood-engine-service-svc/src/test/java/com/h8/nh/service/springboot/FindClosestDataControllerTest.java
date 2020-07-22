package com.h8.nh.service.springboot;

import com.h8.nh.service.port.webflux.ClosestData;
import com.h8.nh.service.port.webflux.ClosestDataFinderWebFluxAdapter;
import com.h8.nh.service.springboot.controller.FindClosestDataController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(FindClosestDataController.class)
class FindClosestDataControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	private ClosestDataFinderWebFluxAdapter fluxAdapter;

	@Test
	void shouldFindClosestDataById() {
		var id = 1;
		var dto = new ClosestData(id);

		Mono<ClosestData> mono = Mono.just(dto);

		Mockito.when(fluxAdapter.findByID(id)).thenReturn(mono);

		webTestClient.get()
				.uri("/" + id)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(ClosestData.class)
				.value(ClosestData::getId, Matchers.equalTo(id));

		Mockito.verify(fluxAdapter, Mockito.times(1)).findByID(id);
	}
}
