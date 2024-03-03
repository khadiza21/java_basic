package com.mycompany.report1_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Report1_1 {

    private static URL postUrl;

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");       
      // String postData = "{\"name\":\"Khadiza\",\"job\":\"Software Engineer\"}";
      // postUrl = new URL("https://reqres.in/api/users");    
       String postData = "{\"title\":\"Khadiza\",\"completed\":false,\"userId\":1}";
       postUrl = new URL("https://jsonplaceholder.typicode.com/todos");
       
       getRequestMaking(url);
       postRequestMaking(postUrl, postData);
    }

    private static void getRequestMaking(URL url) throws IOException {
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.setRequestProperty("User-Agent", "Chrome");

        int response = connect.getResponseCode();
        System.out.println("Response Code " + response);
        System.out.println("Response message " + connect.getResponseMessage());

        if (response == HttpURLConnection.HTTP_OK) {
            BufferedReader read = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            StringBuffer str = new StringBuffer();
            String store = null;
            while ((store = read.readLine()) != null) {
                str.append(store);
            }
            read.close();
            System.out.println("response " + str.toString());
            System.out.println();
        }
    }

    private static void postRequestMaking(URL url, String postData) throws IOException {
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();

        connect.setRequestMethod("POST");
        connect.setRequestProperty("User-Agent", "Chrome");
        connect.setDoOutput(true);

        try (OutputStream outputStream = connect.getOutputStream()) {
            byte[] input = postData.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        int response = connect.getResponseCode();
        System.out.println("Response Code: " + response);

        System.out.println("Response Message: " + connect.getResponseMessage());

        if (response == HttpURLConnection.HTTP_OK || response == HttpURLConnection.HTTP_CREATED) {
            BufferedReader read = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            StringBuffer str = new StringBuffer();
            String store;

            while ((store = read.readLine()) != null) {
                str.append(store);
            }
            read.close();
            System.out.println("post resonse code");
            System.out.println("Response: " + str.toString());
            System.out.println("POST request is successful.");
            System.out.println();
        }else{
            System.out.println("POST request failed. Check the response code and message for details. ");
        }
    }
}
