package gmap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * 完整內容參考：
 * https://developers.google.com/places/web-service/search?hl=zh-tw#PlaceSearchRequests
 */
public class GMaps_Nearby_Search {
	/*
	 * 參數說明： 
	 * location：定位點 (緯經度) 
	 * radius：要搜尋的半徑距離 (公尺，最大為50,000公尺) 
	 * type：要搜尋的地點種類   參考: https://developers.google.com/places/supported_types?hl=zh-tw#table1
	 * name：要搜尋的地點關鍵字與其比對關聯結果 
	 * language：搜尋結果以何種語言回傳 
	 * key：請換成自己的"伺服器金鑰"
	 */

	// 附近地點搜尋
	private static final String SEARCH_PLACE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
			+ "location=24.95375,121.22575" 
			+ "&radius=500" 
			+ "&types=food" 
			+ "&name=吃到飽" 
			+ "&language=zh-TW"
			+ "&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI";

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		// 讀回json字串
		InputStream is = new URL(SEARCH_PLACE_URL).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null)
			sb.append(str);
		br.close();
		
		// 解析json內容
		if (sb.length() > 0) {
			JsonObject jObj = gson.fromJson(sb.toString(), JsonObject.class);
			JsonArray jArray = jObj.get("results").getAsJsonArray();
			for (int i = 0; i < jArray.size(); i++) {
				JsonObject obj = jArray.get(i).getAsJsonObject();
				System.out.println("經度： " + obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject()
						.get("lng").getAsDouble());
				System.out.println("緯度： " + obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject()
						.get("lat").getAsDouble());
				System.out.println("地名： " + obj.get("name").getAsString());
				if (obj.has("opening_hours")) {
					boolean isOpening = obj.get("opening_hours").getAsJsonObject().get("open_now").getAsBoolean();
					System.out.println("是否營業中： " + (isOpening ? "是" : "否"));
				}
				if (obj.has("rating"))
					System.out.println("總評分數： " + obj.get("rating").getAsFloat());

				System.out.println("=====================");
			}
		}

	}

}
