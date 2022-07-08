package Manager;

import Hotel.Room;
import Data.IOFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomManager {
    public static final String PATHNAME_ROOM = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\room.txt";
    private ArrayList<Room>rooms;
    private Scanner scanner = new Scanner(System.in);
    private final IOFile<Room> ioFile = new IOFile<>();

    public RoomManager() {
        if (new File(PATHNAME_ROOM).length() == 0){
            this.rooms = new ArrayList<>();
        } else {
            this.rooms = ioFile.readFile(PATHNAME_ROOM);
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String getStatus(int choice) {
        String status = "";
        switch (choice) {
            case 1:
                status = "Đã có người thuê";
                break;
            case 2:
                status = "Còn trống";
                break;
            case 3:
                status = "Đang sửa";
                break;
        }
        return status;
    }

    public void addRoom() {
        Room.ID_ROOM = setValue();
        System.out.println("Nhập tên phòng:");
        String roomName = scanner.nextLine();
        System.out.println("Nhập giá phòng:");
        double rentalPrice = scanner.nextDouble();
        System.out.println("Nhập trạng thái phòng:");
        System.out.println("1. Đã có người thuê");
        System.out.println("2. Còn trống");
        System.out.println("3. Đang sửa");
        int status = scanner.nextInt();
        scanner.nextLine();
        if (getStatus(status).equals("")) {
            System.out.println("Nhập sai yêu cầu, mời nhập lại");
            System.out.println("--------------------");
            return;
        }
        for (Room room1 : rooms) {
            if (room1.getRoomName().equals(roomName)) {
                System.out.println("Tên phòng bị trùng, mời nhập lại !!!");
                System.out.println("--------------------");
                return;
            }
        }
        Room room = new Room(roomName,getStatus(status),rentalPrice);
        rooms.add(room);
        ioFile.writeFile(rooms, PATHNAME_ROOM);
        writeValue();
        System.out.println("Thêm phòng " + roomName + " thành công !!!");
        System.out.println("--------------------");
    }

    public void editRoom(int id) {
        Room editRoom = null;
        for (Room room : rooms) {
            if (room.getIdRoom() == id) {
                editRoom = room;
            }
        }
        if (editRoom != null) {
            int index = rooms.indexOf(editRoom);
            System.out.println("Nhập tên phòng mới:");
            String name = scanner.nextLine();
            editRoom.setRoomName(name);
            System.out.println("Nhập giá phòng mới:");
            double rentalPrice = scanner.nextDouble();
            editRoom.setRentalPrice(rentalPrice);
            System.out.println("Nhập trạng thái phòng mới:");
            System.out.println("1. Đã có người thuê");
            System.out.println("2. Còn trống");
            System.out.println("3. Đang sửa");
            int status = scanner.nextInt();
            scanner.nextLine();
            editRoom.setRoomStatus(getStatus(status));
            rooms.set(index, editRoom);
            ioFile.writeFile(rooms, PATHNAME_ROOM);
            System.out.println("Sửa thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("Id không tồn tại, mời nhập lại !!!");
            System.out.println("--------------------");
        }
    }

    public void deleteByIdRoom(int id) {
        Room room = null;
        for (Room room1 : rooms) {
            if (room1.getIdRoom() == id) {
                room = room1;
            }
        }
        if (room != null) {
            rooms.remove(room);
            ioFile.writeFile(rooms, PATHNAME_ROOM);
            System.out.println("Xóa thành công!");
            System.out.println("--------------------");
        } else {
            System.out.println("Không tìm thấy Id cần xóa!");
            System.out.println("--------------------");
        }
    }

    public void displayRoomList() {
        if (rooms.isEmpty()) {
            System.out.println("Danh sách phòng chưa được cập nhật!");
            System.out.println("--------------------");
            return;
        }
        System.out.println("----------------------------------------------------");
        System.out.printf("| %-15s| %-15s| %-15s|\n", "Tên", "Giá", "Trạng thái ");
        System.out.println("----------------------------------------------------");
        for (Room room : rooms) {
            System.out.printf("| %-15s| %-15.2f| %-15s|", room.getRoomName(), room.getRentalPrice(), room.getRoomStatus());
            System.out.println();
            System.out.println("----------------------------------------------------");
        }
    }

    public void searchByPriceAndStatus(double lowerPrice, double abovePrice) {
        ArrayList<Room> rooms = new ArrayList<>();
        boolean checkRoom = false;
        for (Room room : rooms) {
            if (room.getRentalPrice() >= lowerPrice && room.getRentalPrice() <= abovePrice
                    && room.getRoomStatus().equals("Đang trống")) {
                rooms.add(room);
                checkRoom = true;
            }
        }
        if (checkRoom) {
            System.out.println("----------------------------------------------------");
            System.out.printf("| %-15s| %-15s| %-15s|\n", "Tên", "Giá", "Trạng thái");
            System.out.println("----------------------------------------------------");
            for (Room room : rooms) {
                System.out.printf("| %-15s| %-15.2f| %-15s|", room.getRoomName(), room.getRentalPrice(), room.getRoomStatus());
                System.out.println();
                System.out.println("----------------------------------------------------");
            }
        } else {
            System.out.println("Không tìm thấy phòng nào!");
            System.out.println("--------------------");
        }
    }

    public void displayAll() {
        if (rooms.isEmpty()) {
            System.out.println("Danh sách phòng chưa được cập nhật!");
            System.out.println("--------------------");
            return;
        }
        rooms.forEach(System.out::println);
        System.out.println("--------------------");
    }

    public Room getRoom(String roomName) {
        Room room = null;
        for (Room room1 : rooms) {
            if (room1.getRoomName().equals(roomName)) {
                room = room1;
            }
        }
        return room;
    }

    public void writeValue() {
        try {
            String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueRoom";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(Room.ID_ROOM);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueRoom";
            File file = new File(PATH_NAME);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                int i;
                if ((i = bufferedReader.read()) != -1) {
                    return i;
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return 0;
    }
}