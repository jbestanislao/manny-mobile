package manny.com.androidapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rey on 02/09/2017.
 */

public class SaveTask extends AsyncTask<String, Void, Void> {
    private static final String POST_URL = "http://192.168.0.106:9080/amex/data";

    @Override
    protected Void doInBackground(String... input) {
        System.out.println(input[0] + ", " + input[1]);
        try {
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", input[0]);
            jsonParam.put("value", input[1]);

            postJSONObjectFromURL(POST_URL, jsonParam);

        } catch (JSONException | IOException je) {
            je.printStackTrace();
        }
        return null;
    }

    public static void postJSONObjectFromURL(String urlString, JSONObject jsoData) throws IOException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.connect();

        OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
        out.write(jsoData.toString());
        out.close();

        int HttpResult =urlConnection.getResponseCode();
        if(HttpResult ==HttpURLConnection.HTTP_OK) {
            System.out.println("Successfully saved the data");
        } else {
            System.out.println("Failed to  saved the data");
        }
    }
}
