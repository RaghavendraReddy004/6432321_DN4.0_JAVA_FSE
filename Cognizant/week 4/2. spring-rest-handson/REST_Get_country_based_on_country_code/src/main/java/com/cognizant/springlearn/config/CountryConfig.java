package com.cognizant.springlearn.config;

import com.cognizant.springlearn.model.Countries;
import com.cognizant.springlearn.model.Country;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.List;

@Configuration
public class CountryConfig {

    private List<Country> countries;

    public CountryConfig() {
        try {
            Resource resource = new ClassPathResource("countries.xml");
            InputStream is = resource.getInputStream();
            JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Countries countriesObj = unmarshaller.unmarshal(new StreamSource(is), Countries.class).getValue();
            this.countries = countriesObj.getCountry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Country> getCountries() {
        return countries;
    }
}
