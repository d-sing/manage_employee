package org.ds.manage_employee;

import org.ds.manage_employee.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ManageEmployeeApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ManageEmployeeApplication.class, args);
        manageEmployee();
    }

    public static void manageEmployee() throws IOException {

        Map<Integer, Employee> employees = new HashMap<>();
        Map<Integer, List<Employee>> managerToSubOrdinate = new HashMap<>();

        try{

            File file = new ClassPathResource("employees.csv").getFile();
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            br.readLine();

            while((line =br.readLine())!=null){

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String firstName = data[1].trim();
                String lastName= data[2].trim();
                double salary = Double.parseDouble(data[3].trim());
                Integer managerId= null;
                if(data.length>4) {
                    managerId = Integer.parseInt(data[4].trim());
               }

                Employee employee = new Employee(id, firstName, lastName, salary, managerId);
                employees.put(id, employee);

                if(null != managerId) {
                    managerToSubOrdinate.computeIfAbsent(managerId, k -> new ArrayList<>()).add(employee);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Managers earning less or more than expected");

        for(Map.Entry<Integer, List<Employee>> entry : managerToSubOrdinate.entrySet()) {

            Integer managerId = entry.getKey();

            if(managerId == null || !employees.containsKey(managerId)) {
                continue;
            }

            Employee manager = employees.get(managerId);
            List<Employee> subOrdinate = entry.getValue();

            double totalSalary = 0;

            for(Employee employee : subOrdinate) {
                totalSalary += employee.getSalary();
            }

            double avgSalary = totalSalary/subOrdinate.size();
            double minSalary= 1.2 * avgSalary;
            double maxSalary = 1.5 * avgSalary;

            if(manager.getSalary()< minSalary){
                System.out.println("Manager" + manager.getFirstName() + " salary is lower by " + (minSalary-manager.getSalary()));
            }else if(manager.getSalary()>maxSalary){
                System.out.println("Manager" + manager.getFirstName() + " salary is lower by " + (manager.getSalary()-maxSalary));
            }
        }

        // Report employees with long reporting chains
        System.out.println("\nEmployees with too many levels between them and the CEO:");
        for (Employee emp : employees.values()) {
            int levels = countLevels(emp, employees);
            if (levels > 4) {
                System.out.printf("%s has %d levels above, exceeding the limit by %d levels.\n",
                        emp.getFirstName(), levels, (levels - 4));
            }
        }

    }

    private static int countLevels(Employee emp, Map<Integer, Employee> employees) {
        int count = 0;
        while (emp.getManagerId()!= null) {
            count++;
            emp = employees.get(emp.getManagerId());
        }
        return count;
    }
}


