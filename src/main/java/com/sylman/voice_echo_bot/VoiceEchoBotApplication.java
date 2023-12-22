package com.sylman.voice_echo_bot;

import java.io.FileInputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class VoiceEchoBotApplication {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("russianModels/ru.lm.bin");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

//        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
//        recognizer.startRecognition(true);
        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
        recognizer.startRecognition(new FileInputStream("woman.wav"));

        System.out.println("Recognition beginning...");


        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            String transcription = result.getHypothesis();
            System.out.println("Transription: " + transcription);
        }
        recognizer.stopRecognition();
    }
}





