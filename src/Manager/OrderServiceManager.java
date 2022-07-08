package Manager;

import Hotel.Bill;
import Hotel.OrderService;
import Hotel.Service;
import Data.IOFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderServiceManager {
    public static final String PATHNAME_ORDER_SERVICE = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\orderService.txt";
    private ArrayList<OrderService> orderServices;
    private Scanner scanner = new Scanner(System.in);
    private final IOFile<OrderService> ioFile = new IOFile<>();

    public OrderServiceManager() {
        if (new File(PATHNAME_ORDER_SERVICE).length() == 0) {
            this.orderServices = new ArrayList<>();
        } else {
            this.orderServices = ioFile.readFile(PATHNAME_ORDER_SERVICE);
        }
    }
    public OrderServiceManager(ArrayList<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public void addOrderService(Bill bill, Service service, LocalDate orderDate) {
        OrderService.ID_ORDER = setValue();
        System.out.println("Nhập số lượng:");
        int quantity = Integer.parseInt(scanner.nextLine());
        OrderService orderService = new OrderService(bill, service, orderDate, quantity);
        orderServices.add(orderService);
        ioFile.writeFile(orderServices, PATHNAME_ORDER_SERVICE);
        writeValue();
        System.out.println("\uF0FC Thêm dịch vụ " + service.getServiceName() + " của phòng " + bill.getRoom().getRoomName() + " thành công !!!");
        System.out.println("--------------------");
    }

    public void deleteByRoomNameAndServiceName(String roomName, String serviceName, LocalDate orderDate) {
        OrderService orderService = null;
        for (OrderService order : orderServices) {
            if (order.getBill().getRoom().getRoomName().equals(roomName) && order.getService().getServiceName().equalsIgnoreCase(serviceName) && order.getOrderDate().isEqual(orderDate)) {
                orderService = order;
            }
        }
        if (orderService != null) {
            orderServices.remove(orderService);
            ioFile.writeFile(orderServices, PATHNAME_ORDER_SERVICE);
            System.out.println("\uF0FC Xóa dịch vụ " + serviceName + " của phòng " + roomName + " thành công!");
            System.out.println("--------------------");
        } else {
            System.out.println("\uF0FB Không tìm thấy dịch vụ cần xóa!");
            System.out.println("--------------------");
        }
    }

    public double getTotalInAMonth(int month, int year) {
        double total = 0;
        for (OrderService orderService : orderServices) {
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (orderService.getOrderDate().getMonth().getValue() == month && orderService.getOrderDate().getYear() == year) {
                total += getTotalOrderService;
            }
        }
        return total;
    }

    public double getTotalCheckOut(String roomName, LocalDate startDate) {
        double totalCheckOut = 0;
        for (OrderService orderService : orderServices) {
            boolean checkRoomName = orderService.getBill().getRoom().getRoomName().equals(roomName);
            boolean checkDate = orderService.getBill().getStartDate().isEqual(startDate);
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (checkRoomName && checkDate) {
                totalCheckOut += getTotalOrderService;
            }
        }
        return totalCheckOut;
    }

    public void displayByRoomName(String roomName, LocalDate startDate) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-10s| %-20s| %-15s| %-10s| %-15s|\n", "Ngày", "Tên dịch vụ", "Giá tiền", "Số lượng", "Thành tiền");
        System.out.println("---------------------------------------------------------------------------------");
        for (OrderService orderService : orderServices) {
            boolean checkRoomName = orderService.getBill().getRoom().getRoomName().equals(roomName);
            boolean checkDate = orderService.getBill().getStartDate().isEqual(startDate);
            double getTotalOrderService = orderService.getQuantity() * orderService.getService().getPriceOfService();
            if (checkRoomName && checkDate) {
                System.out.printf("| %-10s| %-20s| %-15.2f| %-10s| %-15.2f|\n", orderService.getOrderDate(), orderService.getService().getServiceName(),
                        orderService.getService().getPriceOfService(), orderService.getQuantity(), getTotalOrderService);
                System.out.println("---------------------------------------------------------------------------------");
            }
        }
    }

    public void writeValue() {
        try {
            String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueOrder.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
            bufferedWriter.write(OrderService.ID_ORDER);
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int setValue() {
        try {
            String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueOrder.txt";
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
