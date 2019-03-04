import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Represents the train track activity in a thread-safe CopyOnWriteArrayList<String> called theActivities
// Modified version of the supplied Activity class
class Activity {

    private final String[] REFERENCE_TRACK = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    private CopyOnWriteArrayList<String> theActivities;
    private String[] trainTrack;
    private JTextPane[] trackSlots;

    /**
     * Constructor for Activity
     *
     * @param trackSlots the JTextPlanes that represent the track in the GUI
     */
    Activity(JTextPane[] trackSlots) {
        theActivities = new CopyOnWriteArrayList<>();
        this.trainTrack = REFERENCE_TRACK.clone();
        this.trackSlots = trackSlots;
    }

    /**
     * Adds train movement to the activity history and updates the trackSlots in the GUI
     *
     * @param newSection      the section the train moved too
     * @param previousSection the section the train moved from
     * @param trainName       the name of the train
     */
    synchronized void addMovedTo(int newSection, int previousSection, String trainName, Color color) {
        // add an activity message to the activity history
        String message = String.format("Train %s moved from [%d] to [%d]", trainName, previousSection, newSection);
        theActivities.add(message);
        // update track to have train in new location
        if (previousSection != -1) {
            trainTrack[previousSection] = REFERENCE_TRACK[previousSection];
            trackSlots[previousSection].setText(Integer.toString(previousSection));
            trackSlots[previousSection].setForeground(Color.BLACK);
        }
        if (newSection != -1) {
            trainTrack[newSection] = trainName;
            trackSlots[newSection].setText(trainName);
            trackSlots[newSection].setForeground(color);
        }
        theActivities.add(trackString());
    }

    /**
     * Add message to the activity history
     *
     * @param message the message to add
     */
    void addMessage(String message) {
        theActivities.add(message);
    }

    /**
     * Print the activity history
     */
    void printActivities() {
        System.out.println("TRAIN TRACK ACTIVITY(Tracks [0..20])");
        for (String theActivity : theActivities) {
            System.out.println(theActivity);
        }
    }

    // Utility method to represent the track as a string for printing/display
    private String trackString() {
        String trackStateAsString = "       " + trainTrack[2] + "  " + trainTrack[3] + "  " + trainTrack[4] + " \n"
                + "       " + trainTrack[1] + "     " + trainTrack[5] + " \n"
                + "       " + trainTrack[0] + "     " + trainTrack[6] + " \n"
                + " " + trainTrack[17] + " " + trainTrack[18] + " "
                + trainTrack[9] + "  " + trainTrack[8] + "  " + trainTrack[7] + " "
                + " " + trainTrack[19] + " " + trainTrack[20] + " \n"
                + "      " + trainTrack[10] + "    " + trainTrack[16] + " \n"
                + "      " + trainTrack[11] + "    " + trainTrack[15] + " \n"
                + "      " + trainTrack[12] + " " + trainTrack[13] + " " + trainTrack[14] + " \n";
        return (trackStateAsString);
    }
}