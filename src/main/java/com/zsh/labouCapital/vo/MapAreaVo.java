package com.zsh.labouCapital.vo;

public class MapAreaVo {
    private String lonMin;
    private String lonMax;
    private String latMin;
    private String latMax;
	public String getLonMin() {
		return lonMin;
	}
	public void setLonMin(String lonMin) {
		this.lonMin = lonMin;
	}
	public String getLonMax() {
		return lonMax;
	}
	public void setLonMax(String lonMax) {
		this.lonMax = lonMax;
	}
	public String getLatMin() {
		return latMin;
	}
	public void setLatMin(String latMin) {
		this.latMin = latMin;
	}
	public String getLatMax() {
		return latMax;
	}
	public void setLatMax(String latMax) {
		this.latMax = latMax;
	}
	@Override
	public String toString(){
		return new StringBuilder(" MapAreaVo[")
				.append("lonMin=").append(lonMin).append(",")
				.append("lonMax=").append(lonMax).append(",")
				.append("latMin=").append(latMin).append(",")
				.append("latMax=").append(latMax)
				.append("]").toString();
	}
}
