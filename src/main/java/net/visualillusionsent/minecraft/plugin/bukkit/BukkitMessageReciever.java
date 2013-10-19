package net.visualillusionsent.minecraft.plugin.bukkit;

import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;
import org.bukkit.command.CommandSender;

/**
 * Bukkit Message Receiver
 *
 * @author Jason (darkdiplomat)
 */
public class BukkitMessageReciever implements ModMessageReceiver {
    private CommandSender receiver;

    public BukkitMessageReciever(CommandSender receiver) {
        this.receiver = receiver;
    }

    @Override
    public final void message(String message) {
        receiver.sendMessage(message);
    }

}
