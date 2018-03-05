/*
 * Copyright 2015 Adobe Systems Incorporated Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.citraining.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A service to demonstrate how changes in the resource tree can be listened for. It registers an event handler service.
 * The component is activated immediately after the bundle is started through the immediate flag. 
 * Please note, that apart from EventHandler services, the immediate flag should not be set on a service.
 * Event Handler - At the Sling level with event handlers and jobs
 * 
 * EventHandler objects are registered with the Framework service registry and are notified with an Event object 
 * when an event is sent or posted.
 * EventHandler objects can inspect the received Event object to determine its topic and properties.
 * EventHandler objects must be registered with a service property EventConstants.EVENT_TOPIC 
 * whose value is the list of topics in which the event handler is interested.
 */
@Component (service = EventHandler.class, immediate = true, property = { Constants.SERVICE_DESCRIPTION + "=Demo to listen on changes in the resource tree", EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/*" })
public class SimpleResourceHandler implements EventHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void handleEvent(final Event event) {
		logger.debug("Resource event: {} at: {}", event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));
	}
}
