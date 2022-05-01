package com.grunclepug.grunclebotupdatemanager.bot.commands;

import com.grunclepug.grunclebotupdatemanager.bot.core.Config;
import com.grunclepug.grunclebotupdatemanager.bot.util.log.BotLog;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Stop Command
 * @author grunclepug
 */
public class Stop extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase(Config.getPrefix() + "stop") || args[0].equalsIgnoreCase(Config.getPrefix() + "shutdown")) {
            if(event.getMessage().getAuthor().getId().equals("247916497803018242")) {
                event.getMessage().getChannel().sendTyping().queue();
                event.getMessage().getChannel().sendMessage("```fix\nBOT SHUTTING DOWN..\n```").queue();
                BotLog.log("STOP");
                BotLog.log(event);
                event.getMessage().getChannel().sendTyping().queue();
                event.getMessage().getChannel().sendMessage("```diff\n- BOT SHUTDOWN\n```").queue();
            }
        }
    }
}
