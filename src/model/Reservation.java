package model;

import util.IOUtils;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room,
                       Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


    public IRoom getRoom() {
        return room;
    }


    public Date getCheckInDate() {
        return checkInDate;
    }


    public Date getCheckOutDate() {
        return checkOutDate;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + IOUtils.getDateString(checkInDate) +
                ", checkOutDate=" + IOUtils.getDateString(checkOutDate) +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Reservation)) {
            return false;
        }

        Reservation r = (Reservation) obj;

        return Objects.equals(r.room.getRoomNumber(), this.room.getRoomNumber())
                && Objects.equals(r.checkInDate, this.checkInDate)
                && Objects.equals(r.checkOutDate, this.checkOutDate);

    }
}
