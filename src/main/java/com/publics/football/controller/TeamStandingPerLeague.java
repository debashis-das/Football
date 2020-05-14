package com.publics.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.publics.football.entity.Standing;
import com.publics.football.service.StandingCalculator;

@RestController
public class TeamStandingPerLeague {
	
	@Autowired
	StandingCalculator standingCalculator;
	
	@RequestMapping(value="/standing",method=RequestMethod.GET)
	public Standing fetchTeamStandingPerLeague(@RequestParam("country") String country,
			@RequestParam("league") String league, @RequestParam("team") String team) {
		return standingCalculator.calculateStanding(country, league, team);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String fetchTeamStandingPerLeague() {
		return "Hello";
	}

}
