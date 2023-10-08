package org.openjfx.dictionary.cmd;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.sound.sampled.*;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class Helper {
    public static Dictionary dictionary = new Dictionary();
    private static final int BUFFER_SIZE = 8192;

    public static void speak(String text, String languages) {
        // Set up voice parameters
        VoiceParameters params = new VoiceParameters(text, languages);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate((int) Math.round(-2.9936 * 1 * 1 + 15.2942 * 1 - 12.7612));

        // Create a voice provider
        VoiceProvider voiceProvider = new VoiceProvider("0399b37a008a49dfa5cda0e4e6162552");

        try {
            // Perform the text-to-speech conversion
            byte[] voice = voiceProvider.speech(params);

            // Play the speech
            playAudio(voice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void playAudio(byte[] audioData) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData))) {
            javax.sound.sampled.AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                line.open(format);
                line.start();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;

                while ((bytesRead = audioStream.read(buffer, 0, buffer.length)) != -1) {
                    line.write(buffer, 0, bytesRead);
                }

                line.drain();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String googleTranslate(String langFrom, String langTo, String text) throws IOException, InterruptedException {
        // Construct the URL for the Google Translate script with query parameters
        String urlScript;
        urlScript = "https://script.google.com/macros/s/AKfycbw1qSfs1Hvfnoi3FzGuoDWijwQW69eGcMM_iGDF7p5vu1oN_CaFqIDFmCGzBuuGCk_N/exec"
                +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        // Create an HttpClient instance
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();

        // Create an HttpRequest object with the URL and set the User-Agent header
        HttpRequest request = HttpRequest.newBuilder(URI.create(urlScript)).header("User-Agent", "Mozilla/5.0")
                .build();

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

    private static String detectLanguage(String text) {
        try {
            // Create a detector
            Detector detector = DetectorFactory.create();

            // Detect the language of the text
            detector.setMaxTextLength(text.length()); // Set the maximum text length
            detector.append(text);
            String detectedLanguage = detector.detect(); // Perform language detection

            // Return the detected language
            return detectedLanguage;
        } catch (LangDetectException e) {
            e.printStackTrace();
            // Return a default language if an error occurs
            return "en";
        }
    }

    public static void main(String[] args) {
        String text = "Hello, how are you?";

        // Initialize language profiles
        try {
            DetectorFactory.loadProfile(
                    "D:/QuangWork/Github/OPP/dictionary/src/main/resources/org/openjfx/dictionary/profiles");
        } catch (LangDetectException e) {
            e.printStackTrace();
            return;
        }

        // Detect the language of the input text
        String detectedLanguage = detectLanguage(text);

        // Pass the text and detected language to the speak method
        speak(text, detectedLanguage);
    }
}