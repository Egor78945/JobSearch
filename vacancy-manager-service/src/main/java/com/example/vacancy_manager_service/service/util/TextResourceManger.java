package com.example.vacancy_manager_service.service.util;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class TextResourceManger extends ResourceManager {
    public TextResourceManger(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    public String getAsString(String name){
        InputStream file = getFile(name);
        StringBuilder res = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            int c = br.read();
            while(c != -1){
                res.append((char)c);
                c = br.read();
            }
            return res.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
