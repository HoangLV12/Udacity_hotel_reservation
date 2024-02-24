import api.HotelResource;
import model.IRoom;
import model.Reservation;
import util.IOUtils;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hr = HotelResource.getInstance();
    private static final int SUGGEST_DAY = 7;

    public static void dashboard() {
        while (true) {
            displayMenu();
            int choice = IOUtils.getUserChoice(1, 2, 3, 4, 5);
            switch (choice) {
                case 1:
                    findAndReserveRoom();
                    break;
                case 2:
                    seeMyReservation();
                    break;
                case 3:
                    createUserAccount();
                    break;
                case 4:
                    AdminMenu.dashboard();
                    break;
                case 5:

                    System.exit(0);
            }
        }
    }

    public static void displayMenu() {
        System.out.println("-----------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservation");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-----------------");
        System.out.println("Please select a number for the menu option");
    }

    private static void findAndReserveRoom() {
        Date checkInDate, checkOutDate;
        while (true) {
            System.out.println("Enter Check-In Date:");
            checkInDate = IOUtils.getInputDate();
            System.out.println("Enter Check-Out Date:");
            checkOutDate = IOUtils.getInputDate();
            if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate)) {
                System.out.println("Check-out date must be after check-in date and not equal check-in date");
                continue;
            }
            break;
        }
        Collection<IRoom> listAvailableRoom = hr.findARoom(checkInDate, checkOutDate);
        if (listAvailableRoom.isEmpty()) {
            checkInDate = IOUtils.plusDay(checkInDate, SUGGEST_DAY);
            checkOutDate = IOUtils.plusDay(checkOutDate, SUGGEST_DAY);
            listAvailableRoom = hr.findARoom(checkInDate, checkOutDate);
            if (listAvailableRoom.isEmpty()) {
                System.out.println("No rooms found.");
            } else {
                System.out.println("We only found rooms on other days (from: " + IOUtils.getDateString(checkInDate) + " to: " + IOUtils.getDateString(checkOutDate));
            }
        }

        displayListRoom(listAvailableRoom);
        reserveRoom(listAvailableRoom, checkInDate, checkOutDate);


    }

    private static void reserveRoom(Collection<IRoom> listAvailableRoom, Date checkInDate, Date checkOutDate) {
        System.out.println("Would you like to book? (y/n)");
        if (IOUtils.getUserChoice("Y", "N").equalsIgnoreCase("Y")) {
            System.out.println("Do you have account? (y/n)");
            //have account
            if (IOUtils.getUserChoice("Y", "N").equalsIgnoreCase("Y")) {

                String email = null;
                while (true) {
                    System.out.println("Enter your email (name@domain.com):");
                    email = IOUtils.enterEmail();
                    if (hr.getCustomer(email) == null) {
                        System.out.println("Email does not exist. Would you like to register? (y/n)");
                        if (IOUtils.getUserChoice("Y", "N").equalsIgnoreCase("Y")) {
                            System.out.println("Back to main menu and select 3 to create account.");
                            return;
                        }
                    } else {
                        break;
                    }
                }

                IRoom room = null;
                while (true) {
                    System.out.println("What room number would you like to reserve?");
                    room = hr.getRoom(IOUtils.enterRoomNumber());
                    if (!listAvailableRoom.contains(room)) {
                        System.out.println("Room number not available, Please select the room number from the list :");
                        displayListRoom(listAvailableRoom);
                    } else {
                        break;
                    }
                }

                Reservation reservation = hr.bookARoom(email, room, checkInDate, checkOutDate);
                if (reservation != null) {
                    System.out.println("reserve successful:");
                    System.out.println(reservation);
                }
            } else {
                System.out.println("Back to main menu and select 3 to create account.");
            }
        }
    }

    private static void displayListRoom(Collection<IRoom> listRoom) {
        if (Objects.nonNull(listRoom) && !listRoom.isEmpty()) {
            System.out.println("List room:");
            listRoom.forEach(System.out::println);
        }
    }

    private static void seeMyReservation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email format: name@domain.com");
        String email = sc.nextLine();
        Collection<Reservation> listReservations = hr.getCustomersReservations(email);
        if (listReservations == null || listReservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Reservation history:");
            listReservations.forEach(System.out::println);
        }

    }

    public static void createUserAccount() {
        Scanner sc = new Scanner(System.in);

        System.out.println("First Name:");
        String firstName = sc.nextLine();

        System.out.println("Last Name:");
        String lastName = sc.nextLine();
        System.out.println("Enter Email (name@domain.com):");
        String email = sc.nextLine();


        try {
            hr.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createUserAccount();
        }
    }

}
