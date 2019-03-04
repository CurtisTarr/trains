import java.util.concurrent.*;

// QuietSemaphore is a simple extension of Semaphore that swallows InterruptedExceptions
class QuietSemaphore extends Semaphore {

    QuietSemaphore(int permits) {
        super(permits);
    }

    @Override
    public void acquire() {
        try {
            super.acquire();
        } catch (InterruptedException ex) {
            System.out.println("Interrupted when waiting");
        }
    }
}
