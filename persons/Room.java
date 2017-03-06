package persons;

/**
 * Created by minorius on 04.03.2017.
 */
public class Room {

    static volatile int doctorInRoom = 0;
    static volatile int patientInRoom = 0;

    static boolean open = true;

    public static final Object lock = new Object();
    public static final Object healing = new Object();

    public static void handling(String person) throws InterruptedException {

            if(!open){
                synchronized (lock){
                    System.out.println("Ждун");
                    lock.wait();
                }
            }

            synchronized (healing) {
                personInRoom(person);

                if(patientInRoom > 1) {
                    patientInRoom--;
                    Thread.sleep(1000);
                    System.out.println("Пацієнт вийшов, в кабінеті " + patientInRoom + " пацієнтів");

                }else if(patientInRoom == 1 && doctorInRoom == 0){
                    patientInRoom--;
                    System.out.println("Пацієнт вийшов, в кабінеті " + patientInRoom + " пацієнтів");
                    System.out.println("Пацієнти вийшли, кабінет відкритий");
                    synchronized (lock){
                        lock.notifyAll();
                    }
                    open = true;

                }else if(patientInRoom == 1 && doctorInRoom == 1){
                    patientInRoom--;
                    System.out.println("Пацієнт вийшов, в кабінеті " + patientInRoom + " пацієнтів");
                }else if(doctorInRoom == 1){
                    doctorInRoom--;
                    Thread.sleep(1000);
                    System.out.println("Лікар вийшов, кабінет відкритий");
                    synchronized (lock){
                        lock.notifyAll();
                    }
                    open=true;
                }
            }

        System.out.println("Кількість потоків в черзі: "+Thread.activeCount());

    }

    private static void personInRoom(String person) throws InterruptedException {
        if(person.equalsIgnoreCase("Виліковуюсь")){
            patientInRoom++;
            System.out.println("Пацієнт зайшов "+ patientInRoom);
        }else {
            doctorInRoom++;
            System.out.println("Лікар зайшов");
        }

        if (patientInRoom == 4 || doctorInRoom == 1){
            open = false;
            healing.notifyAll();
            System.out.println("Кабінет закритий");
        }else if(patientInRoom < 4 || doctorInRoom == 0){
            healing.wait();
        }
    }
}
