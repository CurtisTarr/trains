public class RunTrains {

    public static void main(String[] args) {
        String[] track = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        int[] aTrack = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] bTrack = new int[]{16, 15, 14, 13, 12, 11, 10, 9, 8, 7};
        int[] cTrack = new int[]{17, 18, 9, 8, 7, 19, 20};
        MageeSemaphore[] trackSemaphore = new MageeSemaphore[21];

        for (int i = 0; i < 21; i++) {
            trackSemaphore[i] = new MageeSemaphore(1);
        }

        Activity activity = new Activity(track);

        Train A1 = new Train(aTrack, activity, 5, trackSemaphore, "A1");
        Train A2 = new Train(aTrack, activity, 5, trackSemaphore, "A2");
        Train A3 = new Train(aTrack, activity, 5, trackSemaphore, "A3");
        A1.run();
        A2.run();
        A3.run();
    }
}
