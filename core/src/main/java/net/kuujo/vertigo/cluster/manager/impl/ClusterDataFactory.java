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
package net.kuujo.vertigo.cluster.manager.impl;

import org.vertx.java.core.Vertx;

import com.hazelcast.core.HazelcastInstance;

/**
 * Cluster data provider factory.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
class ClusterDataFactory {
  private final Vertx vertx;

  ClusterDataFactory(Vertx vertx) {
    this.vertx = vertx;
  }

  /**
   * Creates cluster data.
   *
   * @param localOnly Indicates whether to force the cluster to be local only.
   * @return A cluster data store.
   */
  public ClusterData createClusterData(boolean localOnly) {
    if (localOnly) return new VertxClusterData(vertx);
    HazelcastInstance hazelcast = ClusterListenerFactory.getHazelcastInstance(vertx);
    if (hazelcast != null) {
      return new HazelcastClusterData(hazelcast);
    } else {
      return new VertxClusterData(vertx);
    }
  }

}
