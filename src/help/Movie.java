package help;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Movie {

//	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// kobis 사이트에서 받아온 나의 KEY 값을 입력
		String key = "57596eca19ce873c08f9d8690677b40a";
		String result = "";

		// URL 타입의 클래스 선언 ( kobis 사이트의 영화 정보를 가져오는 url )
		try {
			URL url = new URL("http://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=" + key
					+ "&movieCd=20124039");

			// url에 있는 openStream() 메서드로 스트림을 생성. utf-8 타입으로 인코딩
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			result = bf.readLine();

			// jsonParser : json 타입의 파일을 parsing 해주는 클래스
			JSONParser jsonParser = new JSONParser();

			// JSONObject 타입의 객체를 만들어서 result로 읽어온 값을 parsing 함.
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
			JSONObject movieInfoResult = (JSONObject) jsonObject.get("movieInfoResult");
			JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");

			// movieInfo 안에 배열로 정보가 담겨있는 nations, directors, genres 등은 배열에 접속하여 출력해야 함
			// 영화명, 상영 시간, 개봉일 등의 정보 출력
			System.out.println("영화명 : " + movieInfo.get("movieNm"));
			System.out.println("영화명(EN) : " + movieInfo.get("movieNmEn"));
			System.out.println("상영 시간 : " + movieInfo.get("showTm") + "분");
			System.out.println("개봉일 : " + movieInfo.get("openDt"));

			// nations(국가) 정보를 출력
			JSONArray nations = (JSONArray) movieInfo.get("nations");
			JSONObject nations_nationNm = (JSONObject) nations.get(0);
			System.out.println("JSONArray 제작 국가 : " + nations.get(0));
			System.out.println("제작 국가 : " + nations_nationNm.get("nationNm"));

			// directors(감독) 정보를 출력
			JSONArray directors = (JSONArray) movieInfo.get("directors");
			JSONObject directors_peopleNm = (JSONObject) directors.get(0);
			System.out.println("감독 : " + directors_peopleNm.get("peopleNm"));

			// genres(장르) 정보를 출력
			JSONArray genres = (JSONArray) movieInfo.get("genres");
			JSONObject genres_genresNm = (JSONObject) genres.get(0);
			System.out.println("장르 : " + genres_genresNm.get("genreNm"));

			// 배우 정보를 출력
			JSONArray actors = (JSONArray) movieInfo.get("actors");

			// for문 사용
			String peopleNm = "";
			for (int i = 0; i < actors.size(); i++) {
				JSONObject actors_peopleNm = (JSONObject) actors.get(i);
				peopleNm += actors_peopleNm.get("peopleNm") + " ";
			}
			System.out.println("배우 : " + peopleNm);

			// 향상된 for문 사용
			StringBuilder peopleNm2 = new StringBuilder();
			for (Object actor : actors) {
				JSONObject actorObj = (JSONObject) actor;
				peopleNm2.append(actorObj.get("peopleNm")).append(" ");
			}
			System.out.println("배우 : " + peopleNm2.toString());

			// stream 사용
			Stream<String> ac = actors.stream().map(actor -> ((JSONObject) actor).get("peopleNm"));
			String actorString = ac.collect(Collectors.joining(", "));

			String peopleNm3 = (String) actors.stream().map(actor -> ((JSONObject) actor).get("peopleNm"))
					.collect(Collectors.joining(", "));
			System.out.println("배우 : " + peopleNm3);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
