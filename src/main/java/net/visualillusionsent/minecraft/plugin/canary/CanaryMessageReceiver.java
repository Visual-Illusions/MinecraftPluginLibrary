package net.visualillusionsent.minecraft.plugin.canary;

import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;

/**
 * Created with IntelliJ IDEA.
 * User: darkdiplomat
 * Date: 10/18/13
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class CanaryMessageReceiver implements ModMessageReceiver {
    private final MessageReceiver receiver;

    public CanaryMessageReceiver(MessageReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public final void message(String message) {
        receiver.message(message);
    }
}
