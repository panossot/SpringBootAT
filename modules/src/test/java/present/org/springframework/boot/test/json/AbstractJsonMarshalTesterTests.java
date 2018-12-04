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

package org.springframework.boot.test.json;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.springframework.core.ResolvableType;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ReflectionUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.jboss.eap.additional.testsuite.annotations.EapAdditionalTestsuite;

/**
 * Tests for {@link AbstractJsonMarshalTester}.
 *
 * @author Phillip Webb
 */
@EapAdditionalTestsuite({"modules/testcases/jdkAll/master/springboot/src/main/java"})
public abstract class AbstractJsonMarshalTesterTests {

	private static final String JSON = "{\"name\":\"Spring\",\"age\":123}";

	private static final String MAP_JSON = "{\"a\":" + JSON + "}";

	private static final String ARRAY_JSON = "[" + JSON + "]";

	private static final ExampleObject OBJECT = createExampleObject("Spring", 123);

	private static final ResolvableType TYPE = ResolvableType
			.forClass(ExampleObject.class);

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	

	protected static final ExampleObject createExampleObject(String name, int age) {
		ExampleObject exampleObject = new ExampleObject();
		exampleObject.setName(name);
		exampleObject.setAge(age);
		return exampleObject;
	}

	protected final AbstractJsonMarshalTester<Object> createTester(ResolvableType type) {
		return createTester(AbstractJsonMarshalTesterTests.class, type);
	}

	protected abstract AbstractJsonMarshalTester<Object> createTester(
			Class<?> resourceLoadClass, ResolvableType type);

	/**
	 * Access to field backed by {@link ResolvableType}.
	 */
	public static class ResolvableTypes {

		public List<ExampleObject> listOfExampleObject;

		public ExampleObject[] arrayOfExampleObject;

		public Map<String, ExampleObject> mapOfExampleObject;

		public static ResolvableType get(String name) {
			Field field = ReflectionUtils.findField(ResolvableTypes.class, name);
			return ResolvableType.forField(field);
		}

	}

}
