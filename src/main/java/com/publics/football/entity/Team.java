package com.publics.football.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
	String team_key;
	String team_name;

	public String getTeam_key() {
		return team_key;
	}

	public void setTeam_key(String team_key) {
		this.team_key = team_key;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

}
