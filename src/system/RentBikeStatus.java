package system;

public class RentBikeStatus {

	private int rackTotCnt;
	private String stationName;
	private int parkingBikeTotCnt;
	private int shared;
	private double stationLatitude;
	private double stationLongitude;
	private String stationId;
	
	public RentBikeStatus() {
	}
	
	public int getRackToCnt() {
		return rackTotCnt;
	}
	public void setRackToCnt(int racToCnt) {
		this.rackTotCnt = racToCnt;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getParkingBikeTotCnt() {
		return parkingBikeTotCnt;
	}
	public void setParkingBikeTotCnt(int parkingBikeToCnt) {
		this.parkingBikeTotCnt = parkingBikeToCnt;
	}
	public int getShared() {
		return shared;
	}
	public void setShared(int shared) {
		this.shared = shared;
	}
	public double getStationLatitude() {
		return stationLatitude;
	}
	public void setStationLatitude(double stationLatitude) {
		this.stationLatitude = stationLatitude;
	}
	public double getStationLongitude() {
		return stationLongitude;
	}
	public void setStationLongitude(double stationLongitude) {
		this.stationLongitude = stationLongitude;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	@Override
	public String toString() {
		return "[거치대 개수=" + rackTotCnt + ", 대여소 이름=" + stationName + ", 주차된 자전거 수="
				+ parkingBikeTotCnt + ",거치율=" + shared + ", 위도=" + stationLatitude
				+ ", 경도=" + stationLongitude + ", 대여소ID=" + stationId + "]";
	}
}
