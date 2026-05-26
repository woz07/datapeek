package io.github.woz07.datapeek.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.woz07.datapeek.util.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * JsonIoService.java
 * @author          woz07
 * @description     Class that lets us read and write data to json file
 */

public class JsonIoService {
    private static ObjectMapper mapper = new ObjectMapper();

    public static AppConfiguration read(String path) {
        InputStream input = JsonIoService.class.getClassLoader().getResourceAsStream(path);

        if (input == null) {;
            Logger.failure("Unable to find resource: '" + path + "'.");
            return null;
        }

        try {
            return mapper.readValue(input, AppConfiguration.class);
        } catch (IOException e) {
            Logger.failure("Unable to read data from resource: '" + path + "'.");
            return null;
        }
    }

    public static boolean write(AppConfiguration data, String path) {
        try {
            mapper.writeValue(new File(path), data);
            return true;
        } catch (IOException e) {
            Logger.failure("Unable to write data to path : '" + path + "'.");
            return false;
        }
    }
}
