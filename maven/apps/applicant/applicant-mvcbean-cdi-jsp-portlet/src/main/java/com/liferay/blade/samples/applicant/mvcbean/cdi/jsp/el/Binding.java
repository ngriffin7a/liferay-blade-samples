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
package com.liferay.blade.samples.applicant.mvcbean.cdi.jsp.el;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;


/**
 * @author  Neil Griffin
 */
@Named
@ApplicationScoped
public class Binding {

	@Inject
	private BindingResult bindingResult;

	public String error(String exp) {

		StringBuilder sb = new StringBuilder();
		Collection<String> errors = errors(exp);

		boolean first = true;

		for (String error : errors) {

			if (first) {
				first = false;
			}
			else {
				sb.append(" ");
			}

			sb.append(error);
		}

		return sb.toString();
	}

	public Collection<String> errors(String exp) {

		if (exp == null) {
			return null;
		}

		Expression expression = new Expression(exp);

		Set<String> errors = new LinkedHashSet<>();

		Set<ParamError> bindingResultErrors = bindingResult.getErrors(expression.getProperty());

		for (ParamError bindingResultError : bindingResultErrors) {
			errors.add(bindingResultError.getMessage());
		}

		return errors;
	}

	public boolean hasErrors(String exp) {

		if (exp == null) {
			return false;
		}

		Expression expression = new Expression(exp);

		Set<ParamError> bindingResultErrors = bindingResult.getErrors(expression.getProperty());

		return !bindingResultErrors.isEmpty();
	}
}
