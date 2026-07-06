package com.parko.persistence.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = TestPersistenceCoreApplication.class)
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
