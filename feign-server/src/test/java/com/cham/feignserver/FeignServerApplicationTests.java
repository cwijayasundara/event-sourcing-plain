package com.cham.feignserver;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners(
		listeners = CassandraUnitTestExecutionListener.class,
		mergeMode = MERGE_WITH_DEFAULTS
)
@CassandraUnit
@CassandraDataSet
@EmbeddedCassandra(configuration = "application.yml")
public class FeignServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
