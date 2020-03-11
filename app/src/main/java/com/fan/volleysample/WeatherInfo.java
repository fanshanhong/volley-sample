package com.fan.volleysample;

import java.util.List;

public class WeatherInfo {
 
	private String city;
 
	private String temp;
 
	private String time;
	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getCity() {
		return city;
	}
 
	public void setCity(String city) {
		this.city = city;
	}
 
	public String getTemp() {
		return temp;
	}
 
	public void setTemp(String temp) {
		this.temp = temp;
	}
 
	public String getTime() {
		return time;
	}
 
	public void setTime(String time) {
		this.time = time;
	}
 
}