/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.context;

import javax.servlet.ServletContext;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import static org.assertj.core.api.Assertions.assertThat;
import org.jboss.eap.additional.testsuite.annotations.EapAdditionalTestsuite;

/**
 * Base class for {@link SpringBootTest} tests configured to start an embedded web server.
 *
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
@EapAdditionalTestsuite({"modules/testcases/jdkAll/master/springboot/src/main/java"})
public abstract class AbstractSpringBootTestWebServerWebEnvironmentTests {

	@LocalServerPort
	private int port = 0;

	@Value("${value}")
	private int value = 0;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private TestRestTemplate restTemplate;

	public WebApplicationContext getContext() {
		return this.context;
	}

	public TestRestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	protected abstract static class AbstractConfig {

		@Value("${server.port:8080}")
		private int port = 8080;

		@Bean
		public DispatcherServlet dispatcherServlet() {
			return new DispatcherServlet();
		}

		@Bean
		public ServletWebServerFactory webServerFactory() {
			TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
			factory.setPort(this.port);
			return factory;
		}

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyPlaceholder() {
			return new PropertySourcesPlaceholderConfigurer();
		}

		@RequestMapping("/")
		public String home() {
			return "Hello World";
		}

	}

}
