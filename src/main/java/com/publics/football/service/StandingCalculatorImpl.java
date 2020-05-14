package com.publics.football.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publics.football.entity.Country;
import com.publics.football.entity.Standing;
import com.publics.football.entity.Team;

@Service
public class StandingCalculatorImpl implements StandingCalculator {

	final String url = "https://apiv2.apifootball.com";
	final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Standing calculateStanding(String country, String league, String team) {
		if (isCorrectCountry(country) && isCorrectTeam(team)) {
			try {
				CloseableHttpResponse response = client.execute(new HttpGet(
						url + "/?action=get_standings&league_id=" + league.split("-")[1] + "&APIkey=" + apiKey));
				String bodyAsString = EntityUtils.toString(response.getEntity());
				Standing[] standings = mapper.readValue(bodyAsString, Standing[].class);
				for (Standing temp : standings) {
					System.out.println(temp);
				}
				List<Standing> standingFilter = Arrays.asList(standings).stream()
						.filter(s -> s.getCountry_name().equals(country.split("-")[0])
								&& s.getTeam_id().equals(team.split("-")[1]))
						.collect(Collectors.toList());
				if (standingFilter.size() != 0) {
					return standingFilter.get(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean isCorrectTeam(String team) {
		try {
			CloseableHttpResponse response = client.execute(new HttpGet(
					url + "/?action=get_teams&team_id=" + team.split("-")[1] + "&APIkey=" + apiKey));
			String bodyAsString = EntityUtils.toString(response.getEntity());
			Team[] teamObj = mapper.readValue(bodyAsString, Team[].class);
			if(teamObj.length == 1 && teamObj[0].getTeam_name().equalsIgnoreCase(team.split("-")[0]))
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isCorrectCountry(String country) {
		try {
			CloseableHttpResponse response = client.execute(new HttpGet(
					url + "/?action=get_countries&APIkey=" + apiKey));
			String bodyAsString = EntityUtils.toString(response.getEntity());
			Country[] countries = mapper.readValue(bodyAsString, Country[].class);
			List<Country> countryObj = Arrays.asList(countries).stream()
					.filter(s -> s.getCountry_name().equals(country.split("-")[0])
							&& s.getCountry_id().equals(country.split("-")[1]))
					.collect(Collectors.toList());
			if(countryObj.size() == 1)
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
