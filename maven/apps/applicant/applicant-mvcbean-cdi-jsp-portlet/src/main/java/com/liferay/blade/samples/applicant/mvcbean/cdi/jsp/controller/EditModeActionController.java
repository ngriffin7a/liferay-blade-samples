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
package com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.controller;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Set;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.mvc.security.CsrfProtected;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.WindowState;
import javax.portlet.annotations.ActionMethod;
import javax.portlet.annotations.Namespace;
import javax.portlet.annotations.PortletRequestScoped;
import javax.validation.constraints.NotBlank;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.FormParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.dto.Preferences;


/**
 * @author  Neil Griffin
 */
@Controller
@PortletRequestScoped
@View("edit.jspx")
public class EditModeActionController {

	private static final Logger logger = LoggerFactory.getLogger(EditModeActionController.class);

	@Inject
	private BindingResult bindingResult;

	@Inject
	@FormParam("datePattern")
	@NotBlank
	private String datePattern;

	@Inject
	private Models models;

	@Inject
	@Namespace
	private CharSequence namespace;

	@Inject
	private PortletConfig portletConfig;

	@Inject
	private PortletPreferences portletPreferences;

	@ActionMethod(portletName = "portlet1", actionName = "reset")
	@ValidateOnExecution(type = ExecutableType.NONE)
	@CsrfProtected
	public void resetPreferences(ActionRequest actionRequest, ActionResponse actionResponse) {

		ResourceBundle resourceBundle = portletConfig.getResourceBundle(actionRequest.getLocale());

		try {

			Enumeration<String> preferenceNames = portletPreferences.getNames();

			while (preferenceNames.hasMoreElements()) {
				String preferenceName = preferenceNames.nextElement();

				if (!portletPreferences.isReadOnly(preferenceName)) {
					portletPreferences.reset(preferenceName);
				}
			}

			portletPreferences.store();
			actionResponse.setPortletMode(PortletMode.VIEW);
			actionResponse.setWindowState(WindowState.NORMAL);
			models.put("globalInfoMessage", resourceBundle.getString("your-request-processed-successfully"));
		}
		catch (Exception e) {

			models.put("globalErrorMessage", resourceBundle.getString("an-unexpected-error-occurred"));

			logger.error(e.getMessage(), e);
		}
	}

	@ActionMethod(portletName = "portlet1", actionName = "submitPreferences")
	@CsrfProtected
	public void submitPreferences(ActionRequest actionRequest, ActionResponse actionResponse) {

		ResourceBundle resourceBundle = portletConfig.getResourceBundle(actionRequest.getLocale());

		models.put("preferences", new Preferences(datePattern));

		Set<ParamError> bindingErrors = bindingResult.getAllErrors();

		if (bindingErrors.isEmpty()) {

			try {
				portletPreferences.setValue("datePattern", datePattern);
				portletPreferences.store();
				actionResponse.setPortletMode(PortletMode.VIEW);
				actionResponse.setWindowState(WindowState.NORMAL);
				models.put("globalInfoMessage", resourceBundle.getString("your-request-processed-successfully"));
			}
			catch (Exception e) {
				models.put("globalErrorMessage", resourceBundle.getString("an-unexpected-error-occurred"));
				logger.error(e.getMessage(), e);
			}
		}
	}
}
