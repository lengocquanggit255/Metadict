package org.openjfx.dictionary;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import java.io.ByteArrayInputStream;

import javax.sound.sampled.*;

import org.openjfx.dictionary.cmd.Dictionary;

public class Helper {
    public static Dictionary dictionary = new Dictionary();
    private static final int BUFFER_SIZE = 8192;

    public static void speak(String text) {
        // Set up voice parameters
        VoiceParameters params = new VoiceParameters(text, Languages.English_UnitedStates);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setVoice("Linda");
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
}