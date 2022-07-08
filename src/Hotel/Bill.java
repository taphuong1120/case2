package Hotel;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Bill implements Serializable {
    public static int ID_BILL;
    private int idBill;
    private String customerName, petType;
    private LocalDate startDate, endDate;
    private Room room;

    public Bill() {
    }

    public Bill(Room room, String customerName, String petType, LocalDate startDate, LocalDate endDate) {
        this.idBill = ++ID_BILL;
        this.room = room;
        this.customerName = customerName;
        this.petType = petType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Bill(int idBill, String customerName, String petType, LocalDate startDate, LocalDate endDate, Room room) {
        this.idBill = idBill;
        this.customerName = customerName;
        this.petType = petType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }

    public int getIdBill() {
        return idBill;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return (room.getRentalPrice() * (DAYS.between(startDate, endDate)));
    }

    @Override
    public String toString() {
        return "Bill{" +
                "Mã hóa đơn =" + idBill +
                ", Tên phòng ='" + room.getRoomName() + '\'' +
                ", Tên khách hàng ='" + customerName + '\'' +
                ", Loại thú cưng ='" + petType + '\'' +
                ", Ngày bắt đầu thuê =" + startDate +
                ", Ngày kết thúc thuê =" + endDate +
                ", Tổng =" + getTotalPrice() +
                '}';
    }
}
