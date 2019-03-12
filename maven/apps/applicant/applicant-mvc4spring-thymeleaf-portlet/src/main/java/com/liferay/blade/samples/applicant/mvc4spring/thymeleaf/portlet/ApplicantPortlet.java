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
package com.liferay.blade.samples.applicant.mvc4spring.thymeleaf.portlet;

import java.io.IOException;

import javax.portlet.HeaderRequest;
import javax.portlet.HeaderResponse;
import javax.portlet.PortletException;

import com.liferay.portletmvc4spring.DispatcherPortlet;


/**
 * @author  Neil Griffin
 */
public class ApplicantPortlet extends DispatcherPortlet {

	@Override
	public void renderHeaders(HeaderRequest headerRequest, HeaderResponse headerResponse) throws PortletException,
		IOException {

		boolean liferayDetected = PortletContainer.LIFERAY.isDetected(headerRequest);
		boolean plutoDetected = PortletContainer.PLUTO.isDetected(headerRequest);

		if (plutoDetected) {
			headerResponse.addDependency("jQuery", "com.jquery", "1.12.1",
				"<script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>");
		}

		if (liferayDetected || plutoDetected) {
			headerResponse.addDependency("jQueryUI", "com.jquery", "1.12.1",
				"<script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>");
			headerResponse.addDependency("jQueryUICSS", "com.jquery", "1.12.1",
				"<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">");
		}
	}
}
