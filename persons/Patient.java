package persons;

/**
 * Created by minorius on 04.03.2017.
 */
public class Patient implements Visitor, Runnable {

    public Patient(){
        reason();
    }

    public String reason(){
        return "Виліковуюсь";
    }

    public void run() {
        try {
            Room.handling(reason());
        } catch (InterruptedException e) {

        }
    }
}
