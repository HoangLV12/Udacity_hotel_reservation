import api.AdminResource;
import model.Room;
import model.RoomType;
import util.IOUtils;

import java.util.Collections;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource ar = AdminResource.getInstance();

    public static void displayMenu() {
        System.out.println("-----------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-----------------");
        System.out.println("Please select a number for the menu option");
    }

    public static void dashboard() {
        int choice;
        do {
            displayMenu();
            choice = IOUtils.getUserChoice(1, 2, 3, 4, 5);
            switch (choice) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addRoom();
                    break;
                case 5:
                    //back to main menu
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }

    private static void addRoom() {
        System.out.println("Enter room number:");
        String roomNumber = IOUtils.enterRoomNumber();

        System.out.println("Enter room price:");
        double roomPrice = IOUtils.enterPrice();

        System.out.println("Enter room type (1:SINGLE, 2:DOUBLE ):");
        RoomType roomType = enterRoomType();

        Room room = new Room(roomNumber, roomPrice, roomType);
        ar.addRoom(Collections.singletonList(room));
        System.out.println("Room added successfully!");

        System.out.println("Would like to add another room? Y/N");
        if (IOUtils.getUserChoice("Y", "N").equalsIgnoreCase("Y")) {
            addRoom();
        }

    }

    private static void seeAllReservations() {
        ar.displayAllReservations();
    }

    private static void seeAllRooms() {
        ar.getAllRooms().forEach(System.out::println);
    }

    private static void seeAllCustomers() {
        ar.getAllCustomers().forEach(System.out::println);
    }

    private static RoomType enterRoomType() {
        Scanner sc = new Scanner(System.in);
        try {
            return RoomType.valueOfLabel(sc.nextLine());
        } catch (IllegalArgumentException exp) {
            System.out.println("Invalid room type! Please, choose 1 for SINGLE or 2 for DOUBLE :");
            return enterRoomType();
        }
    }
}
