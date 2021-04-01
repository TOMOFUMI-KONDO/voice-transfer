package voiceTransfer.sender;

import java.util.HashSet;
import java.util.Set;

public class VoiceSenderSet {
    Set<VoiceSender> senders = new HashSet<>();

    public Set<VoiceSender> getSenders() {
        return this.senders;
    }

    public void addSender(VoiceSender sender) {
        this.senders.add(sender);
    }

    public void removeSender(VoiceSender sender) {
        this.senders.remove(sender);
    }
}
