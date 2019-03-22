/**
 * Copyright (c) 2000-2019 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.blade.samples.applicant.mvcbean.spring.jsp.event;

import javax.enterprise.context.ApplicationScoped;
import javax.mvc.event.AfterControllerEvent;
import javax.mvc.event.AfterProcessViewEvent;
import javax.mvc.event.BeforeControllerEvent;
import javax.mvc.event.BeforeProcessViewEvent;
import javax.mvc.event.ControllerRedirectEvent;
import javax.mvc.event.MvcEvent;
import javax.ws.rs.container.ResourceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class MvcEventListener {

	private static final Logger logger = LoggerFactory.getLogger(MvcEventListener.class);

	@EventListener
	public void afterControllerEventListener(AfterControllerEvent afterControllerEvent) {
		ResourceInfo resourceInfo = afterControllerEvent.getResourceInfo();

		logger.debug("Observed AfterControllerEvent class=[{}], method=[{}]", resourceInfo.getResourceClass(),
			resourceInfo.getResourceMethod());
	}

	@EventListener
	public void afterProcessViewEventListener(AfterProcessViewEvent afterProcessViewEvent) {
		logger.debug("Observed AfterProcesssViewEvent view=[{}]", afterProcessViewEvent.getView());
	}

	@EventListener
	public void beforeControllerEventListener(BeforeControllerEvent beforeControllerEvent) {
		ResourceInfo resourceInfo = beforeControllerEvent.getResourceInfo();

		logger.debug("Observed BeforeControllerEvent class=[{}], method=[{}]", resourceInfo.getResourceClass(),
			resourceInfo.getResourceMethod());
	}

	@EventListener
	public void beforeProcessViewEventListener(BeforeProcessViewEvent beforeProcessViewEvent) {
		logger.debug("Observed BeforeProcesssViewEvent view=[{}]", beforeProcessViewEvent.getView());
	}

	@EventListener
	public void controllerRedirectEventListener(ControllerRedirectEvent controllerRedirectEvent) {
		ResourceInfo resourceInfo = controllerRedirectEvent.getResourceInfo();

		logger.debug("Observed ControllerRedirectEvent class=[{}], method=[{}] location=[{}]",
			resourceInfo.getResourceClass(), resourceInfo.getResourceMethod(), controllerRedirectEvent.getLocation());
	}

	@EventListener
	public void mvcEventListener(MvcEvent mvcEvent) {
		logger.trace("Observed MvcEvent=[{}]", mvcEvent);
	}
}
