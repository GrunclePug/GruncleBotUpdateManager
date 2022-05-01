package com.grunclepug.grunclebotupdatemanager.bot.util.manager;

import com.grunclepug.grunclebotupdatemanager.bot.core.Config;
import com.grunclepug.grunclebotupdatemanager.bot.core.Driver;

import java.io.File;
import java.io.IOException;

/**
 * GruncleBot jar file interaction/building
 * @author grunclepug
 */
public class JarUtil {
    private static Process process;

    /**
     * Run GruncleBot
     * @throws IOException issue finding project
     * @throws InterruptedException execution interupted
     */
    public static void executeJar() throws IOException, InterruptedException {
        System.out.println("[Update Manager] Starting GruncleBot..");
        process = Runtime.getRuntime().exec("java -jar build/libs/GruncleBot-1.0-all.jar", null, new File(Config.getBotAbsolutePath()));
        System.out.println("[Update Manager] Bot Online");
    }

    /**
     * Restart GruncleBot
     * @throws IOException issue finding project
     * @throws InterruptedException execution interupted
     */
    public static void restartJar() throws IOException, InterruptedException {
        System.out.println("[Update Manager] Restarting GruncleBot..");
        Driver.jda.getTextChannelById(Config.getBotLogChannel()).sendMessage(Config.getPrefix() + "stop").queue();
        Thread.sleep(500);
        executeJar();
    }

    /**
     * Update GruncleBot
     * @throws IOException issue finding project
     * @throws InterruptedException execution interupted
     */
    public static void updateJar() throws IOException, InterruptedException {
        System.out.println("[Update Manager] Shutting down GruncleBot..");
        Driver.jda.getTextChannelById(Config.getBotUpdateChannel()).sendMessage("```[Update Manager] Shutting down GruncleBot..```").queue();
        Driver.jda.getTextChannelById(Config.getBotLogChannel()).sendMessage(Config.getPrefix() + "stop").queue();
        Thread.sleep(500);

        System.out.println("[Update Manager] Pulling update from GitHub..");
        Driver.jda.getTextChannelById(Config.getBotUpdateChannel()).sendMessage("```[Update Manager] Pulling update from GitHub..```").queue();
        process = Runtime.getRuntime().exec("git pull", null, new File(Config.getBotAbsolutePath()));
        process.waitFor();

        System.out.println("[Update Manager] Building Gradle project..");
        Driver.jda.getTextChannelById(Config.getBotUpdateChannel()).sendMessage("```[Update Manager] Building Gradle project..```").queue();
        process = Runtime.getRuntime().exec("gradle build", null, new File(Config.getBotAbsolutePath()));
        process.waitFor();

        process.destroyForcibly();
        process = null;
        System.out.println("[Update Manager] Update Complete");
        Driver.jda.getTextChannelById(Config.getBotUpdateChannel()).sendMessage("```[Update Manager] Update Complete```").queue();
        executeJar();
    }
}
