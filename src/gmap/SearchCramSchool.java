package gmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/*
 * 完整內容參考：
 * https://developers.google.com/places/web-service/search?hl=zh-tw#PlaceSearchRequests
 */
public class SearchCramSchool {
  /*
   * 參數說明：
   * location：定位點 (緯經度)
   * radius：要搜尋的半徑距離 (公尺，最大為50,000公尺)
   * query：要搜尋的地點關鍵字與其比對關聯結果
   * language：搜尋結果以何種語言回傳
   * key：請換成自己的"伺服器金鑰"
   */

  public static void main(String[] args) throws Exception {
    // 附近地點搜尋
    String[] locArray = {"23.310019,120.127536",
        "23.405417,120.437472",
        "23.089177,120.084688",
        "23.315737,120.146113",
        "23.321455,120.164690",
        "23.327173,120.183267",
        "23.332891,120.201843",
        "23.338609,120.220420",
        "23.344327,120.238997",
        "23.350045,120.257574",
        "23.355762,120.276151",
        "23.361480,120.294728",
        "23.367198,120.313304",
        "23.372916,120.331881",
        "23.378634,120.350458",
        "23.384352,120.369035",
        "23.390070,120.387612",
        "23.395788,120.406189",
        "23.401506,120.424765",
        "23.292311,120.124100",
        "23.274604,120.120665",
        "23.256896,120.117229",
        "23.239188,120.113793",
        "23.221480,120.110358",
        "23.203773,120.106922",
        "23.186065,120.103486",
        "23.168357,120.100051",
        "23.150649,120.096615",
        "23.132942,120.093179",
        "23.115234,120.089744",
        "23.097526,120.086308",
        "23.298029,120.142677",
        "23.280321,120.139241",
        "23.262614,120.135806",
        "23.244906,120.132370",
        "23.227198,120.128934",
        "23.209491,120.125499",
        "23.191783,120.122063",
        "23.174075,120.118627",
        "23.156367,120.115192",
        "23.138660,120.111756",
        "23.120952,120.108320",
        "23.103244,120.104885",
        "23.085536,120.101449",
        "23.067829,120.098013",
        "23.050121,120.094578",
        "23.303747,120.161254",
        "23.286039,120.157818",
        "23.268332,120.154383",
        "23.250624,120.150947",
        "23.232916,120.147511",
        "23.215208,120.144076",
        "23.197501,120.140640",
        "23.179793,120.137204",
        "23.162085,120.133769",
        "23.144378,120.130333",
        "23.126670,120.126897",
        "23.108962,120.123462",
        "23.091254,120.120026",
        "23.073547,120.116590",
        "23.055839,120.113155",
        "23.038131,120.109719",
        "23.309465,120.179831",
        "23.291757,120.176395",
        "23.274050,120.172960",
        "23.256342,120.169524",
        "23.238634,120.166088",
        "23.220926,120.162652",
        "23.203219,120.159217",
        "23.185511,120.155781",
        "23.167803,120.152345",
        "23.150095,120.148910",
        "23.132388,120.145474",
        "23.114680,120.142038",
        "23.096972,120.138603",
        "23.079265,120.135167",
        "23.061557,120.131731",
        "23.043849,120.128296",
        "23.026141,120.124860",
        "23.315183,120.198408",
        "23.297475,120.194972",
        "23.279768,120.191536",
        "23.262060,120.188101",
        "23.244352,120.184665",
        "23.226644,120.181229",
        "23.208937,120.177794",
        "23.191229,120.174358",
        "23.173521,120.170922",
        "23.155813,120.167487",
        "23.138106,120.164051",
        "23.120398,120.160615",
        "23.102690,120.157180",
        "23.084982,120.153744",
        "23.067275,120.150308",
        "23.049567,120.146873",
        "23.031859,120.143437",
        "23.320901,120.216985",
        "23.303193,120.213549",
        "23.285485,120.210113",
        "23.267778,120.206678",
        "23.250070,120.203242",
        "23.232362,120.199806",
        "23.214655,120.196370",
        "23.196947,120.192935",
        "23.179239,120.189499",
        "23.161531,120.186063",
        "23.143824,120.182628",
        "23.126116,120.179192",
        "23.108408,120.175756",
        "23.090700,120.172321",
        "23.072993,120.168885",
        "23.055285,120.165449",
        "23.037577,120.162014",
        "23.019869,120.158578",
        "23.002162,120.155142",
        "22.984454,120.151707",
        "23.326619,120.235561",
        "23.308911,120.232126",
        "23.291203,120.228690",
        "23.273496,120.225254",
        "23.255788,120.221819",
        "23.238080,120.218383",
        "23.220372,120.214947",
        "23.202665,120.211512",
        "23.184957,120.208076",
        "23.167249,120.204640",
        "23.149542,120.201205",
        "23.131834,120.197769",
        "23.114126,120.194333",
        "23.096418,120.190898",
        "23.078711,120.187462",
        "23.061003,120.184026",
        "23.043295,120.180591",
        "23.025587,120.177155",
        "23.007880,120.173719",
        "22.990172,120.170284",
        "22.972464,120.166848",
        "23.332337,120.254138",
        "23.314629,120.250703",
        "23.296921,120.247267",
        "23.279214,120.243831",
        "23.261506,120.240396",
        "23.243798,120.236960",
        "23.226090,120.233524",
        "23.208383,120.230088",
        "23.190675,120.226653",
        "23.172967,120.223217",
        "23.155259,120.219781",
        "23.137552,120.216346",
        "23.119844,120.212910",
        "23.102136,120.209474",
        "23.084429,120.206039",
        "23.066721,120.202603",
        "23.049013,120.199167",
        "23.031305,120.195732",
        "23.013598,120.192296",
        "22.995890,120.188860",
        "22.978182,120.185425",
        "22.960474,120.181989",
        "22.942767,120.178553",
        "23.338055,120.272715",
        "23.320347,120.269279",
        "23.302639,120.265844",
        "23.284932,120.262408",
        "23.267224,120.258972",
        "23.249516,120.255537",
        "23.231808,120.252101",
        "23.214101,120.248665",
        "23.196393,120.245230",
        "23.178685,120.241794",
        "23.160977,120.238358",
        "23.143270,120.234923",
        "23.125562,120.231487",
        "23.107854,120.228051",
        "23.090146,120.224616",
        "23.072439,120.221180",
        "23.054731,120.217744",
        "23.037023,120.214309",
        "23.019316,120.210873",
        "23.001608,120.207437",
        "22.983900,120.204002",
        "22.966192,120.200566",
        "22.948485,120.197130",
        "22.930777,120.193695",
        "23.343773,120.291292",
        "23.326065,120.287856",
        "23.308357,120.284421",
        "23.290649,120.280985",
        "23.272942,120.277549",
        "23.255234,120.274114",
        "23.237526,120.270678",
        "23.219819,120.267242",
        "23.202111,120.263807",
        "23.184403,120.260371",
        "23.166695,120.256935",
        "23.148988,120.253499",
        "23.131280,120.250064",
        "23.113572,120.246628",
        "23.095864,120.243192",
        "23.078157,120.239757",
        "23.060449,120.236321",
        "23.042741,120.232885",
        "23.025033,120.229450",
        "23.007326,120.226014",
        "22.989618,120.222578",
        "22.971910,120.219143",
        "22.954203,120.215707",
        "22.936495,120.212271",
        "22.918787,120.208836",
        "23.349491,120.309869",
        "23.331783,120.306433",
        "23.314075,120.302997",
        "23.296367,120.299562",
        "23.278660,120.296126",
        "23.260952,120.292690",
        "23.243244,120.289255",
        "23.225536,120.285819",
        "23.207829,120.282383",
        "23.190121,120.278948",
        "23.172413,120.275512",
        "23.154706,120.272076",
        "23.136998,120.268641",
        "23.119290,120.265205",
        "23.101582,120.261769",
        "23.083875,120.258334",
        "23.066167,120.254898",
        "23.048459,120.251462",
        "23.030751,120.248027",
        "23.013044,120.244591",
        "22.995336,120.241155",
        "22.977628,120.237720",
        "22.959920,120.234284",
        "22.942213,120.230848",
        "22.924505,120.227413",
        "23.355209,120.328446",
        "23.337501,120.325010",
        "23.319793,120.321574",
        "23.302085,120.318139",
        "23.284378,120.314703",
        "23.266670,120.311267",
        "23.248962,120.307832",
        "23.231254,120.304396",
        "23.213547,120.300960",
        "23.195839,120.297525",
        "23.178131,120.294089",
        "23.160423,120.290653",
        "23.142716,120.287217",
        "23.125008,120.283782",
        "23.107300,120.280346",
        "23.089593,120.276910",
        "23.071885,120.273475",
        "23.054177,120.270039",
        "23.036469,120.266603",
        "23.018762,120.263168",
        "23.001054,120.259732",
        "22.983346,120.256296",
        "22.965638,120.252861",
        "22.947931,120.249425",
        "22.930223,120.245989",
        "22.912515,120.242554",
        "23.360926,120.347022",
        "23.343219,120.343587",
        "23.325511,120.340151",
        "23.307803,120.336715",
        "23.290096,120.333280",
        "23.272388,120.329844",
        "23.254680,120.326408",
        "23.236972,120.322973",
        "23.219265,120.319537",
        "23.201557,120.316101",
        "23.183849,120.312666",
        "23.166141,120.309230",
        "23.148434,120.305794",
        "23.130726,120.302359",
        "23.113018,120.298923",
        "23.095310,120.295487",
        "23.077603,120.292052",
        "23.059895,120.288616",
        "23.042187,120.285180",
        "23.024480,120.281745",
        "23.006772,120.278309",
        "22.989064,120.274873",
        "22.971356,120.271438",
        "22.953649,120.268002",
        "22.935941,120.264566",
        "22.918233,120.261131",
        "23.366644,120.365599",
        "23.348937,120.362164",
        "23.331229,120.358728",
        "23.313521,120.355292",
        "23.295813,120.351857",
        "23.278106,120.348421",
        "23.260398,120.344985",
        "23.242690,120.341550",
        "23.224983,120.338114",
        "23.207275,120.334678",
        "23.189567,120.331243",
        "23.171859,120.327807",
        "23.154152,120.324371",
        "23.136444,120.320935",
        "23.118736,120.317500",
        "23.101028,120.314064",
        "23.083321,120.310628",
        "23.065613,120.307193",
        "23.047905,120.303757",
        "23.030197,120.300321",
        "23.012490,120.296886",
        "22.994782,120.293450",
        "22.977074,120.290014",
        "22.959367,120.286579",
        "22.941659,120.283143",
        "22.923951,120.279707",
        "22.906243,120.276272",
        "23.372362,120.384176",
        "23.354655,120.380740",
        "23.336947,120.377305",
        "23.319239,120.373869",
        "23.301531,120.370433",
        "23.283824,120.366998",
        "23.266116,120.363562",
        "23.248408,120.360126",
        "23.230700,120.356691",
        "23.212993,120.353255",
        "23.195285,120.349819",
        "23.177577,120.346384",
        "23.159870,120.342948",
        "23.142162,120.339512",
        "23.124454,120.336077",
        "23.106746,120.332641",
        "23.089039,120.329205",
        "23.071331,120.325770",
        "23.053623,120.322334",
        "23.035915,120.318898",
        "23.018208,120.315463",
        "23.000500,120.312027",
        "22.982792,120.308591",
        "22.965084,120.305156",
        "22.947377,120.301720",
        "22.929669,120.298284",
        "22.911961,120.294849",
        "23.378080,120.402753",
        "23.360373,120.399317",
        "23.342665,120.395882",
        "23.324957,120.392446",
        "23.307249,120.389010",
        "23.289542,120.385575",
        "23.271834,120.382139",
        "23.254126,120.378703",
        "23.236418,120.375268",
        "23.218711,120.371832",
        "23.201003,120.368396",
        "23.183295,120.364961",
        "23.165587,120.361525",
        "23.147880,120.358089",
        "23.130172,120.354654",
        "23.112464,120.351218",
        "23.094757,120.347782",
        "23.077049,120.344346",
        "23.059341,120.340911",
        "23.041633,120.337475",
        "23.023926,120.334039",
        "23.006218,120.330604",
        "22.988510,120.327168",
        "22.970802,120.323732",
        "22.953095,120.320297",
        "22.935387,120.316861",
        "22.917679,120.313425",
        "23.383798,120.421330",
        "23.366090,120.417894",
        "23.348383,120.414458",
        "23.330675,120.411023",
        "23.312967,120.407587",
        "23.295260,120.404151",
        "23.277552,120.400716",
        "23.259844,120.397280",
        "23.242136,120.393844",
        "23.224429,120.390409",
        "23.206721,120.386973",
        "23.189013,120.383537",
        "23.171305,120.380102",
        "23.153598,120.376666",
        "23.135890,120.373230",
        "23.118182,120.369795",
        "23.100474,120.366359",
        "23.082767,120.362923",
        "23.065059,120.359488",
        "23.047351,120.356052",
        "23.029644,120.352616",
        "23.011936,120.349181",
        "22.994228,120.345745",
        "22.976520,120.342309",
        "22.958813,120.338874",
        "22.941105,120.335438",
        "22.923397,120.332002",
        "22.905689,120.328567",
        "23.387709,120.434036",
        "23.370002,120.430601",
        "23.352294,120.427165",
        "23.334586,120.423729",
        "23.316878,120.420294",
        "23.299171,120.416858",
        "23.281463,120.413422",
        "23.263755,120.409987",
        "23.246047,120.406551",
        "23.228340,120.403115",
        "23.210632,120.399680",
        "23.192924,120.396244",
        "23.175216,120.392808",
        "23.157509,120.389373",
        "23.139801,120.385937",
        "23.122093,120.382501",
        "23.104386,120.379066",
        "23.086678,120.375630",
        "23.068970,120.372194",
        "23.051262,120.368759",
        "23.033555,120.365323",
        "23.015847,120.361887",
        "22.998139,120.358452",
        "22.980431,120.355016",
        "22.962724,120.351580",
        "22.945016,120.348144",
        "22.927308,120.344709",
        "22.909600,120.341273",
        "22.891893,120.337837"}; // 緯經度
    int radius = 1000; // 半徑(m)
    String keyword = "課後照顧";
    String language = "zh-TW";
    String key = "AIzaSyAlXxtjmxQQ1RxEWFvZDyZQ32jI4v-X-J4";
    int count = 0;
    List<Map<String, Object>> outcome = new ArrayList<Map<String, Object>>();

    for (String location : locArray) {
      // 開始前先標註查詢的緯經度
      count++;
      Map<String, Object> mapTitle = new LinkedHashMap<String, Object>();
      mapTitle.put("name", "第" + count + "次查詢");
      mapTitle.put("address", "使用緯經度：");
      mapTitle.put("latLng", location);
      mapTitle.put("businessStatus", "   ");
      mapTitle.put("isOpenging", "   ");
      mapTitle.put("rating", "   ");
      mapTitle.put("userRatingNum", "   ");
      mapTitle.put("types", "   ");

      outcome.add(mapTitle);
      
      boolean needSearch = true;
      String pagetoken = "";
      String SEARCH_PLACE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?" + "location=" + location + "&radius=" + radius + "&query=" + keyword + "&language=" + language + "&key=" + key;

      // 迴圈前確認是否需要查詢
      while (needSearch) {
        System.out.println("開始查詢" + needSearch);
        // 查詢前休息3秒
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        // 讀取資料
        Gson gson = new Gson();
        // 讀回json字串
        InputStream is = new URL(SEARCH_PLACE_URL + "&pagetoken=" + pagetoken).openStream();
        System.out.println("SEARCH_PLACE_URL= \n" + SEARCH_PLACE_URL + "&pagetoken=" + pagetoken);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null)
          sb.append(str);
        br.close();
        is.close();

        // 解析json內容
        if (sb.length() > 0) {
          JsonObject jObj = gson.fromJson(sb.toString(), JsonObject.class);
          // 確認有無下一頁
          try {
            pagetoken = jObj.get("next_page_token").getAsString();
            needSearch = true;
            System.out.println("有pagetoken： " + pagetoken);
          } catch (Exception e) {
            System.out.println("no pagetoken");
            needSearch = false; // 準備結束查詢迴圈
          }

          JsonArray jArray = jObj.get("results").getAsJsonArray();
          if (jArray.size() > 0) { // 有查詢結果才進行解析
            for (int i = 0; i < jArray.size(); i++) {
              JsonObject obj = jArray.get(i).getAsJsonObject();
              // 解析各欄位
              String objLng = obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsString(); // 經度
              String objLat = obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsString(); // 緯度
              String objLatLng = objLat + "," + objLng; // 緯經度
              String objName = obj.get("name").getAsString(); // 名稱
              String objAddress = obj.get("formatted_address").getAsString(); // 地址
              String objBusinessStatus = obj.get("business_status").getAsString(); // 經營狀態
              String objIsOpening = null;
              if (obj.has("opening_hours")) {
                JsonObject objOpeningHours = obj.get("opening_hours").getAsJsonObject();
                if (objOpeningHours.has("open_now")) {
                  objIsOpening = objOpeningHours.get("open_now").getAsBoolean() ? "營業中" : "休息中"; // 是否營業中
                }
              }
              String objRating = null;
              if (obj.has("rating")) {
                objRating = obj.get("rating").getAsString(); // 總評分數
              }
              String objUserRatingNum = null;
              if (obj.has("user_ratings_total")) {
                objUserRatingNum = obj.get("user_ratings_total").getAsString(); // 總評分人數
              }
              JsonArray jTypeArray = obj.get("types").getAsJsonArray();
              StringBuilder typeArray = new StringBuilder();
              if (jTypeArray != null && jTypeArray.size() > 0) {
                for (int j = 0; j < jTypeArray.size(); j++) {
                  typeArray.append(jTypeArray.get(j).getAsString()).append(",");
                }
              }

              // 將欄位值存入準備寫入excel的map
              Map<String, Object> map = new LinkedHashMap<String, Object>();
              map.put("name", objName);
              map.put("address", objAddress);
              map.put("latLng", objLatLng);
              map.put("businessStatus", objBusinessStatus);
              map.put("isOpenging", objIsOpening);
              map.put("rating", objRating);
              map.put("userRatingNum", objUserRatingNum);
              map.put("types", typeArray.toString());

              outcome.add(map);

            }
          } else { // 無查詢結果
            System.out.println("no results");
            needSearch = false; // 準備結束查詢迴圈
          }

          // 結束查詢做excel分隔線
          if (!needSearch) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("name", "=========");
            map.put("address", "=========");
            map.put("latLng", "=========");
            map.put("businessStatus", "=========");
            map.put("isOpenging", "=========");
            map.put("rating", "=========");
            map.put("userRatingNum", "=========");
            map.put("types", "====================================");

            outcome.add(map);
          }
        }
      }
    }

    // 產生excel
    genFile(outcome);

  }

  public static boolean genFile(List<Map<String, Object>> list) throws Exception {
    String excelPathName = "20200926" + "_TNN_" + "SearchAfterSchoolCareCenter" + ".xls";

    LinkedHashMap<String, String> colMap = new LinkedHashMap<String, String>();
    colMap.put("name", "名稱");
    colMap.put("address", "地址");
    colMap.put("latLng", "緯經度");
    colMap.put("businessStatus","經營狀態");
    colMap.put("isOpenging", "營業狀態");
    colMap.put("rating", "總評分數");
    colMap.put("userRatingNum", "評分人次");
    colMap.put("types", "搜尋類型");

    boolean genResult = createExcel(colMap, list, excelPathName);

    return genResult;
  }

  /**
   * 產出excel，存至{指定路徑excelPathName}
   * 
   * @param colMap
   *          (注意順序)
   *          </br>
   *          ex : [name : "名稱", address : "地址", latLng : "緯經度", isOpenging : "營業狀態"]
   * @param rowData
   *          </br>
   *          ex : [{name : "XX補習班", address : "XX市XX路", latLng : "23.5,23.5", isOpenging :"Y"},</br>
   *          {name : "XX補習班", address : "XX市XX路", latLng : "23.5,23.5", isOpenging :"Y"}]
   * @param excelPathName
   *          </br>
   *          ex : test123/20200312_SearchCramSchool.xls
   * 
   * @return boolean TRUE:產生成功 FALSE:產生失敗
   */
  public static boolean createExcel(LinkedHashMap<String, String> colMap, List<Map<String, Object>> rowData, String excelPathName) {
    FileOutputStream fos = null;
    HSSFWorkbook workbook = null;
    try {

      /* 基本輸入檢核 */
      if (colMap == null || colMap.isEmpty() || rowData == null || rowData.size() == 0 || excelPathName == null || !excelPathName.endsWith(".xls")) {
        return false;
      }

      /* Excel初始化 */
      File file = new File("/test123/" + excelPathName);
      File parent = file.getParentFile();
      if (!parent.exists()) {
        parent.mkdirs();
      }
      fos = new FileOutputStream(file);
      workbook = new HSSFWorkbook();
      HSSFSheet sheet = workbook.createSheet("sheet");

      /* 欄位格式 */
      HSSFCellStyle dateCellStyle = workbook.createCellStyle();
      short df = workbook.createDataFormat().getFormat("yyyy/mm/dd");
      dateCellStyle.setDataFormat(df);

      /* 表格各欄標題 */
      HSSFRow titleRow = sheet.createRow(0);
      int colIndex = 0;
      for (String colKey : colMap.keySet()) {
        titleRow.createCell(colIndex).setCellValue(colMap.get(colKey));
        colIndex++;
      }
      colIndex = 0;

      /* 表格值 */
      for (int i = 1; i < rowData.size() + 1; i++) {
        Map<String, Object> dataMap = rowData.get(i - 1);
        HSSFRow dataRow = sheet.createRow(i);
        for (String colKey : colMap.keySet()) {
          Object cellData = dataMap.get(colKey);
          if (cellData != null) {
            HSSFCell dataCell = dataRow.createCell(colIndex);
            if (cellData instanceof Integer) {
              dataCell.setCellValue(((Integer) cellData).doubleValue());
            } else if (cellData instanceof BigDecimal) {
              dataCell.setCellValue(((BigDecimal) cellData).doubleValue());
            } else if (cellData instanceof Date) {
              dataCell.setCellStyle(dateCellStyle);
              dataCell.setCellValue(((Date) cellData));
            } else {
              if (!"".equals(cellData.toString())) {
                dataCell.setCellValue(cellData.toString());
              }
            }
          }
          colIndex++;
        }
        colIndex = 0;
      }

      workbook.write(fos);
      fos.close();
      fos.flush();
      workbook.close();
      workbook = null;
      return true;
    } catch (Exception e) {
      if (fos != null) {
        try {
          fos.close();
          fos.flush();
        } catch (IOException e1) {
        }
      }
      if (workbook != null) {
        try {
          workbook.close();
          workbook = null;
        } catch (Exception e2) {
        }
      }
      return false;
    }
  }
}
