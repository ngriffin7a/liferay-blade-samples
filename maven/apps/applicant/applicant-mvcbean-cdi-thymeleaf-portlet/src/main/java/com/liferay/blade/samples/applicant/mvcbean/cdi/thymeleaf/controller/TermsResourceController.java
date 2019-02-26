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
package com.liferay.blade.samples.applicant.mvcbean.cdi.thymeleaf.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.mvc.security.CsrfProtected;
import javax.portlet.MimeResponse;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceParameters;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.portlet.annotations.ServeResourceMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Controller
public class TermsResourceController {

	private static final Logger logger = LoggerFactory.getLogger(TermsResourceController.class);

	@Inject
	private Models models;

	@CsrfProtected
	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "acceptTerms")
	public String acceptTerms(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		return "redirect:acceptance.html";
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html")
	public void redirectedView(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		ResourceParameters resourceParameters = resourceRequest.getResourceParameters();

		logger.debug("redirectedView=[{}]", resourceParameters.getValue("redirectedView"));
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "viewTerms")
	@View("terms.html")
	public void viewTerms(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy");

		Calendar todayCalendar = Calendar.getInstance();

		models.put("thisYear", dateFormat.format(todayCalendar.getTime()));

		// Thymeleaf
		models.put("acceptTermsResourceURL", ControllerUtil.createResourceURL(resourceResponse, "acceptTerms"));
		models.put("viewTermsAgainResourceURL", ControllerUtil.createResourceURL(resourceResponse, "viewTermsAgain"));
	}

	@ServeResourceMethod(portletNames = "portlet1", contentType = "text/html", resourceID = "viewTermsAgain")
	@View("redirect:terms.html")
	public void viewTermsAgain(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		logger.debug("Redirecting to Terms of Service");
	}
}
