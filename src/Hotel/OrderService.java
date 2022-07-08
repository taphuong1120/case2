package Hotel;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderService implements Serializable {
    public static int ID_ORDER;
    private int idOrder;
    private Bill bill;
    private Service service;
    private LocalDate orderDate;
    private int quantity;

    public OrderService() {
    }

    public OrderService(Bill bill, Service service, LocalDate orderDate, int quantity) {
        this.idOrder = ID_ORDER;
        this.bill = bill;
        this.service = service;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public OrderService(int idOrder, Bill bill, Service service, LocalDate orderDate, int quantity) {
        this.idOrder = idOrder;
        this.bill = bill;
        this.service = service;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderService{" +
                "idOrder=" + idOrder +
                ", Hóa đơn =" + bill +
                ", Dịch vụ =" + service +
                ", Ngày order =" + orderDate +
                ", Số lượng =" + quantity +
                '}';
    }
}
