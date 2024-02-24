import model.Customer;
import model.FreeRoom;
import model.Room;
import model.RoomType;

public class Tester {
    public static void main(String[] args) {

        Customer customer = new Customer("first", "second", "j@domain.com");
//        Customer customer = new Customer("first","second","email"); //throw IllegalArgumentException
        Room freeRoom = new FreeRoom("LV1", RoomType.DOUBLE);
        System.out.println(customer);
        System.out.println(freeRoom.getRoomPrice());

    }
}