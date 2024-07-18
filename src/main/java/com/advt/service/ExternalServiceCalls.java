package com.advt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceCalls {

    private static final String BASE_URL = "http://localhost:8383/";

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getStates(String mobileNumber) {
        String url = BASE_URL + "states/{mobileNumber}";
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {}, mobileNumber).getBody();
    }

    public List<String> getDistricts(String mobileNumber, String stateName) {
        String url = BASE_URL + "districts/{mobileNumber}/{stateName}";
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {}, mobileNumber, stateName).getBody();
    }

    public List<String> getMandals(String mobileNumber, String districtName) {
        String url = BASE_URL + "mandals/{mobileNumber}/{districtName}";
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {}, mobileNumber, districtName).getBody();
    }
    
    public List<String> getDeliveryTimesByTimePeriod(String timePeriod) {
		String url = BASE_URL + "batchids/" + timePeriod;
		return restTemplate.exchange(url, HttpMethod.GET, null, 
			   new ParameterizedTypeReference<List<String>>() {}, timePeriod).getBody();
    }
}
