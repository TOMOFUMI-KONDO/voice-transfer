package voiceTransfer.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("send!");
    }
}
