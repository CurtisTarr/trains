import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;

// Represents the train track activity in a thread-safe CopyOnWriteArrayList<String>
// called theActivities
// - addMovementTo(<Integer>) adds a train movement (destination) activity to the record
// - addMessage(<String>) adds a message to the record
// - printActivities() display all the activity history of the train movement
// - trackString() takes a snapshot of the traintrack (with trains) for printing
public class Activity {

    private final CopyOnWriteArrayList<String> theActivities;
    private final String[] referenceTrack;
    private String[] trainTrack;
    private JFrame frame;
    private JTextArea textArea;

    // Constructor for objects of class Activity
    // A reference to the track is passed as a parameter
    public Activity(String[] trainTrack, JFrame frame, JTextArea textArea) {
        theActivities = new CopyOnWriteArrayList<>();
        this.trainTrack = trainTrack;
        this.referenceTrack = trainTrack.clone();
        this.frame = frame;
        this.textArea = textArea;
    }

    // Note - edited to take previousSection and trainName so it is easier to keep history of
    // what train did what, and where all trains are at a moment in time
    public synchronized void addMovedTo(int newSection, int previousSection, String trainName) {
        // add an activity message to the activity history
        String tempString1 = String.format("Train %s moved from [%d] to [%d]", trainName, previousSection, newSection);
        theActivities.add(tempString1);
        // update track to have train in new location
        if (previousSection != -1) {
            trainTrack[previousSection] = referenceTrack[previousSection];
        }
        if (newSection != -1) {
            trainTrack[newSection] = trainName;
        }
        // add the current state of the track to the activity history
        theActivities.add(trackString());
    }// end addMovedTo

    public void addMessage(String message) {
        // add an activity message to the activity history
        String tempString1 = message;
        theActivities.add(tempString1);
    }// end addMessage

    public void printActivities() {
        // print all the train activity history
        System.out.println("TRAIN TRACK ACTIVITY(Tracks [0..20])");
        Iterator<String> iterator = theActivities.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }// end printActivities

    // Utility method to represent the track as a string for printing/display
    public String trackString() {
        String trackStateAsString = "       " + trainTrack[2] + "  " + trainTrack[3] + "  " + trainTrack[4] + " \n"
                + "       " + trainTrack[1] + "     " + trainTrack[5] + " \n"
                + "       " + trainTrack[0] + "     " + trainTrack[6] + " \n"
                + " " + trainTrack[17] + " " + trainTrack[18] + " "
                + trainTrack[9] + "  " + trainTrack[8] + "  " + trainTrack[7] + " "
                + " " + trainTrack[19] + " " + trainTrack[20] + " \n"
                + "      " + trainTrack[10] + "    " + trainTrack[16] + " \n"
                + "      " + trainTrack[11] + "    " + trainTrack[15] + " \n"
                + "      " + trainTrack[12] + " " + trainTrack[13] + " " + trainTrack[14] + " \n";
        textArea.setText(trackStateAsString);
        frame.pack();
        return (trackStateAsString);
    }// end trackString
}// end Activity