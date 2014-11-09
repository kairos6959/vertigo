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
package net.kuujo.vertigo.io.connection;

import net.kuujo.vertigo.Definition;
import net.kuujo.vertigo.io.partition.Partitioner;

/**
 * A connection represents a link between two components within a network.<p>
 *
 * When a connection is created, each partition of the source component
 * will be setup to send messages to each partition of the target component.
 * How messages are routed to multiple target partitions can be configured
 * using selectors.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public interface ConnectionDefinition extends Definition {

  /**
   * Sets the connection source.
   *
   * @param source The connection source.
   * @return The connection options.
   */
  ConnectionDefinition setSource(SourceDefinition source);

  /**
   * Returns the connection source.
   *
   * @return The connection source.
   */
  SourceDefinition getSource();

  /**
   * Sets the connection target.
   *
   * @param target The connection target.
   * @return The connection options.
   */
  ConnectionDefinition setTarget(TargetDefinition target);

  /**
   * Returns the connection target.
   *
   * @return The connection target.
   */
  TargetDefinition getTarget();

  /**
   * Returns the connection partitioner.
   *
   * @return The connection selector.
   */
  Partitioner getPartitioner();

  /**
   * Sets the connection partitioner.
   *
   * @param partitioner The partitioner with which to partition individual connections.
   * @return The connection configuration.
   */
  ConnectionDefinition setPartitioner(Partitioner partitioner);

  /**
   * Sets a round-robin partitioner on the connection.
   *
   * @return The connection configuration.
   */
  ConnectionDefinition roundPartition();

  /**
   * Sets a random partitioner on the connection.
   *
   * @return The connection configuration.
   */
  ConnectionDefinition randomPartition();

  /**
   * Sets a mod-hash based partitioner on the connection.
   *
   * @param header The hash header.
   * @return The connection configuration.
   */
  ConnectionDefinition hashPartition(String header);

  /**
   * Sets an all partitioner on the connection.
   *
   * @return The connection configuration.
   */
  ConnectionDefinition allPartition();

  /**
   * Sets a custom partitioner on the connection.
   *
   * @param partitioner The custom selector to set.
   * @return The connection configuration.
   */
  ConnectionDefinition partition(Partitioner partitioner);

}
