package com.publics.football.service;

import java.net.http.HttpClient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.publics.football.entity.Standing;

public interface StandingCalculator {
	final String apiKey = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
	final CloseableHttpClient client = HttpClientBuilder.create().build();
	Standing calculateStanding(String country,String league,String team);
}
