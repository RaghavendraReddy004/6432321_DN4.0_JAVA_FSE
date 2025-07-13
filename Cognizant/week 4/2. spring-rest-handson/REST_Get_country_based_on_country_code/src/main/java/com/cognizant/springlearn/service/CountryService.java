package com.cognizant.springlearn.service;

import com.cognizant.springlearn.config.CountryConfig;
import com.cognizant.springlearn.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryConfig countryConfig;

    public Country getCountry(String code) {
        List<Country> countries = countryConfig.getCountries();

        return countries.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found with code: " + code));
    }
}
