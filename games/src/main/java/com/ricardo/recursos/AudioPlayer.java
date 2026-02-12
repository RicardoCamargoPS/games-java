package com.ricardo.recursos;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

    
    private static final Map<String, byte[]> cache = new ConcurrentHashMap<>();

    public static void preload(String resourceName){
        try {
            String path = "/" + resourceName + ".wav";
            InputStream is = AudioPlayer.class.getResourceAsStream(path);
            if(is == null){
                is = AudioPlayer.class.getResourceAsStream("/resources/" + resourceName + ".wav");
            }
            if(is == null) return;
            try (BufferedInputStream bis = new BufferedInputStream(is)){
                byte[] buf = bis.readAllBytes();
                cache.put(resourceName, buf);
                
                try (ByteArrayInputStream bais = new ByteArrayInputStream(buf);
                     AudioInputStream ais = AudioSystem.getAudioInputStream(bais)){
                    Clip clip = AudioSystem.getClip();
                    clip.open(ais);
                    clip.close();
                } catch (Throwable t) {
                    // ignore:
                }
            }
        } catch (Exception e) {
            // ignore 
        }
    }

    public static void play(String resourceName){
        try {
            byte[] data = cache.get(resourceName);
            InputStream is = null;
            if(data != null){
                is = new ByteArrayInputStream(data);
            } else {
                String path = "/" + resourceName + ".wav";
                is = AudioPlayer.class.getResourceAsStream(path);
                if(is == null){
                    is = AudioPlayer.class.getResourceAsStream("/resources/" + resourceName + ".wav");
                }
            }
            if(is == null) return;

            try (BufferedInputStream bis = new BufferedInputStream(is);
                 AudioInputStream ais = AudioSystem.getAudioInputStream(bis)){
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
        } catch (Exception e) {
            
        }
    }
}
