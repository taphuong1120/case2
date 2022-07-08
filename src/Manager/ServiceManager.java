package Manager;

import Hotel.Service;
import Data.IOFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceManager {
    public static final String PATHNAME_SERVICE = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\service.txt";
    private ArrayList<Service> services;
    private Scanner scanner = new Scanner(System.in);
    private IOFile<Service> ioFile = new IOFile<>();

    public ArrayList<Service> getServices() {
        return services;
    }

    public ServiceManager() {
        if (new File(PATHNAME_SERVICE).length() == 0) {
            this.services = new ArrayList<>();
        } else {
            this.services = ioFile.readFile(PATHNAME_SERVICE);
        }


        }

        public void addService() {
            boolean check = true;
            Service.ID_SERVICE = setValue();
            System.out.println("Nhập tên dịch vụ:");
            String name = scanner.nextLine();
            System.out.println("Nhập giá dịch vụ:");
            double price = Double.parseDouble(scanner.nextLine());
            for (Service service : services) {
                if (service.getServiceName().equalsIgnoreCase(name)) {
                    check = false;
                    break;
                }
            }
            Service service = null;
            if (check) {
                service = new Service(name, price);
                services.add(service);
                ioFile.writeFile(services, PATHNAME_SERVICE);
                writeValue();
                System.out.println("Thêm dịch vụ " + name + " thành công!");
                System.out.println("--------------------");
            } else {
                System.out.println("Tên bị trùng, mời nhập lại!");
                System.out.println("--------------------");
            }

        }

        public void editService(String name) {
            Service editService = null;
            for (Service service : services) {
                if (service.getServiceName().equalsIgnoreCase(name)) {
                    editService = service;
                }
            }
            if (editService != null) {
                int index = services.indexOf(editService);
                System.out.println("Nhập giá mới:");
                editService.setPriceOfService(Double.parseDouble(scanner.nextLine()));
                services.set(index, editService);
                ioFile.writeFile(services, PATHNAME_SERVICE);
                System.out.println("Sửa thành công!");
                System.out.println("--------------------");
            }
        }

        public void deleteServiceByName(String name) {
            Service deleteService = null;
            for (Service service : services) {
                if (service.getServiceName().equalsIgnoreCase(name)) {
                    deleteService = service;
                }
            }
            if (deleteService != null) {
                services.remove(deleteService);
                ioFile.writeFile(services, PATHNAME_SERVICE);
                System.out.println("Xóa " + name + " thành công!");
                System.out.println("--------------------");
            } else {
                System.out.println("Không tìm thấy " + name + " !");
                System.out.println("--------------------");
            }
        }

        public void displayServiceList() {
            if (services.isEmpty()) {
                System.out.println("Danh sách dịch vụ chưa được cập nhật!");
                System.out.println("--------------------");
                return;
            }
            System.out.println("-----------------------------------------------");
            System.out.printf("| %-5s| %-20s| %-15s|\n", "Id", "Tên Dịch Vụ", "Giá");
            System.out.println("-----------------------------------------------");
            for (Service service : services) {
                System.out.printf("| %-5s| %-20s| %-15.2f|\n", service.getIdService(), service.getServiceName(), service.getPriceOfService());
                System.out.println("-----------------------------------------------");
            }
        }

        public Service getService(int id) {
            Service service = null;
            for (Service service1 : services) {
                if (service1.getIdService() == id) {
                    service = service1;
                }
            }
            return service;
        }

        public void writeValue() {
            try {
                String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueService.txt";
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_NAME));
                bufferedWriter.write(Service.ID_SERVICE);
                bufferedWriter.close();
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }

        public int setValue() {
            try {
                String PATH_NAME = "C:\\Users\\Admin\\OneDrive\\Desktop\\module2\\CaseStudy2\\src\\Data\\valueService.txt";
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
