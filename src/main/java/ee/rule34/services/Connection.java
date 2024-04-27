package ee.rule34.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection extends Thread {
    Thread thread = new Thread(this);
    String targetUrl;
    String response;

    public void setUrl(String url) {
        this.targetUrl = url;
    }

    public String getResponse() {
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public void run() {
        while (this.response == null) {
            try {
                URL url = new URL(targetUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                if (connection.getResponseCode() == 200) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    this.response = String.valueOf(response);
                } else {
                    throw new RuntimeException("NOT 200 for : " + url);
                }
                connection.disconnect();
            } catch (IOException ignore) {
            }
        }
    }
}
