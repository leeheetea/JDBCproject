package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public void insertIntoSql(RentBikeStatus bikeObject) {

		int rackTotCnt = bikeObject.getRackTotCnt();
		String stationName = bikeObject.getStationName();
		int parkingBikeTotCnt = bikeObject.getParkingBikeTotCnt();
		int shared = bikeObject.getShared();
		double stationLatitude = bikeObject.getStationLatitude();
		double stationLongitude = bikeObject.getStationLongitude();
		String stationId = bikeObject.getStationId();

		String sql = "INSERT INTO rentbikestatus \r\n"
				+ "	(rackTotCnt, stationName, parkingBikeTotCnt, shared, stationLatitude, stationLongitude, stationId)\r\n"
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
	
	public List<RentBikeStatus> readToSql(int pageNum) {
		String sql = "SELECT * FROM mydb.rentbikestatus WHERE rownum BETWEEN ? AND ?";
		List<RentBikeStatus> list = new ArrayList<>();
		int startRownum = 1 + (pageNum-1) * 10;
		int endRownum = pageNum * 10;
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, startRownum);
			pstm.setInt(2, endRownum);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int rackTotCnt = rs.getInt("rackTotCnt");
				String stationName = rs.getString("stationName");
				int parkingBikeTotCnt = rs.getInt("parkingBikeTotCnt");
				int shared = rs.getInt("shared");
				double stationLatitude = rs.getDouble("stationLatitude");
				double stationLongitude = rs.getDouble("stationLongitude");
				String stationId = rs.getString("stationId");
				
				RentBikeStatus rentBikeStatus = new RentBikeStatus(rackTotCnt, stationName, parkingBikeTotCnt, shared, stationLatitude, stationLongitude, stationId);
				list.add(rentBikeStatus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateToSql(RentBikeStatus bikeObject) {
		int rackTotCnt = bikeObject.getRackTotCnt();
		String stationName = bikeObject.getStationName();
		int parkingBikeTotCnt = bikeObject.getParkingBikeTotCnt();
		int shared = bikeObject.getShared();
		double stationLatitude = bikeObject.getStationLatitude();
		double stationLongitude = bikeObject.getStationLongitude();
		String stationId = bikeObject.getStationId();
		
		String sql = "UPDATE rentbikestatus SET rackTotCnt = ?, stationName = ?, parkingBikeToCnt = ?, shared = ?, stationLatitude = ?, stationLongitude = ?, stationId =?"
		+ "WHERE staionId = " + stationId;
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
			if(result > 0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
	public void deleteFromSql(String stationId) {
		String stationNumber = stationId.trim();
		
		String sql = "DELETE FROM book WHERE bookid = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stationNumber);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCount() {
		int dataCount = 0;
		String sql = "SELECT COUNT(rownum) FROM rentbikestatus";
		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			dataCount = rs.getInt("COUNT(rownum)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dataCount;
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

				String rackTotCnt = (String) rowJson.get("rackTotCnt");
				int rackToCntInt = Integer.parseInt(rackTotCnt);
				rentBikeStatus.setRackToCnt(rackToCntInt);

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
