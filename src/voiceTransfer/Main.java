package voiceTransfer;

import util.PrintUtil;
import voiceTransfer.view.VoiceTransferFrame;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        JFrame frame;
        try {
            frame = new VoiceTransferFrame();
        } catch (SocketException | LineUnavailableException e) {
            PrintUtil.printException(e);
            return;
        }

        frame.setVisible(true);
    }
}
