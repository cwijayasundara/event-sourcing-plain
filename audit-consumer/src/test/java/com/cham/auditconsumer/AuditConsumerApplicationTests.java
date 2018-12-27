package com.cham.auditconsumer;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@Profile("test")
@SpringBootTest
@TestExecutionListeners(listeners = {
		CassandraUnitDependencyInjectionTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class}
)
@EmbeddedCassandra(timeout = 60000)
@CassandraDataSet(keyspace = "event_source_keyspace", value = {"tables.cql"})
public class AuditConsumerApplicationTests {

	@Test
	public void contextLoads() {
	}
}

