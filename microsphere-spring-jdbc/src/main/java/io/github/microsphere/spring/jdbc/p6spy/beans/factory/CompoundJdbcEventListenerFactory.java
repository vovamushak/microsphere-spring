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
package io.github.microsphere.spring.jdbc.p6spy.beans.factory;

import com.p6spy.engine.event.CompoundJdbcEventListener;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6Factory;
import com.p6spy.engine.spy.P6LoadableOptions;
import com.p6spy.engine.spy.option.P6OptionChangedListener;
import com.p6spy.engine.spy.option.P6OptionsRepository;
import io.github.microsphere.spring.jdbc.p6spy.NoOpP6LoadableOptions;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

import static io.github.microsphere.spring.util.BeanUtils.getSortedBeans;

/**
 * The {@link P6Factory} class to create {@link CompoundJdbcEventListener}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see CompoundJdbcEventListener
 * @see P6Factory
 * @since 1.0.0
 */
public class CompoundJdbcEventListenerFactory implements P6Factory {

    private final ConfigurableListableBeanFactory beanFactory;

    private final ConfigurableEnvironment environment;

    public CompoundJdbcEventListenerFactory(ConfigurableListableBeanFactory beanFactory, ConfigurableEnvironment environment) {
        this.beanFactory = beanFactory;
        this.environment = environment;
    }

    @Override
    public P6LoadableOptions getOptions(P6OptionsRepository optionsRepository) {
        return new NoOpP6LoadableOptions();
    }

    private void registerP6OptionChangedListenerBeans(P6OptionsRepository optionsRepository) {
        List<P6OptionChangedListener> listeners = getSortedBeans(beanFactory, P6OptionChangedListener.class);
        listeners.forEach(optionsRepository::registerOptionChangedListener);
    }

    @Override
    public JdbcEventListener getJdbcEventListener() {
        List<JdbcEventListener> listeners = getSortedBeans(beanFactory, JdbcEventListener.class);
        return new CompoundJdbcEventListener(listeners);
    }
}
