import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/1574/");
            //opens a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode(); //gets the response code
            BufferedReader reader; //create a reader to read the response from the server line by line
            if (responseCode == HttpURLConnection.HTTP_OK) { //can also be written as == 200
                //gets the whole input stream (response) and adds it to the reader in case the connection is ok
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                //gets the response in case the connection has some error
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            //StringBuilder is a class similar to string, but works faster compared to String
            StringBuilder response = new StringBuilder();
            String line; //creates a variable to use for every new line in the response
            //while the reader's next line isn't empty it appends it to the response
            while ((line = reader.readLine()) != null) {
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