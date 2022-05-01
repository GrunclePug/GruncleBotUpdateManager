package com.grunclepug.grunclebotupdatemanager.bot.core;

import com.grunclepug.grunclebotupdatemanager.bot.commands.Restart;
import com.grunclepug.grunclebotupdatemanager.bot.commands.Start;
import com.grunclepug.grunclebotupdatemanager.bot.commands.Stop;
import com.grunclepug.grunclebotupdatemanager.bot.commands.Update;
import com.grunclepug.grunclebotupdatemanager.bot.util.github.AutoUpdater;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.IOException;

/**
 * Driver for GruncleBot Update Manager
 * @author grunclepug
 */
public class Driver {
    public static JDA jda;

    /**
     * Main Method
     * @param args console args
     */
    public static void main(String[] args) {
        try {
            new Driver().setup();
        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Bot init
     * @throws LoginException issue logging in
     * @throws IOException issue reading config file
     */
    private void setup() throws LoginException, IOException, InterruptedException {
        Config.readFile();

        jda = JDABuilder.createDefault(Config.getToken()).build();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching(Config.getPrefix() + "help | GrunclePug#7015"));

        // Auto Updater
        jda.addEventListener(new AutoUpdater());

        // OWNER
        jda.addEventListener(new Start());
        jda.addEventListener(new Restart());
        jda.addEventListener(new Update());
        jda.addEventListener(new Stop());

        startBot();
    }

    private void startBot() throws InterruptedException {
        jda.awaitReady();
        jda.getTextChannelById(Config.getBotLogChannel()).sendMessage(Config.getPrefix() + "start").queue();
    }
}
