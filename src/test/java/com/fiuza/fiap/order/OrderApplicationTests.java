package com.fiuza.fiap.order;

import com.fiuza.fiap.order.infra.feign.customer.FeignCustomerClient;
import com.fiuza.fiap.order.infra.feign.payment.FeignPaymentClient;
import com.fiuza.fiap.order.infra.feign.stock.FeignStockClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.kafka.bootstrap-servers=localhost:9092",
		"spring.kafka.listener.auto-startup=false",
		"springdoc.api-docs.enabled=false",
		"springdoc.swagger-ui.enabled=false"
})
class OrderApplicationTests {
	@MockBean
	private FeignCustomerClient  feignCustomerClient;

	@MockBean
	private FeignPaymentClient  feignPaymentClient;

	@MockBean
	private FeignStockClient  feignStockClient;

	@MockBean
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	void contextLoads() {
	}

}
