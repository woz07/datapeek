package io.github.woz07.datapeek.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ScriptExecutor.java
 * @author          woz07
 * @description     Class that allows you to execute scripts from within /resources folder
 *                  Utilizes singleton design pattern
 */

public class ScriptExecutor {
    private static volatile ScriptExecutor INSTANCE;
    private ScriptExecutor() {}

    public static ScriptExecutor getInstance() {
        if (INSTANCE == null) {
            synchronized (ScriptExecutor.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ScriptExecutor();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Executes exe based on name from within resources folder
     * @param name  The name of the exe to execute
     * @param args  The args to pass through with the exe
     * @return      Returns 0 if success else 1 as fail
     */
    public int execute(String name, String[] args) throws IOException {
        // Look for the executable resource
        URL resource = getClass().getClassLoader().getResource(name);

        if (resource == null) {
            return 1; // Failed
        }

        Path temp;
        try {
            temp = Files.createTempFile(null, ".exe");

            try (InputStream input = resource.openStream()) {
                Files.copy(input, temp, StandardCopyOption.REPLACE_EXISTING);
            }

            File exe = temp.toFile();
            if (!exe.setExecutable(true)) {
                return 1;
            }

            // Build the command with the executable and its arguments
            List<String> command = new ArrayList<>();
            command.add(exe.getAbsolutePath());
            command.addAll(Arrays.asList(args));

            // Check if the file exists before executing
            if (!exe.exists()) {
                return 1;
            }

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start().waitFor();
            Files.deleteIfExists(temp);

            return 0; // Success
        } catch (IOException | InterruptedException ignore) {
            return 1;
        }
    }

}