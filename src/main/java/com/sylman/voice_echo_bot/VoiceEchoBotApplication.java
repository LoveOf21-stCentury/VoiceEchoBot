package com.sylman.voice_echo_bot;

import java.io.FileInputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class VoiceEchoBotApplication {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();

        // TODO: 12/26/2023 the path are laid incorrectly, the project is under development 
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);
//        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
//        recognizer.startRecognition(new FileInputStream("woman.wav"));
        // TODO: 12/26/2023 path to voice woman.wav is real. audio is not pushed into gitHub 

        System.out.println("Recognition beginning...");


        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            String transcription = result.getHypothesis();
            System.out.println("Transription: " + transcription);
        }
        recognizer.stopRecognition();
    }
}