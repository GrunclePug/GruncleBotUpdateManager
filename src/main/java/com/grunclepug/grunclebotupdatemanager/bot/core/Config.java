package com.grunclepug.grunclebotupdatemanager.bot.core;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Config loader
 * @author grunclepug
 */
public class Config {
    private static final String FILE_NAME = "src/main/resources/config.json";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm");

    private static String token;
    private static String prefix;
    private static String botLogChannel;
    private static String botUpdateChannel;
    private static String botAbsolutePath;
    private static String githubUpdateChannel;

    private Config() {}

    /**
     * Read config file
     * @throws IOException issue loading file
     */
    public static void readFile() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));
        Map<?, ?> configMap = gson.fromJson(reader, Map.class);

        for(Map.Entry<?, ?> entry : configMap.entrySet()) {
            switch(entry.getKey().toString()) {
                case "token":
                    token = entry.getValue().toString();
                    break;
                case "prefix":
                    prefix = entry.getValue().toString();
                    break;
                case "botLogChannel":
                    botLogChannel = entry.getValue().toString();
                    break;
                case "botUpdateChannel":
                    botUpdateChannel = entry.getValue().toString();
                    break;
                case "botAbsolutePath":
                    botAbsolutePath = entry.getValue().toString();
                    break;
                case "githubUpdateChannel":
                    githubUpdateChannel = entry.getValue().toString();
                    break;
            }
        }
    }

    /**
     * Token Accessor
     * @return token
     */
    public static String getToken() {
        return token;
    }

    /**
     * Prefix Accessor
     * @return prefix
     */
    public static String getPrefix() {
        return prefix;
    }

    /**
     * BotLogChannel Accessor
     * @return botLogChannel
     */
    public static String getBotLogChannel() {
        return botLogChannel;
    }

    /**
     * BotUpdateChannel Accessor
     * @return botUpdateChannel
     */
    public static String getBotUpdateChannel() {
        return botUpdateChannel;
    }

    /**
     * BotAbsolutePath Accessor
     * @return botAbsolutePath
     */
    public static String getBotAbsolutePath() {
        return botAbsolutePath;
    }

    /**
     * GithubUpdateChannel Accessor
     * @return githubUpdateChannel
     */
    public static String getGithubUpdateChannel() {
        return githubUpdateChannel;
    }
}
