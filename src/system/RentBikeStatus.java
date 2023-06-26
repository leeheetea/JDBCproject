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
	
	public RentBikeStatus(int rackTotCnt, String stationName, int parkingBikeTotCnt, int shared, double stationLatitude,
			double stationLongitude, String stationId) {
		super();
		this.rackTotCnt = rackTotCnt;
		this.stationName = stationName;
		this.parkingBikeTotCnt = parkingBikeTotCnt;
		this.shared = shared;
		this.stationLatitude = stationLatitude;
		this.stationLongitude = stationLongitude;
		this.stationId = stationId;
	}

	public int getRackTotCnt() {
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
		return "[ " + stationName + " | 주차된 자전거 수="
				+ parkingBikeTotCnt + " | 대여소 ID=" + stationId + " ]";
	}
}
