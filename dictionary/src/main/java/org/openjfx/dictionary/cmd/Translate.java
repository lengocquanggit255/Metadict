package org.openjfx.dictionary.cmd;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Translate {
    public static String googleTranslate(String langFrom, String langTo, String text)
            throws IOException, InterruptedException {
        // Construct the URL for the Google Translate script with query parameters
        String urlScript = "https://script.google.com/macros/s/AKfycbx7_ch9fVT0OpGJ3-lGOt4Byvt3N9hkkwSdsEDZ0XTrjNduT2v1nE9XGAAHEF-qUrdz5A/exec"
                +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;

        // Create an HttpClient instance
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();

        // Create an HttpRequest object with the URL and set the User-Agent header
        HttpRequest request = HttpRequest.newBuilder(URI.create(urlScript)).header("User-Agent", "Mozilla/5.0").build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response status code is 200 (OK)
        if (response.statusCode() == 200) {
            return response.body();
        }
        // If the response status code is 302 (Found/Redirect), handle the redirect
        else if (response.statusCode() == 302) {
            // Get the new location from the "Location" header
            String redirectUrl = response.headers().firstValue("Location").orElse("");

            // Create a new request to the redirect location
            request = HttpRequest.newBuilder(URI.create(redirectUrl)).header("User-Agent", "Mozilla/5.0").build();

            // Send the new request and get the response
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the translated text from the updated response
            return response.body();
        }
        // If the response status code is neither 200 nor 302, throw an exception
        else {
            throw new IOException("Translation request failed with status code: " + response.statusCode());
        }
    }
}
