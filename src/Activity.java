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
    private final String[] trainTrack;

    // Constructor for objects of class Activity
    // A reference to the track is passed as a parameter
    public Activity(String[] trainTrack) {
        theActivities = new CopyOnWriteArrayList<>();
        this.trainTrack = trainTrack;
    }

    public synchronized void addMovedTo(int section, String train) {
        // add an activity message to the activity history
        String tempString1 = "Train " + train + " moving/moved to [" + section + "]";
        theActivities.add(tempString1);
        // add the current state of the track to the activity history
        theActivities.add(trackString(section, train));
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
    public String trackString(int section, String train) {
        String trackStateAsString =  "       " + trainTrack[2] + "  " + trainTrack[3] + "  " + trainTrack[4] + " \n"
                + "       " + trainTrack[1] + "     " + trainTrack[5] + " \n"
                + "       " + trainTrack[0] + "     " + trainTrack[6] + " \n"
                + " " + trainTrack[17] + " " + trainTrack[18] + " "
                + trainTrack[9] + "  " + trainTrack[8] + "  " + trainTrack[7] + " "
                + " " + trainTrack[19] + " " + trainTrack[20] + " \n"
                + "      " + trainTrack[10] + "    " + trainTrack[16] + " \n"
                + "      " + trainTrack[11] + "    " + trainTrack[15] + " \n"
                + "      " + trainTrack[12] + " " + trainTrack[13] + " " + trainTrack[14] + " \n";
        trackStateAsString = trackStateAsString.replace(String.format(" %d ", section), " " + train + " ");
        return (trackStateAsString);
    }// end trackString
}// end Activity