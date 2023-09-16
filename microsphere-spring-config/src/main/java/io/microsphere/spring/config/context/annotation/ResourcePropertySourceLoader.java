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
package io.microsphere.spring.config.context.annotation;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.List;

import static org.springframework.core.io.support.ResourcePatternUtils.getResourcePatternResolver;

/**
 * The {@link PropertySourceExtensionLoader} Class for {@link ResourcePropertySource}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ResourcePropertySource
 * @see PropertySourceExtensionAttributes
 * @since 1.0.0
 */
public class ResourcePropertySourceLoader extends PropertySourceExtensionLoader<ResourcePropertySource, PropertySourceExtensionAttributes<ResourcePropertySource>> implements ResourceLoaderAware, BeanClassLoaderAware {

    private ResourcePatternResolver resourcePatternResolver;

    private ClassLoader classLoader;

    @Override
    protected Resource[] getResources(PropertySourceExtensionAttributes<ResourcePropertySource> extensionAttributes, String propertySourceName, String resourceValue)
            throws Throwable {
        return resourcePatternResolver.getResources(resourceValue);
    }

    @Override
    protected void configureAutoRefreshedResources(PropertySourceExtensionAttributes<ResourcePropertySource> extensionAttributes,
                                                   String propertySourceName, String[] resourceValues, List<Resource> resourcesList) {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = getResourcePatternResolver(resourceLoader);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
