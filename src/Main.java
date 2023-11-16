import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/1574/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //opens a connection to the URL
            connection.setRequestMethod("GET"); //what is a request method?
            int responseCode = connection.getResponseCode(); //gets the response code
            BufferedReader reader; //create a reader to read the response from the server
            if (responseCode == HttpURLConnection.HTTP_OK) { //can also be written as == 200
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //???
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream())); //???
            }

            StringBuilder response = new StringBuilder(); //???
            String line; //???
            while ((line = reader.readLine()) != null) { //while the reader has new line it appends to the StringBuilder???
                response.append(line);
            }
            reader.close(); //closes the reader
            connection.disconnect(); //closes the connection

            String sb = response.toString(); //converts the StringBuilder into String
            JSONObject jsonObject = new JSONObject(sb); //converts the String into JSONObject
            String code = jsonObject.getString("code");
            System.out.println(code);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}