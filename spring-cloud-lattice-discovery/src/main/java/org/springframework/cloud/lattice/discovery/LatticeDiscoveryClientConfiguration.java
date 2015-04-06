/*
 * Copyright 2013-2015 the original author or authors.
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

package org.springframework.cloud.lattice.discovery;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pivotal.receptor.client.ReceptorClient;

/**
 * @author Spencer Gibb
 */
@Configuration
@EnableConfigurationProperties
public class LatticeDiscoveryClientConfiguration {

	@Bean
	public ReceptorService receptorService() {
		return new ReceptorService(receptorClient(), latticeDiscoveryProperties());
	}

	@Bean
	public ReceptorClient receptorClient() {
		return new ReceptorClient(latticeDiscoveryProperties().getReceptorHost());
	}

	@Bean
	public LatticeDiscoveryClient latticeDiscoveryClient() {
		return new LatticeDiscoveryClient(receptorService(), receptorClient());
	}

	@Bean
	public LatticeDiscoveryProperties latticeDiscoveryProperties() {
		return new LatticeDiscoveryProperties();
	}
}
