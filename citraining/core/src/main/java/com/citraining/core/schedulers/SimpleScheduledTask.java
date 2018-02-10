/*
 * Copyright 2015 Adobe Systems Incorporated Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.citraining.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.config.SchedulerConfig;

/**
 * A simple demo for cron-job like tasks that get executed regularly. It also demonstrates how property values can be set. Users can set the property
 * values in /system/console/configMgr
 */
@Component (service = Runnable.class, property = { "metatype = true", "label = A scheduled task", "description = Simple demo for cron-job like task with properties" })
@Designate (ocd = SchedulerConfig.class)
public class SimpleScheduledTask implements Runnable {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private SchedulerConfig config;

	@Override
	public void run() {
		String myParmeter = config.myParameter();
		logger.debug("SimpleScheduledTask is now running, myParameter='{}'", myParmeter);
	}

	@Activate
	protected void activate(SchedulerConfig config) {
		this.config = config;
	}

}
