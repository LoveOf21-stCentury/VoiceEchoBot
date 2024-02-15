package com.sylman.voice_echo_bot;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;

import java.net.URL;

public class VoiceEchoBotApplication {

    public static void main(String[] args) throws Exception {
        URL path = VoiceEchoBotApplication.class.getClassLoader().getResource("log4j2.xml");
        Configurator.initialize(null, path.getPath());

        Logger logger= LogManager.getLogger(VoiceEchoBotApplication.class);

        //TODO: разнести функцтлнал по пакетам
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

        logger.info("Recognition beginning...");


        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            String transcription = result.getHypothesis();
            logger.info("Transcription: {}", transcription);
        }
        recognizer.stopRecognition();
    }
}