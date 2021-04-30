package voiceTransfer.view.button.playButton;

import util.PrintUtil;
import voiceTransfer.receiver.VoiceReceiver;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.function.Consumer;

public class PlayButtonListener implements ActionListener {
    private VoiceReceiver receiver;
    private final Consumer<String> handleClick;

    public PlayButtonListener(Consumer<String> handleClick) {
        this.handleClick = handleClick;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.receiver == null) {
            try {
                this.receiver = new VoiceReceiver();
            } catch (SocketException | LineUnavailableException e) {
                PrintUtil.printException(e);
            }

            this.handleClick.accept("Tap to stop playing");
        } else {
            this.receiver.end();
            this.receiver = null;

            this.handleClick.accept("Tap to play");
        }

    }
}
