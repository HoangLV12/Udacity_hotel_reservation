package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {

    private static HotelResource hotelResource;
    private final CustomerService cs = CustomerService.getInstance();
    private final ReservationService rs = ReservationService.getInstance();

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        return cs.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        cs.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return rs.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        return rs.reserveARoom(cs.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer c = cs.getCustomer(customerEmail);
        if (c == null) {
            return Collections.emptyList();
        }
        return rs.getCustomersReservation(c);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return rs.findRoom(checkIn, checkOut);
    }
}
