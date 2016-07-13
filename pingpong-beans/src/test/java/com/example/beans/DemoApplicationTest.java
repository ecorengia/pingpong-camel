package com.example.beans;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class DemoApplicationTest {

	@Autowired
	CamelContext camelContext;

	@Test
	public void contextLoads() {
		// we expect that a number of messages is automatic done by the Camel
		// route as it uses a timer to trigger
		NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(4).create();

		assertTrue(notify.matches(10, TimeUnit.SECONDS));
	}

}
