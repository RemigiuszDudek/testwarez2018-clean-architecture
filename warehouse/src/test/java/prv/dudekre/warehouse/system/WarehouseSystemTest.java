package prv.dudekre.warehouse.system;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import prv.dudekre.warehouse.infra.controller.warehouse.ProductPlacementRequest;
import prv.dudekre.warehouse.util.WarehouseEntryForTest;

import java.io.File;
import java.net.URI;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;
import static prv.dudekre.warehouse.system.WarehouseSystemTest.PRODUCT_SERVICE_PORT;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(
        webEnvironment = DEFINED_PORT,
        properties = {"prv.dudekre.product-service.port=" + PRODUCT_SERVICE_PORT}
)
@AutoConfigureStubRunner(
        ids = "prv.dudekre:product-service-contract:0.0.1-SNAPSHOT:stubs:" + PRODUCT_SERVICE_PORT,
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@SuppressWarnings("PMD.TooManyStaticImports")
public class WarehouseSystemTest {
    static final String PRODUCT_SERVICE_PORT = "9999";
    @Value("${server.port}") private String port;
    @Value("${prv.dudekre.repository.rootDir}") private String repositoryRootDir;
    private final RestTemplate restTemplate = new RestTemplate();
    private URI uri;

    @Before
    public void setup() {
        stream(requireNonNull(new File(repositoryRootDir).listFiles())).forEach(File::delete);
        uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(parseInt(port))
                .build().toUri();
    }

    @Test
    public void shouldReturnProductsThatWerePreviouslyAdded() {
        List<ProductPlacementRequest> requestBody = of(
                new ProductPlacementRequest("123", 1),
                new ProductPlacementRequest("123", 1)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        HttpEntity<List<ProductPlacementRequest>> entity = new HttpEntity<>(requestBody, headers);
        restTemplate.put(fromUri(uri).path("products").build().toUri(), entity);

        ResponseEntity<List<WarehouseEntryForTest>> result = restTemplate.exchange(
                fromUri(uri).path("content").build().toUri(),
                GET,
                null,
                new ParameterizedTypeReference<List<WarehouseEntryForTest>>() {
                });
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(of(new WarehouseEntryForTest("123", 2)));
    }
}
