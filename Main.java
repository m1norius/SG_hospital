import persons.Doctor;
import persons.Patient;
import persons.Room;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        while (true){
            Thread.sleep(1000);
            getPerson();
            Thread.sleep(1000);
            getPerson();
            Thread.sleep(1000);
            getPerson();
            Thread.sleep(1000);
            getPerson();

        }
    }

    public static void getPerson(){
        int[] randomPerson = {0, 0, 1, 1, 1, 1, 1};
        Random random = new Random();
        int person = random.nextInt(randomPerson.length);

        if (randomPerson[person] == 0){
            new Thread(new Doctor()).start();
        }else {
            new Thread(new Patient()).start();
        }
    }
}
