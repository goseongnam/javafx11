package api;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;

public class Api {
    JSONArray contacts = null;
    public Double apiExecutuon(String SearchDate, String forex) throws MalformedURLException {
        String ret = "";

        String AuthKey = "AkkYyhYUysxtNQABF1j9pfh7wrjT5Pc6";
        String dataType = "AP01";
        String apiURL = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + AuthKey + "&searchdate=" + SearchDate + "&data=" + dataType;
        double value = 0d;
        try {
            URL oracle = new URL(apiURL);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String inputLine ="";
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(
                        inputLine);
            }
            ret = stringBuilder.toString();
            in.close();
            contacts = new JSONArray(ret);


            for(int i = 0; i < contacts.length(); i++){
                JSONObject c = contacts.getJSONObject(i);
                String unit = c.getString("cur_unit");
                if(unit.equals(forex)){
                    String bkpr = c.getString("bkpr");
                    value = Double.valueOf(bkpr);
                }
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}

