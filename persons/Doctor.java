package persons;

/**
 * Created by minorius on 04.03.2017.
 */
public class Doctor implements Visitor, Runnable {

    public Doctor(){
        reason();
    }

    public String reason(){
        return "Лікую";
    }

    public void run() {
        try {
            Room.handling(reason());
        } catch (InterruptedException e) {

        }

    }
}
