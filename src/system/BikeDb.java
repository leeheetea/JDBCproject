package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BikeDb {

	Connection con;

	public BikeDb() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mydb";
		String userid = "root";
		String pwd = "root";

		try {
			Class.forName(driver);
			System.out.println("드라이버 로드");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}
	}

	private void insertIntoSql(RentBikeStatus bikeObject) {

		int rackTotCnt = bikeObject.getRackToCnt();
		String stationName = bikeObject.getStationName();
		int parkingBikeTotCnt = bikeObject.getParkingBikeTotCnt();
		int shared = bikeObject.getShared();
		double stationLatitude = bikeObject.getStationLatitude();
		double stationLongitude = bikeObject.getStationLongitude();
		String stationId = bikeObject.getStationId();

		String sql = "INSERT INTO rentbikestatus \r\n"
				+ "	(rackToCnt, stationName, parkingBikeTotCnt, shared, stationLatitude, stationLongitude, stationId)\r\n"
				+ "	VALUES(?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement pstmt;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rackTotCnt);
			pstmt.setString(2, stationName);
			pstmt.setInt(3, parkingBikeTotCnt);
			pstmt.setInt(4, shared);
			pstmt.setDouble(5, stationLatitude);
			pstmt.setDouble(6, stationLongitude);
			pstmt.setString(7, stationId);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("데이터 삽입 성공");
			} else {
				System.out.println("데이터 삽입 실패");
			}

		} catch (SQLException e) {
			System.out.println("데이터 삽입 실패");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		String key = "786a6b78486b616e39316376734d53";
		String result = "";
		List<RentBikeStatus> bikeStatusList = new ArrayList<>();

		try {
			URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/bikeList/1001/2000");

			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject rentBikeJson = (JSONObject) jsonObject.get("rentBikeStatus");
			JSONArray rowArray = (JSONArray) rentBikeJson.get("row");

			for (int i = 0; i < rowArray.size(); i++) {
				JSONObject rowJson = (JSONObject) rowArray.get(i);
				RentBikeStatus rentBikeStatus = new RentBikeStatus();

				// Exception in thread "main" java.lang.ClassCastException: class
				// java.lang.String cannot be cast to class java.lang.Number (java.lang.String
				// and java.lang.Number are in module java.base of loader 'bootstrap')
				// at system.BikeDb.main(BikeDb.java:98)

				String ractToCnt = (String) rowJson.get("rackTotCnt");
				int ractToCntInt = Integer.parseInt(ractToCnt);
				rentBikeStatus.setRackToCnt(ractToCntInt);

				rentBikeStatus.setStationName(((String) rowJson.get("stationName")));

				String parkingBikeTotCnt = (String) rowJson.get("parkingBikeTotCnt");
				int parkingBikeToCntInt = Integer.parseInt(parkingBikeTotCnt);
				rentBikeStatus.setParkingBikeTotCnt(parkingBikeToCntInt);

				rentBikeStatus.setShared(Integer.parseInt((String) (rowJson.get("shared"))));
				rentBikeStatus.setStationLatitude(Double.parseDouble((String) (rowJson.get("stationLatitude"))));
				rentBikeStatus.setStationLongitude(Double.parseDouble((String) (rowJson.get("stationLongitude"))));
				rentBikeStatus.setStationId((String) rowJson.get("stationId"));

				bikeStatusList.add(rentBikeStatus);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		BikeDb bikeDb = new BikeDb();

		for (RentBikeStatus rentBike : bikeStatusList) {
			bikeDb.insertIntoSql(rentBike);
		}
		System.out.println("작업종료");
	}
}
