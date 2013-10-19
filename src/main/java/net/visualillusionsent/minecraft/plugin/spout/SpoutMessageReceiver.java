package net.visualillusionsent.minecraft.plugin.spout;

import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;
import org.spout.api.command.CommandSource;

/**
 * Spout Message Receiver
 *
 * @author Jason (darkdiplomat)
 */
public class SpoutMessageReceiver implements ModMessageReceiver {
    private final CommandSource receiver;

    public SpoutMessageReceiver(CommandSource receiver) {
        this.receiver = receiver;
    }

    public final void message(String message) {
        receiver.sendMessage(message);
    }
}
