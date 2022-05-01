package com.grunclepug.grunclebotupdatemanager.bot.commands;

import com.grunclepug.grunclebotupdatemanager.bot.core.Config;
import com.grunclepug.grunclebotupdatemanager.bot.util.log.BotLog;
import com.grunclepug.grunclebotupdatemanager.bot.util.manager.JarUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

/**
 * Update Command
 * @author grunclepug
 */
public class Update extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Config.getPrefix() + "update")) {
            if(event.getMessage().getAuthor().getId().equals("247916497803018242")) {
                try {
                    event.getMessage().getChannel().sendTyping().queue();
                    event.getMessage().getChannel().sendMessage("```fix\nBOT UPDATING..\n```").queue();
                    BotLog.log("UPDATE");
                    BotLog.log(event);
                    JarUtil.updateJar();
                    event.getMessage().getChannel().sendTyping().queue();
                    event.getMessage().getChannel().sendMessage("```diff\n+ BOT UPDATED\n```").queue();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
