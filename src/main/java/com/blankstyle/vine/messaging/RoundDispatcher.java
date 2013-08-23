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
package com.blankstyle.vine.messaging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.blankstyle.vine.Channel;
import com.blankstyle.vine.Message;

/**
 * A round-robin dispatcher.
 *
 * @author Jordan Halterman
 */
public class RoundDispatcher implements Dispatcher {

  private List<Channel> items;

  private Iterator<Channel> iterator;

  @Override
  public void init(Collection<Channel> channels) {
    this.items = new ArrayList<Channel>();
    Iterator<Channel> iterator = channels.iterator();
    while (iterator.hasNext()) {
      items.add(iterator.next());
    }
    this.iterator = items.iterator();
  }

  @Override
  public <T> void dispatch(Message<T> message) {
    if (!iterator.hasNext()) {
      iterator = items.iterator();
    }
    iterator.next().send(message);
  }

}