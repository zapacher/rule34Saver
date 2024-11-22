package ee.rule34.services;

import ee.rule34.Properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Saver {
    Properties properties = new Properties();
    String savePath = System.getProperty("user.home") + properties.getSaveFolder();
    public void saveFromLink(String link, String directory) {
        folderCheck(directory);
        String saveFilePath = savePath + directory + File.separator + link.substring(link.lastIndexOf("/") + 1);
        if (!fileCheck(saveFilePath)) {
            boolean run = true;
            while(run) {
                try {
                    URL url = new URL(link);
                    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                    httpConn.setRequestProperty("User-Agent", "Mozilla/5.0");
                    httpConn.setRequestProperty("Referer", "https://google.com");
                    int responseCode = httpConn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = httpConn.getInputStream();

                        FileOutputStream outputStream = new FileOutputStream(saveFilePath);
                        int bytesRead;

                        byte[] buffer = new byte[8192];

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        outputStream.close();
                        inputStream.close();
                        httpConn.disconnect();
                        run = false;
                        System.out.println("Saved " + directory + " file : " + saveFilePath);
                    }
                } catch (IOException ignore) {
                }
            }
        }
    }


    private void folderCheck(String directory) {
        File saveDirectory = new File(savePath+directory);
        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }
    }

    public boolean fileCheck(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}