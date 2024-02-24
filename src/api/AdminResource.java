package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static final CustomerService cs = CustomerService.getInstance();
    private static final ReservationService rs = ReservationService.getInstance();
    private static AdminResource adminResource;

    private AdminResource() {

    }

    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return cs.getCustomer(email);

    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom r : rooms) {
            rs.addRoom(r);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return rs.getAllRooms();

    }

    public Collection<Customer> getAllCustomers() {
        return cs.getAllCustomer();
    }

    public void displayAllReservations() {
        rs.printAllReservation();
    }
}
