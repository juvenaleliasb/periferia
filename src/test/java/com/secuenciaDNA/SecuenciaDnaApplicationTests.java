package com.secuenciaDNA;

import com.secuenciaDNA.models.Stats;
import com.secuenciaDNA.services.SecuenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;


import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecuenciaDnaApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void dnaTest() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		Path jsonFilePath = Path.of(classLoader.getResource("dna_test.json").getFile());
		String jsonContent = Files.readString(jsonFilePath, StandardCharsets.UTF_8);

		String resp = mvc.perform(
						post("/mutants/mutant")
								.contentType(MediaType.APPLICATION_JSON)
								.content(jsonContent)
				)
				.andExpect(status().isOk())
				.andExpect(
						content().string(
								containsString("true")))
				.andReturn()
				.getResponse().getContentAsString();

	}

	@Test
	void dnaTestForbidden() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		Path jsonFilePath = Path.of(classLoader.getResource("dna_test_forbidden.json").getFile());
		String jsonContent = Files.readString(jsonFilePath, StandardCharsets.UTF_8);

		mvc.perform(
						post("/mutants/mutant")
								.contentType(MediaType.APPLICATION_JSON)
								.content(jsonContent)
				)
				.andExpect(status().isForbidden());

	}

	@Test
	void adnStatsTest() throws Exception {

		Stats mockDnaStats = new Stats() {
			@Override
			public Long getChecks() {
				return 0L;
			}

			@Override
			public Long getMutants() {
				return 0L;
			}

			@Override
			public Double getPercent() {
				return 0.0;
			}
		};

		mvc.perform(get("/mutants/stats")
						.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andReturn()
				.getResponse().getContentType();

	}

	@Test
	void dnaRTest() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		Path jsonFilePath = Path.of(Objects.requireNonNull(classLoader.getResource("dna_test.json")).toURI());
		String jsonContent = Files.readString(jsonFilePath, StandardCharsets.UTF_8);

		webTestClient.post()
				.uri("/mutants/mutant")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(jsonContent)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(String.class)
				.isEqualTo("true");
	}

}
