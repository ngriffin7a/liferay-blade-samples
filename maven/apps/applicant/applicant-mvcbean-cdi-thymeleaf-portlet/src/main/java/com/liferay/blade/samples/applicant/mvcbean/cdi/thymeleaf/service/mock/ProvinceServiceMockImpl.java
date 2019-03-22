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
package com.liferay.blade.samples.applicant.mvcbean.cdi.thymeleaf.service.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.liferay.blade.samples.applicant.mvcbean.cdi.thymeleaf.dto.Province;
import com.liferay.blade.samples.applicant.mvcbean.cdi.thymeleaf.service.ProvinceService;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class ProvinceServiceMockImpl implements ProvinceService {

	private List<Province> provinces;

	@Override
	public List<Province> getAllProvinces() {
		return provinces;
	}

	@Override
	public long getProvinceId(String provinceName) {
		long provinceId = 0;
		List<Province> provinces = getAllProvinces();

		for (Province province : provinces) {

			if (province.getProvinceName().equals(provinceName)) {
				provinceId = province.getProvinceId();

				break;
			}
		}

		return provinceId;
	}

	@PostConstruct
	public void postConstruct() {

		long provinceId = 1;
		provinces = new ArrayList<>();

		Province province = new Province(provinceId++, "DE");
		provinces.add(province);
		province = new Province(provinceId++, "GA");
		provinces.add(province);
		province = new Province(provinceId++, "FL");
		provinces.add(province);
		province = new Province(provinceId++, "MD");
		provinces.add(province);
		province = new Province(provinceId++, "NC");
		provinces.add(province);
		province = new Province(provinceId++, "NJ");
		provinces.add(province);
		province = new Province(provinceId++, "NY");
		provinces.add(province);
		province = new Province(provinceId++, "SC");
		provinces.add(province);
		province = new Province(provinceId++, "VA");
		provinces.add(province);
		provinces = Collections.unmodifiableList(provinces);
	}
}
