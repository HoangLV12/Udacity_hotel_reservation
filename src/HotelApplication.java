import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;


public class HotelApplication {
    public static void main(String[] args) {
        //data test
        HotelResource hr = HotelResource.getInstance();
        hr.createACustomer("hoanglv12@gg.com", "le viet", "hoang");
        hr.createACustomer("hoanglv13@gg.com", "le viet", "hoang");
        hr.createACustomer("hoanglv14@gg.com", "le viet", "hoang");

        AdminResource ar = AdminResource.getInstance();
        List<IRoom> dataTest = new ArrayList<>();
        dataTest.add(new Room("R101", 101.2, RoomType.DOUBLE));
        dataTest.add(new FreeRoom("R102", RoomType.SINGLE));
        dataTest.add(new FreeRoom("R102", RoomType.DOUBLE));
        dataTest.add(new FreeRoom("R103", RoomType.DOUBLE));
        ar.addRoom(dataTest);


        MainMenu.dashboard();

    }


}
