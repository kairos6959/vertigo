/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.vertigo.network.impl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import net.kuujo.vertigo.network.NetworkDescriptor;
import net.kuujo.vertigo.network.NetworkLocator;
import net.kuujo.vertigo.util.Configs;

/**
 * Network locator implementation.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public class NetworkLocatorImpl implements NetworkLocator {

  @Override
  public NetworkDescriptor locateNetwork(String network) {
    Config config = ConfigFactory.parseResourcesAnySyntax(network)
      .withFallback(ConfigFactory.empty().withValue("vertigo.network", ConfigValueFactory.fromAnyRef(network)));
    return new NetworkDescriptorImpl(Configs.configObjectToJson(config));
  }

}
