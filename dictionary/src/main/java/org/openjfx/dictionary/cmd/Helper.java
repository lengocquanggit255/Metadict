package org.openjfx.dictionary.cmd;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.sound.sampled.*;

public class Helper {
    public static Dictionary dictionary;
    public static void speak(String text, String language) throws Exception {
        String API_KEY = "3d5347f37b39439ba63c6d0f6ab6ae6b";
        String AUDIO_PATH = "D:\\Github\\OPP\\dictionary\\src\\main\\resources\\org\\openjfx\\dictionary\\audio\\audio.wav";
        double speed = 1;

        VoiceProvider tts = new VoiceProvider(API_KEY);
        VoiceParameters params = new VoiceParameters(text, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setLanguage(language);
        params.setRate((int) Math.round(-2.9936 * speed * speed + 15.2942 * speed - 12.7612));

        byte[] voice = tts.speech(params);

        FileOutputStream fos = new FileOutputStream(AUDIO_PATH);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(AUDIO_PATH))) {
            javax.sound.sampled.AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                line.open(format);
                line.start();

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                    line.write(buffer, 0, bytesRead);
                }

                line.drain();
            }
        }
    }

    public static String googleTranslate(String langFrom, String langTo, String text)
            throws IOException, InterruptedException {

        switch (langFrom) {
            case "en-us":
                langFrom = "en";
                break;

            case "vi-vn":
                langFrom = "vi";
                break;

            case "zh-cn":
                langFrom = "zh";
                break;

            default:
                break;
        }

        switch (langTo) {
            case "en-us":
                langTo = "en";
                break;

            case "vi-vn":
                langTo = "vi";
                break;

            case "zh-cn":
                langTo = "zh";
                break;

            default:
                break;
        }
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