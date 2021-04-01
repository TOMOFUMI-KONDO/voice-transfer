package voiceTransfer.receiver;

import java.util.HashSet;
import java.util.Set;

public class VoiceReceiverSet {
    Set<VoiceReceiver> receivers = new HashSet<>();

    public Set<VoiceReceiver> getReceivers() {
        return this.receivers;
    }

    public void addReceiver(VoiceReceiver receiver) {
        this.receivers.add(receiver);
    }

    public void removeReceiver(VoiceReceiver receiver) {
        this.receivers.remove(receiver);
    }
}
