package voiceTransfer.view.button.receiveButton;

import util.PrintUtil;
import voiceTransfer.receiver.VoiceReceiver;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class ReceiveButtonListener implements ActionListener {
    private VoiceReceiver receiver;

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.receiver == null) {
            try {
                this.receiver = new VoiceReceiver();
            } catch (SocketException | LineUnavailableException e) {
                PrintUtil.printException(e);
            }
        } else {
            this.receiver.end();
            this.receiver = null;
        }
    }
}
