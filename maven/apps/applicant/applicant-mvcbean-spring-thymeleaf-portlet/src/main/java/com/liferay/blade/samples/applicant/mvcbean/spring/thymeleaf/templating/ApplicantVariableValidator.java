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
package com.liferay.blade.samples.applicant.mvcbean.spring.thymeleaf.templating;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;

import org.apache.pluto.thymeleaf.mvc.portlet.spring.SpringVariableValidator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;

import org.springframework.stereotype.Component;


/**
 * @author  Neil Griffin
 */
@Component
@Primary
public class ApplicantVariableValidator extends SpringVariableValidator {

	@Autowired
	private PortletConfig portletConfig;

	@Autowired
	private PortletContext portletContext;

	public ApplicantVariableValidator() {
		super(null, null);
	}

	@Override
	public PortletConfig getPortletConfig() {
		return portletConfig;
	}

	@Override
	public PortletContext getPortletContext() {
		return portletContext;
	}

	@Override
	public boolean isValidName(String name, boolean headerPhase, boolean renderPhase, boolean resourcePhase) {

		boolean valid = super.isValidName(name, headerPhase, renderPhase, resourcePhase);

		if (!valid) {
			return false;
		}

		// In order to improve Thymeleaf performance, prevent the following beans from being included in EL resolution.
		if (name.equals("applicantPortlet") || name.equals("attachmentManager") || name.contains("cityService") ||
				name.endsWith("Controller") || name.equals("mvcEventListener") || name.contains("provinceService")) {
			return false;
		}

		return true;
	}
}
