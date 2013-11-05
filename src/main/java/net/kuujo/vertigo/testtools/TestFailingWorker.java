/*
 * Copyright 2013 the original author or authors.
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
package net.kuujo.vertigo.testtools;

import java.util.UUID;

import org.vertx.java.core.Handler;

import net.kuujo.vertigo.VertigoVerticle;
import net.kuujo.vertigo.message.JsonMessage;
import net.kuujo.vertigo.network.Verticle;
import net.kuujo.vertigo.worker.Worker;

/**
 * A test worker that fails all messages.
 *
 * @author Jordan Halterman
 */
public class TestFailingWorker extends VertigoVerticle {

  /**
   * Creates a failing worker definition.
   *
   * @return
   *   A component definition.
   */
  public static Verticle createDefinition() {
    return createDefinition(1);
  }

  /**
   * Creates a failing worker definition.
   *
   * @param instances
   *   The number of instances.
   * @return
   *   A component definition.
   */
  public static Verticle createDefinition(int instances) {
    return new Verticle(UUID.randomUUID().toString())
        .setMain(TestFailingWorker.class.getName()).setInstances(instances);
  }

  @Override
  public void start() {
    final Worker worker = vertigo.createWorker();
    worker.messageHandler(new Handler<JsonMessage>() {
      @Override
      public void handle(JsonMessage message) {
        worker.fail(message);
      }
    }).start();
  }

}
