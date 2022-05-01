package com.grunclepug.grunclebotupdatemanager.bot.util.github;

import com.grunclepug.grunclebotupdatemanager.bot.core.Config;
import com.grunclepug.grunclebotupdatemanager.bot.core.Driver;
import com.grunclepug.grunclebotupdatemanager.bot.util.manager.JarUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

/**
 * Auto update bot from github
 * @author grunclepug
 */
public class AutoUpdater extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(event.getMessage().getChannel().getId().equals(Config.getGithubUpdateChannel())) {
            try {
                System.out.println("[Update Manager] Update for GruncleBot found");
                Driver.jda.getTextChannelById(Config.getBotUpdateChannel()).sendMessage("```[Update Manager] Update for GruncleBot found```").queue();
                JarUtil.updateJar();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
