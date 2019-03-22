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
package com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.engine.ViewEngineContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.annotations.RenderMethod;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

import com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.dto.Applicant;
import com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.dto.Attachment;
import com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.service.ProvinceService;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
@Controller
public class ApplicantRenderController {

	@Inject
	private AttachmentManager attachmentManager;

	@Inject
	private Models models;

	@Inject
	private PortletPreferences portletPreferences;

	@Inject
	private ProvinceService provinceService;

	@Inject
	private ViewEngineContext viewEngineContext;

	@RenderMethod(portletNames = { "portlet1" })
	@ValidateOnExecution(type = ExecutableType.NONE)
	public String prepareView(RenderRequest renderRequest, RenderResponse renderResponse) {

		String viewName = viewEngineContext.getView();

		if (viewName == null) {

			viewName = "applicant.jspx";

			Applicant applicant = (Applicant) models.get("applicant");

			if (applicant == null) {
				applicant = new Applicant();
				models.put("applicant", applicant);
			}

			PortletSession portletSession = renderRequest.getPortletSession();
			List<Attachment> attachments = attachmentManager.getAttachments(portletSession.getId());
			applicant.setAttachments(attachments);

			String datePattern = portletPreferences.getValue("datePattern", null);

			models.put("jQueryDatePattern", _getJQueryDatePattern(datePattern));

			models.put("provinces", provinceService.getAllProvinces());

			CDI<Object> currentCDI = CDI.current();
			BeanManager beanManager = currentCDI.getBeanManager();
			Class<? extends BeanManager> beanManagerClass = beanManager.getClass();
			Package beanManagerPackage = beanManagerClass.getPackage();
			models.put("weldVersion", beanManagerPackage.getImplementationVersion());
		}

		return viewName;
	}

	private String _getJQueryDatePattern(String datePattern) {

		String jQueryDatePattern = datePattern;

		if (datePattern.contains("yyyy")) {
			jQueryDatePattern = datePattern.replaceAll("yyyy", "yy");
		}
		else if (datePattern.contains("yy")) {
			jQueryDatePattern = datePattern.replaceAll("yy", "y");
		}

		jQueryDatePattern = jQueryDatePattern.replaceAll("M", "m");

		return jQueryDatePattern;
	}
}
