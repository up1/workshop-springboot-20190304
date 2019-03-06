package com.example.hello.aqi;

public class Data {

	private int aqi;
	
	public Data() {
	}

	public Data(int aqi) {
		this.aqi = aqi;
	}

	public int getAqi() {
		return aqi;
	}

	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	
	@Override
	public String toString() {
		return "Data [aqi=" + aqi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aqi;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (aqi != other.aqi)
			return false;
		return true;
	}
	
	
}
