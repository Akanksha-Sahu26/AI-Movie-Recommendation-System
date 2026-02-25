package com.example.dto;

import java.util.ArrayList;

public class MovieRecommendationDto {
	
	private String name;
	private ArrayList<String>cast;
	private String plot;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getCast() {
		return cast;
	}
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	

}
