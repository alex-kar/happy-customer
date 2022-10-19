package io.github.alexkar.happycustomer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
class HappyCustomerApplicationTests {

	@Test
	void contextLoads() {
	}

}
