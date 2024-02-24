package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static ReservationService reservationService;
    private final Map<String, Collection<Reservation>> listAllReservations = new HashMap<>();
    private final Map<String, IRoom> listRoom = new HashMap<>();

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom(IRoom room) {
        listRoom.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return listRoom.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservations = getCustomersReservation(customer);

        if (customerReservations == null) {
            customerReservations = new ArrayList<>();
        }
        customerReservations.add(reservation);
        listAllReservations.put(customer.getEmail(), customerReservations);
        return reservation;
    }

    public Collection<IRoom> findRoom(Date checkInDate, Date checkOutDate) {
        Collection<Reservation> allReservations = getAllReservations();
        Collection<IRoom> listBusyRoom = new HashSet<>();

        for (Reservation reservation : allReservations) {
            if (isBusy(reservation, checkInDate, checkOutDate)) {
                listBusyRoom.add(reservation.getRoom());
            }
        }

        return listRoom.values().stream().filter(room -> listBusyRoom.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }

    private boolean isBusy(Reservation reservation, Date checkInDate,
                           Date checkOutDate) {
        return checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate());
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return listAllReservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        final Collection<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private Collection<Reservation> getAllReservations() {
        Collection<Reservation> allReservations = new ArrayList<>();

        for (Collection<Reservation> reservations : listAllReservations.values()) {
            allReservations.addAll(reservations);
        }

        return allReservations;
    }


    public Collection<IRoom> getAllRooms() {
        return listRoom.values();
    }
}
