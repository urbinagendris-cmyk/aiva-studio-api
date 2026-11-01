package com.aivastudio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:aiva_studio_test;DB_CLOSE_DELAY=-1",
		"logging.level.root=ERROR"
})
class AivaStudioApplicationTests {

	@Test
	void contextLoads() {
	}

}
