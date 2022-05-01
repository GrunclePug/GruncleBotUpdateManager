package com.grunclepug.grunclebotupdatemanager.bot.util.log;

import com.grunclepug.grunclebotupdatemanager.bot.core.Config;
import com.grunclepug.grunclebotupdatemanager.bot.core.Driver;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Date;

/**
 * Bot Log
 * @author grunclepug
 */
public class BotLog {
    private static EmbedBuilder builder = new EmbedBuilder();

    /**
     * Log commands
     * @param event command event for update manager
     */
    public static void log(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        Date date = new Date();
        builder.clear();

        builder.setTitle("__Command: " + args[0].substring(Config.getPrefix().length()) + "__")
                .addField("User", event.getAuthor().getAsMention() + "\n" + event.getAuthor().getId(), true)
                .addField("Guild", event.getGuild().getName() + "\n" + event.getGuild().getId(), true)
                .addField("Channel", event.getChannel().getAsMention() + "\n" + event.getChannel().getId(), true)
                .addField("Command Issued", event.getMessage().getContentDisplay(), false)
                .setFooter(Config.DATE_FORMAT.format(date), "https://i.imgur.com/mk2zlbr.png");

        Driver.jda.getTextChannelById(Config.getBotLogChannel()).sendMessageEmbeds(builder.build()).queue();
    }

    /**
     * Log update manager actions
     * @param event action taken
     */
    public static void log(String event) {
        Date date = new Date();
        String message = "";

        switch(event.toUpperCase()) {
            case "START":
                message = "```diff\n+ BOT STARTED\n" + Config.DATE_FORMAT.format(date) + "\n```";
                break;
            case "RESTART":
                message = "```diff\n+ BOT RESTARTED\n" + Config.DATE_FORMAT.format(date) + "\n```";
                break;
            case "UPDATE":
                message = "```fix\nBOT UPDATED\n" + Config.DATE_FORMAT.format(date) + "\n```";
                break;
            case "STOP":
                message = "```diff\n- BOT SHUTDOWN\n" + Config.DATE_FORMAT.format(date) + "\n```";
                break;
        }

        Driver.jda.getTextChannelById(Config.getBotUpdateChannel()).sendMessage(message).queue();
    }
}
