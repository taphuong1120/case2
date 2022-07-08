package Hotel;

import java.io.Serializable;

public class Service implements Serializable {
   public static int ID_SERVICE;
   private int idService;
   private String serviceName;
   private double priceOfService;

   public Service() {
   }

   public Service(String nameService, double priceOfService) {
      this.idService = ID_SERVICE++;
      this.serviceName = nameService;
      this.priceOfService = priceOfService;
   }


   public int getIdService() {
      return idService;
   }

   public void setIdService(int idService) {
      this.idService = idService;
   }

   public String getServiceName() {
      return serviceName;
   }

   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   public double getPriceOfService() {
      return priceOfService;
   }

   public void setPriceOfService(double priceOfService) {
      this.priceOfService = priceOfService;
   }

   @Override
   public String toString() {
      return "Dịch vụ{" +
              "Mã dịch vụ =" + idService +
              ", Tên dịch vụ ='" + serviceName + '\'' +
              ", Giá dịch vụ =" + priceOfService +
              '}';
   }
}

