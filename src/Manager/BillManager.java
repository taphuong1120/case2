package Manager;

import Hotel.Bill;
import Hotel.Room;
import Data.IOFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BillManager {
public static final String PATHNAME_BILL = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\bill.txt";
private ArrayList<Bill> bills;
private Scanner scanner = new Scanner(System.in);
private final IOFile<Bill> ioFile = new IOFile<>();

    public BillManager() {
        if (new File(PATHNAME_BILL).length() == 0) {
            this.bills = new ArrayList<>();
        } else {
            this.bills = ioFile.readFile(PATHNAME_BILL);
        }
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public boolean checkRoom(String status){
        return (status.equals("Còn trống"));
    }

    public boolean checkDate(String name, LocalDate start,LocalDate end){
        ArrayList<Bill> billArrayList = new ArrayList<>();
        boolean check = true;
        for (Bill bill : bills) {
            if (bill.getRoom().getRoomName().equals(name)) {
                billArrayList.add(bill);
            }
        }
        for (Bill bill : billArrayList) {
            if (end.isBefore(bill.getStartDate()) || start.isAfter(bill.getEndDate())) {
            } else {
                check = false;
                break;
            }
        }
        return check;
    }

    public void addBill(Room room) {
        Bill.ID_BILL = setValue();
        System.out.println("Nhập tên khách thuê:");
        String customerName = scanner.nextLine();
        System.out.println("Nhập loại thú cưng của khách:");
        String petType = scanner.nextLine();
        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
        String start = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
        String end = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        Bill bill = null;
        if (startDate.isBefore(endDate) && checkRoom(room.getRoomStatus()) && checkDate(room.getRoomName(), startDate, endDate)) {
            bill = new Bill(room, customerName, petType, startDate, endDate);
            bills.add(bill);
            ioFile.writeFile(bills, PATHNAME_BILL);
            writeValue();
            System.out.println("Thêm hóa đơn của khách hàng " + customerName + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("- Đã có người thuê");
            System.out.println("- Nhập sai dữ liệu, xin mời nhập lại !!!");
            System.out.println("--------------------");
        }
    }

    public void addBillByUser(Room room) {
        Bill.ID_BILL = setValue();
        System.out.println("Nhập tên khách thuê:");
        String customerName1 = scanner.nextLine();
        String staffName1 = "Online";
        System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy): ");
        String start1 = scanner.nextLine();
        LocalDate startDate1 = LocalDate.parse(start1, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
        String end1 = scanner.nextLine();
        LocalDate endDate1 = LocalDate.parse(end1, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
        Bill bill = null;
        if (startDate1.isBefore(endDate1) && checkRoom(room.getRoomStatus()) && checkDate(room.getRoomName(), startDate1, endDate1)) {
            bill = new Bill(room, customerName1, staffName1, startDate1, endDate1);
            bills.add(bill);
            ioFile.writeFile(bills, PATHNAME_BILL);
            writeValue();
            System.out.println("Đặt phòng của khách hàng " + customerName1 + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("- Đã có người thuê");
            System.out.println("- Nhập sai dữ liệu, xin mời nhập lại !!!");
            System.out.println("--------------------");
        }
    }

    public void editBill(int id) {
        Bill editBill = null;
        for (Bill bill : bills) {
            if (bill.getIdBill() == id) {
                editBill = bill;
            }
        }
        if (editBill != null) {
            int index = bills.indexOf(editBill);
            System.out.println("Nhập tên khách thuê mới:");
            editBill.setCustomerName(scanner.nextLine());
            System.out.println("Nhập loại thú cưng của khách mới:");
            editBill.setPetType(scanner.nextLine());
            try {
                System.out.println("Nhập ngày bắt đầu(dd-mm-yyyy):");
                String start = scanner.next();
                editBill.setStartDate(LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-LL-yyyy")));
                System.out.println("Nhập ngày kết thúc(dd-mm-yyyy):");
                String end = scanner.next();
                editBill.setEndDate(LocalDate.parse(end, DateTimeFormatter.ofPattern("dd-LL-yyyy")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            bills.set(index, editBill);
            ioFile.writeFile(bills, PATHNAME_BILL);
            System.out.println("Sửa thành công !!!");
            System.out.println("--------------------");
        }
    }

    public void deleteByIdBill(int id) {
        Bill bill = null;
        for (Bill bill1 : bills) {
            if (bill1.getIdBill() == id) {
                bill = bill1;
            }
        }
        if (bill != null) {
            bills.remove(bill);
            ioFile.writeFile(bills, PATHNAME_BILL);
            System.out.println("Xóa bill " + id + " thành công !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("Không tìm thấy Id cần xóa !!!");
            System.out.println("--------------------");
        }
    }

    public void displayBillList() {
        if (bills.isEmpty()) {
            System.out.println("Danh sách bill chưa được cập nhật !!!");
            System.out.println("--------------------");
            return;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15s|\n", "Id", "Room", "Customer", "Staff", "Check-in", "Check-out", "Total");
        System.out.println("--------------------------------------------------------------------------------");
        for (Bill bill : bills) {
            System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15.2f|", bill.getIdBill(), bill.getRoom().getRoomName(), bill.getCustomerName(), bill.getPetType(), bill.getStartDate(), bill.getEndDate(), bill.getTotalPrice());
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    public void displayBillListByRoom(String roomName) {
        ArrayList<Bill> billsRoom = new ArrayList<>();
        for (Bill bill : bills) {
            if (bill.getRoom().getRoomName().equals(roomName)) {
                billsRoom.add(bill);
            }
        }
        if (billsRoom.isEmpty()) {
            System.out.println("Không tìm thấy phòng nào !!!");
            System.out.println("--------------------");
        } else {
            billsRoom.sort(Comparator.comparingInt(o -> o.getStartDate().getDayOfMonth()));
            System.out.println("-------------------------------------------------------------------------------- ");
            System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15s|\n", "Id", "Room", "Customer", "Staff", "Check-in", "Check-out", "Total");
            System.out.println("-------------------------------------------------------------------------------- ");
            for (Bill bill : billsRoom) {
                System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15.2f|", bill.getIdBill(), bill.getRoom().getRoomName(), bill.getCustomerName(), bill.getPetType(), bill.getStartDate(), bill.getEndDate(), bill.getTotalPrice());
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------- ");
            }
        }
    }

    public double getBillCheckOut(String roomName, LocalDate checkInDate) {
        double totalBill = 0;
        for (Bill bill : bills) {
            if (bill.getRoom().getRoomName().equals(roomName) && bill.getStartDate().isEqual(checkInDate)) {
                totalBill += bill.getTotalPrice();
            }
        }
        return totalBill;
    }

    public void displayBillCheckOut(String roomName, LocalDate checkInDate) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15s|\n", "Id", "Room", "Customer", "Staff", "Check-in", "Check-out", "Total");
        System.out.println("--------------------------------------------------------------------------------");
        for (Bill bill : bills) {
            if (bill.getRoom().getRoomName().equals(roomName) && bill.getStartDate().isEqual(checkInDate)) {
                System.out.printf("| %-5s| %-5s| %-10s| %-10s| %-10s| %-10s| %-15.2f|", bill.getIdBill(), bill.getRoom().getRoomName(), bill.getCustomerName(), bill.getPetType(), bill.getStartDate(), bill.getEndDate(), bill.getTotalPrice());
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    public double getTotalBillInAMonth(int month, int year) {
        double totalBill = 0;
        for (Bill bill : bills) {
            if (bill.getStartDate().getMonth().getValue() == month && bill.getStartDate().getYear() == year) {
                totalBill += bill.getTotalPrice();
            }
        }
        return totalBill;
    }

    public void checkRoomStatus(String name, LocalDate beforeDate, LocalDate afterDate) {
        ArrayList<Bill> billArrayList = new ArrayList<>();
        Bill firstBill = null;
        Bill lastBill = null;
        int checkDate = 0;
        int checkName = 0;
        if (beforeDate.isAfter(afterDate)) {
            System.out.println("Nhập sai dữ liệu, mời nhập lại !!!");
            System.out.println("--------------------");
            return;
        }
        for (Bill bill : bills) {
            if (bill.getRoom().getRoomName().equals(name)) {
                if (bill.getStartDate().isBefore(beforeDate) && bill.getEndDate().isAfter(beforeDate)) {
                    firstBill = bill;
                } else if (bill.getStartDate().isBefore(afterDate) && bill.getEndDate().isAfter(afterDate)) {
                    lastBill = bill;
                } else if (bill.getStartDate().isEqual(beforeDate) || (bill.getStartDate().isAfter(beforeDate) && bill.getEndDate().isBefore(afterDate)) || bill.getEndDate().isEqual(afterDate)) {
                    billArrayList.add(bill);
                    checkDate++;
                }
                checkName++;
            }
        }
        if (checkName == 0) {
            System.out.println("\uF0FB Không tìm thấy phòng !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("Trạng thái phòng " + name + " từ " + beforeDate + " đến " + afterDate + ":");
            if (checkDate == 0) {
                if (firstBill == null) {
                    if (lastBill == null) {
                        System.out.println("\uF0FC Còn trống !!!");
                        System.out.println("--------------------");
                    } else {
                        System.out.println("----------------------------------------------------");
                        System.out.printf("| %-15s| %-15s| %-15s|\n", "Từ ngày", "Đến ngày", "Trạng thái");
                        System.out.println("----------------------------------------------------");
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                } else {
                    System.out.println("----------------------------------------------------");
                    System.out.printf("| %-15s| %-15s| %-15s|\n", "Từ ngày", "Đến ngày", "Trạng thái");
                    System.out.println("----------------------------------------------------");
                    if (lastBill == null) {
                        System.out.printf("| %-15s| %-15s| %-15s|", beforeDate, firstBill.getEndDate(), "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    } else {
                        System.out.printf("| %-15s| %-15s| %-15s|", beforeDate, firstBill.getEndDate(), "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                }
            } else {
                billArrayList.sort(Comparator.comparingInt(o -> o.getStartDate().getDayOfMonth()));
                System.out.println("----------------------------------------------------");
                System.out.printf("| %-15s| %-15s| %-15s|\n", "Từ ngày", "Đến ngày", "Trạng thái");
                System.out.println("----------------------------------------------------");
                if (firstBill == null) {
                    if (lastBill == null) {
                        for (Bill bill : billArrayList) {
                            System.out.printf("| %-15s| %-15s| %-15s|", bill.getStartDate(), bill.getEndDate(), "ĐÃ THUÊ");
                            System.out.println();
                            System.out.println("----------------------------------------------------");
                        }
                    } else {
                        for (Bill bill : billArrayList) {
                            System.out.printf("| %-15s| %-15s| %-15s|", bill.getStartDate(), bill.getEndDate(), "ĐÃ THUÊ");
                            System.out.println();
                            System.out.println("----------------------------------------------------");
                        }
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                } else {
                    System.out.printf("| %-15s| %-15s| %-15s|", beforeDate, firstBill.getEndDate(), "ĐÃ THUÊ");
                    System.out.println();
                    System.out.println("----------------------------------------------------");
                    for (Bill bill : billArrayList) {
                        System.out.printf("| %-15s| %-15s| %-15s|", bill.getStartDate(), bill.getEndDate(), "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                    if (lastBill != null) {
                        System.out.printf("| %-15s| %-15s| %-15s|", lastBill.getStartDate(), afterDate, "ĐÃ THUÊ");
                        System.out.println();
                        System.out.println("----------------------------------------------------");
                    }
                }
            }
        }
    }

    public Bill getBill(String roomName, LocalDate orderDate) {
        Bill bill = null;
        for (Bill bill1 : bills) {
            boolean checkOrderDate = (bill1.getStartDate().isEqual(orderDate) || bill1.getEndDate().isEqual(orderDate) || (bill1.getStartDate().isBefore(orderDate) && bill1.getEndDate().isAfter(orderDate)));
            if (bill1.getRoom().getRoomName().equals(roomName) && checkOrderDate) {
                bill = bill1;
            }
        }
        return bill;
    }

    public void writeValue() {
        try {
            String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueBill.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(Bill.ID_BILL);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueBill.txt";
            File file = new File(PATH_NAME);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_NAME));
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

