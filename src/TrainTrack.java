import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

// Class for crating the GUI that shows the train track and the trains
class TrainTrack {

    private JTextPane[] trackSlots;

    /**
     * Constructor for TrainTrack
     */
    TrainTrack() {
        JFrame frame = new JFrame("Trains");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 7));
        frame.setBackground(Color.GRAY);

        trackSlots = new JTextPane[21];
        for (int i = 0; i < 21; i++) {
            JTextPane pane = new JTextPane();

            // Style pane
            StyledDocument doc = pane.getStyledDocument();
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            // Bold
            StyleConstants.setBold(attributeSet, true);
            // Center align
            StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), attributeSet, false);
            pane.setCharacterAttributes(attributeSet, true);
            // Font
            pane.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
            pane.setForeground(Color.BLACK);
            // Border
            pane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            pane.setText(Integer.toString(i));
            trackSlots[i] = pane;
        }

        // Row 1
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(trackSlots[2]);
        frame.add(trackSlots[3]);
        frame.add(trackSlots[4]);
        frame.add(new JLabel());
        frame.add(new JLabel());

        // Row 2
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(trackSlots[1]);
        frame.add(new JLabel());
        frame.add(trackSlots[5]);
        frame.add(new JLabel());
        frame.add(new JLabel());

        // Row 3
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(trackSlots[0]);
        frame.add(new JLabel());
        frame.add(trackSlots[6]);
        frame.add(new JLabel());
        frame.add(new JLabel());

        // Row 4
        frame.add(trackSlots[17]);
        frame.add(trackSlots[18]);
        frame.add(trackSlots[9]);
        frame.add(trackSlots[8]);
        frame.add(trackSlots[7]);
        frame.add(trackSlots[19]);
        frame.add(trackSlots[20]);

        // Row 5
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(trackSlots[10]);
        frame.add(new JLabel());
        frame.add(trackSlots[16]);
        frame.add(new JLabel());
        frame.add(new JLabel());

        // Row 6
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(trackSlots[11]);
        frame.add(new JLabel());
        frame.add(trackSlots[15]);
        frame.add(new JLabel());
        frame.add(new JLabel());

        // Row 1
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(trackSlots[12]);
        frame.add(trackSlots[13]);
        frame.add(trackSlots[14]);
        frame.add(new JLabel());
        frame.add(new JLabel());

        frame.pack();
        frame.setVisible(true);
    }

    JTextPane[] getTrackSlots() {
        return trackSlots;
    }
}
