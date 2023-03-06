/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.microsphere.spring.jdbc.p6spy.beans.factory.config;

import com.p6spy.engine.logging.ErrorLoggingEventListener;
import io.github.microsphere.spring.jdbc.p6spy.annotation.EnableP6DataSource;
import io.github.microsphere.spring.test.jdbc.embedded.EnableEmbeddedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

/**
 * {@link EnableP6DataSource} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        EnableP6DataSourceTest.class,
        ErrorLoggingEventListener.class
})
@EnableP6DataSource
@EnableEmbeddedDatabase(dataSource = "testDataSource")
public class EnableP6DataSourceTest {

    @Autowired
    @Qualifier("testDataSource")
    private DataSource dataSource;

    @Test
    public void test() throws Exception {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
    }
}
