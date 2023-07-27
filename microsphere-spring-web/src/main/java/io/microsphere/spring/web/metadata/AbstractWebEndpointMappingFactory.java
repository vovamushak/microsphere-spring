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
package io.microsphere.spring.web.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Abstract class for {@link WebEndpointMappingFactory}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see WebEndpointMappingFactory
 * @since 1.0.0
 */
public abstract class AbstractWebEndpointMappingFactory<S> implements WebEndpointMappingFactory<S> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public final Optional<WebEndpointMapping<?>> create(S source) {
        WebEndpointMapping<?> mapping = null;
        try {
            mapping = doCreate(source);
        } catch (Throwable e) {
            logger.error("The WebEndpointMapping instance can't be created by the source : {}", source, e);
        }
        return ofNullable(mapping);
    }

    @Nullable
    protected abstract WebEndpointMapping<?> doCreate(S source) throws Throwable;
}
