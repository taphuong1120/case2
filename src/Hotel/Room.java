package Hotel;

import java.io.Serializable;

public class Room implements Serializable {
    public static int ID_ROOM;
    private int id;
    private String roomName;
    private String roomStatus;
    protected double rentalPrice;

    public Room() {
    }

    public Room(String roomName, String roomStatus, double rentalPrice) {
        this.id = ID_ROOM++;
        this.roomName = roomName;
        this.roomStatus = roomStatus;
        this.rentalPrice = rentalPrice;
    }

    public Room(int idRoom, String roomName, String roomStatus, double rentalPrice) {
        this.id = idRoom;
        this.roomName = roomName;
        this.roomStatus = roomStatus;
        this.rentalPrice = rentalPrice;
    }

    public int getIdRoom() {
        return id;
    }

    public void setIdRoom(int idRoom) {
        this.id = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "Mã phòng =" + id +
                ", Tên phòng ='" + roomName + '\'' +
                ", Trạng thái ='" + roomStatus + '\'' +
                ", Giá thuê =" + rentalPrice +
                '}';
    }
}
