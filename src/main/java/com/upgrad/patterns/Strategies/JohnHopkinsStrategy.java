package com.upgrad.patterns.Strategies;

import com.upgrad.patterns.Config.RestServiceGenerator;
import com.upgrad.patterns.Entity.JohnHopkinResponse;
import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JohnHopkinsStrategy implements IndianDiseaseStat {

	private Logger logger = LoggerFactory.getLogger(JohnHopkinsStrategy.class);

	private RestTemplate restTemplate;

	@Value("${config.john-hopkins-url}")
	private String baseUrl;

	public JohnHopkinsStrategy() {
		restTemplate = RestServiceGenerator.GetInstance();
	}

	@Override
	public String GetActiveCount() {
		try {
			//get response from the getJohnHopkinResponses method
			JohnHopkinResponse[] responses = getJohnHopkinResponses();
	
			//filter the data based such that country equals India (use getCountry() to get the country value)
			List<JohnHopkinResponse> indiaResponses = Arrays.stream(responses)
					.filter(response -> "India".equals(response.getCountry()))
					.collect(Collectors.toList());
	
			//Map the data to "confirmed" value (use getStats() and getConfirmed() to get stats value and confirmed value)
			//Reduce the data to get a sum of all the "confirmed" values
			float sum = 0;
			for (JohnHopkinResponse response : indiaResponses) {
				if (response.getStats() != null && response.getStats().getConfirmed() != null) {
					sum += response.getStats().getConfirmed();
				}
			}
	
			//return the response after rounding it up to 0 decimal places
			return String.format("%.0f", sum);
	
		} catch (Exception e) {
			//log the error
			logger.error("Error while fetching active count from John Hopkins", e);
	
			//return null
			return null;
		}
	}
		

	private JohnHopkinResponse[] getJohnHopkinResponses() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


		return restTemplate.exchange(
				baseUrl, HttpMethod.GET, new HttpEntity<Object>(headers),
				JohnHopkinResponse[].class).getBody();
	}
}