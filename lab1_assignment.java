package com.mycompany.report_1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Report_1_2 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://webcode.com/");
        getRequestMaking(url);
    }

    // https://.me/
    // https://github.com/
    // https://stackoverflow.com/
    //
    private static void getRequestMaking(URL url) throws IOException {
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("User-Agent", "Chrome");

        int response = connect.getResponseCode();
        System.out.println("Response Code " + response);
        System.out.println("Response message " + connect.getResponseMessage());
        System.out.println();

        if (response == HttpURLConnection.HTTP_OK) {
            BufferedReader read = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = read.readLine()) != null) {
                str.append(line);
            }

            read.close();

            Pattern pattern = Pattern.compile("<p>(.*?)</p>");
            Matcher matcher = pattern.matcher(str.toString());

            while (matcher.find()) {
                System.out.println(matcher.group(1));
            }

            System.out.println();
        }
    }
}