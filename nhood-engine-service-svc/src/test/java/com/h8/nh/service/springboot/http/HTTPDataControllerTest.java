package com.h8.nh.service.springboot.http;

import com.h8.nh.service.dto.EngineDataDTO;
import com.h8.nh.service.port.webflux.AddDataRequestHandler;
import com.h8.nh.service.port.webflux.FindDataRequestHandler;
import com.h8.nh.service.port.webflux.WebFluxAPIException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebFluxTest(HTTPDataController.class)
class HTTPDataControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	private AddDataRequestHandler addDataRequestHandler;

	@MockBean
	private FindDataRequestHandler findDataRequestHandler;

	@Test
	void shouldCallAddDataCommand()
			throws WebFluxAPIException {
		var reference = "URL";
		var dto = new EngineDataDTO(new String[]{"0", "0", "0"}, reference);

		var uuid = UUID.randomUUID();
		Mono<UUID> mono = Mono.just(uuid);
		Mockito.when(addDataRequestHandler.add(dto)).thenReturn(mono);

		webTestClient.post()
				.uri("/data")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(dto))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().valueEquals("location", "/data/" + uuid.toString());

		Mockito.verify(addDataRequestHandler, Mockito.times(1)).add(dto);
	}

	@Test
	void shouldCallFindClosestDataQuery()
			throws WebFluxAPIException {
		var reference = "URL";
		var uuid = UUID.randomUUID();
		var dto = new EngineDataDTO(uuid, new String[]{"0", "0", "0"}, reference);

		var size = 1;

		Flux<EngineDataDTO> mono = Flux.just(dto);
		Mockito.when(findDataRequestHandler.find(dto, 1)).thenReturn(mono);

		webTestClient.post()
				.uri("/find?size=" + size)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(dto))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(EngineDataDTO.class)
				.contains(dto);

		Mockito.verify(findDataRequestHandler, Mockito.times(1)).find(dto, size);
	}

	@Test
	void shouldNotAcceptZeroResultSize()
			throws WebFluxAPIException {
		var reference = "URL";
		var dto = new EngineDataDTO(new String[]{"0", "0", "0"}, reference);

		var size = 0;

		webTestClient.post()
				.uri("/find?size=" + size)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(dto))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isBadRequest();

		Mockito.verify(findDataRequestHandler, Mockito.times(0)).find(dto, size);
	}

	@Test
	void shouldNotAcceptNegativeResultSize()
			throws WebFluxAPIException {
		var reference = "URL";
		var dto = new EngineDataDTO(new String[]{"0", "0", "0"}, reference);

		var size = -1;

		webTestClient.post()
				.uri("/find?size=" + size)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(dto))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isBadRequest();

		Mockito.verify(findDataRequestHandler, Mockito.times(0)).find(dto, size);
	}
}
