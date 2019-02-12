package com.seba.formfiller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class HTMLSourceCodeReader {

  public static Map<Integer, String> getMapOfInputFields(String url) throws IOException {
    URL urlObject = new URL(url);
    URLConnection urlConnection = urlObject.openConnection();
    urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

    return toString(urlConnection.getInputStream());
  }

  private static Map<Integer, String> toString(InputStream inputStream) throws IOException {
    Map<Integer, String> mapOfInputFields = new HashMap<>();
    String inputLine;
    int key = 0;

    try (BufferedReader bufferedReader =
                 new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
      while ((inputLine = bufferedReader.readLine()) != null) {
        if (inputLine.contains("<input") && (
                inputLine.contains("type=\"text\"") ||
                        inputLine.contains("type=\"password\"")
        )
        ) {
          mapOfInputFields.put(key++, idRetriver(inputLine));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mapOfInputFields;
  }

  private static String idRetriver(String input) {
    String result = "";
    String[] split = input.split(" ");

    for (String lookUp : split) {
      if (lookUp.contains("id=")) {
        result = lookUp.substring(4, lookUp.length() - 1);
      }
    }
    return result;
  }

}


