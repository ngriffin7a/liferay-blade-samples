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
package com.liferay.blade.samples.applicant.mvcbean.spring.jsp.service.mock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.liferay.blade.samples.applicant.mvcbean.spring.jsp.dto.City;
import com.liferay.blade.samples.applicant.mvcbean.spring.jsp.service.CityService;
import com.liferay.blade.samples.applicant.mvcbean.spring.jsp.service.ProvinceService;


/**
 * @author  Neil Griffin
 */
@ApplicationScoped
public class CityServiceMockImpl implements CityService {

	@Inject
	private ProvinceService provinceService;

	// Private Data Members
	private List<City> cities;

	public List<City> getCities() {
		return cities;
	}

	public City getCityByPostalCode(String postalCode) {
		List<City> cities = getCities();

		for (City city : cities) {

			if (city.getPostalCode().equals(postalCode)) {
				return city;
			}
		}

		return null;
	}

	@PostConstruct
	public void postConstruct() {

		long cityId = 1;
		cities = new ArrayList<>();

		City city = new City(cityId++, provinceService.getProvinceId("DE"), "Wilmington", "19806");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("GA"), "Atlanta", "30329");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("FL"), "Orlando", "32801");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("MD"), "Baltimore", "21224");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("NC"), "Charlotte", "28202");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("NJ"), "Hoboken", "07030");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("NY"), "Albany", "12205");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("SC"), "Columbia", "29201");
		cities.add(city);
		city = new City(cityId++, provinceService.getProvinceId("VA"), "Roanoke", "24013");
		cities.add(city);
	}
}
