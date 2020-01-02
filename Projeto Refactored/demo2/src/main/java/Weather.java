/**
 * Escreva a descrição da classe Weather aqui.
 * 
 * 
 * @version (número de versão ou data)
 */

package main.java;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.net.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

import static java.lang.System.*;

public class Weather {

    private Weather(){}

    /**
     * COnstrutor para objetos da classe Weather
     */
    public static Map<String, Object> jsonToMap(String str) {
        return new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
    }
    /**
     * Calcurar percentagem de subida de preço dependendo do tempo
     */
    public static double getPercentage( double latitude, double longitude){
        String apiKey = "28c4efcbaf88fde7e5ae89d0b8f05dcd";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude +"&appid=" + apiKey;
        double percentage = 0.0;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = rd.readLine()) != null) {
                result .append(line);
            }
            rd.close();
            out.println(result);

            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            double temp =(Double) mainMap.get("temp");
            double hum = (Double) mainMap.get("humidity");
            double wSpeed = (Double) windMap.get("speed");
            double wAngle = (Double) windMap.get("deg");
            percentage = (temp*0.4/4 + hum*0.4/4 + wSpeed*0.15/4 + wAngle*0.05/4) / 100;
        } catch(IOException e){
            out.println(e.getMessage());
        }
        return percentage;
    }
}
