package voiceTransfer;

import voiceTransfer.frame.VoiceTransferFrame;

import javax.swing.*;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        JFrame frame;
        try {
            frame = new VoiceTransferFrame();
        } catch (SocketException e) {
            e.printStackTrace();
            return;
        }

        frame.setVisible(true);
    }
}
