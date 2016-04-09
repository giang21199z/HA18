package com.giangnd_svmc.ha18.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by admin on 4/9/2016.
 */
public class JsonParser {
    private HttpURLConnection connection;
    private BufferedReader bufferedReader;

    public JSONObject getJsonFromUrl(String myURL) {
        try {
            URL url = new URL(myURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            String fromSever = stringBuilder.toString();
            JSONObject jsonObjectFromSever = new JSONObject(fromSever);
            return jsonObjectFromSever;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
