package voiceTransfer.view.Panel;

import voiceTransfer.view.button.playButton.PlayButton;

import javax.swing.*;
import java.awt.*;

public class PlayPanel extends JPanel {
    public PlayPanel() {
        super();

        this.add(new PlayButton(new Dimension(150, 60)));
    }
}
