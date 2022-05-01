package com.grunclepug.grunclebotupdatemanager.bot.commands;

import com.grunclepug.grunclebotupdatemanager.bot.core.Config;
import com.grunclepug.grunclebotupdatemanager.bot.core.Driver;
import com.grunclepug.grunclebotupdatemanager.bot.util.log.BotLog;
import com.grunclepug.grunclebotupdatemanager.bot.util.manager.JarUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

/**
 * Start Command
 * @author grunclepug
 */
public class Start extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Config.getPrefix() + "start")) {
            if(event.getMessage().getAuthor().getId().equals("247916497803018242") || event.getMessage().getAuthor().getId().equals(Driver.jda.getSelfUser().getId())) {
                try {
                    event.getMessage().getChannel().sendTyping().queue();
                    event.getMessage().getChannel().sendMessage("```fix\nBOT STARTING..\n```").queue();
                    BotLog.log("START");
                    BotLog.log(event);
                    JarUtil.executeJar();
                    event.getMessage().getChannel().sendTyping().queue();
                    event.getMessage().getChannel().sendMessage("```diff\n+ BOT ONLINE\n```").queue();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
