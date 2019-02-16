/**
 * Copyright (c) 2000-2018 Liferay, Inc. All rights reserved.
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
package com.liferay.blade.samples.applicant.mvcbean.spring.jsp.portlet;

import javax.portlet.PortletRequest;
import javax.portlet.filter.PortletRequestWrapper;


/**
 * @author  Neil Griffin
 */
public enum PortletContainer {

	LIFERAY("com.liferay"), PLUTO("org.apache.pluto"), WEBSPHERE("com.ibm");

	private final String fqcnPrefix;

	PortletContainer(String fqcnPrefix) {
		this.fqcnPrefix = fqcnPrefix;
	}

	public boolean isDetected(PortletRequest portletRequest) {

		portletRequest = unwrapPortletRequest(portletRequest);

		Class<? extends PortletRequest> portletRequestClass = portletRequest.getClass();

		String portletRequestClassName = portletRequestClass.getName();

		return portletRequestClassName.startsWith(fqcnPrefix);
	}

	private PortletRequest unwrapPortletRequest(PortletRequest portletRequest) {

		if (portletRequest instanceof PortletRequestWrapper) {
			PortletRequestWrapper portletRequestWrapper = (PortletRequestWrapper) portletRequest;

			return unwrapPortletRequest(portletRequestWrapper.getRequest());
		}

		return portletRequest;
	}
}
