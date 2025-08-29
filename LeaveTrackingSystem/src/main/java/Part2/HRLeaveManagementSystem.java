package Part2;

import Part1.Employee;
import Part1.LeaveRequest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HRLeaveManagementSystem {
    private int leaveRequestCounter = 0;

    public static void main(String[] args){

        boolean quitApplication = false;

        Employee employee1 = new Employee(1, "Alice", "Marketing");
        Employee employee2 = new Employee(2, "Bob", "Security");

        Map<Integer, Employee> employeeMap = new LinkedHashMap<>();
        employeeMap.put(employee1.getEmployeeId(), employee1);
        employeeMap.put(employee2.getEmployeeId(), employee2);

        Map<Integer, SickLeaveRequest> sickLeaveRequestMap = new LinkedHashMap<>();
        Map<Integer, VacationLeaveRequest> vacationLeaveRequestMap = new LinkedHashMap<>();
        Map<Integer, MaternityLeaveRequest> maternityLeaveRequestMap = new LinkedHashMap<>();

        System.out.println("Welcome to the HR Leave Management System!");

        Scanner scanner = new Scanner(System.in);
        HRLeaveManagementSystem hrLeaveManagementSystem = new HRLeaveManagementSystem();

        while(!quitApplication){

            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create New Leave Request");
            System.out.println("2. Process a Pending Request");
            System.out.println("3. View All Request Histories");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");

            String line = scanner.nextLine();
            switch(line){
                case "1":
                    hrLeaveManagementSystem.createLeaveRequest(employeeMap, sickLeaveRequestMap, vacationLeaveRequestMap, maternityLeaveRequestMap);
                    break;
                case "2":
                    hrLeaveManagementSystem.processNextPendingRequest(sickLeaveRequestMap, vacationLeaveRequestMap, maternityLeaveRequestMap);
                    break;
                case "3":
                    hrLeaveManagementSystem.viewAllRequestHistories(sickLeaveRequestMap, vacationLeaveRequestMap, maternityLeaveRequestMap);
                    break;
                case "4":
                    quitApplication = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Error: Option not available. Please only use 1-4 when choosing an option.");
                    break;
            }
        }

    }

    public void createLeaveRequest(Map<Integer, Employee> employees, Map<Integer, SickLeaveRequest> sickLeaveRequestMap, Map<Integer, VacationLeaveRequest> vacationLeaveRequestMap, Map<Integer, MaternityLeaveRequest> maternityLeaveRequestMap){
        boolean leaveRequestCreated = false, leaveTypeSelected = false;
        Scanner scanner = new Scanner(System.in);

        while(!leaveRequestCreated){
            System.out.println("\n--- Create New Leave Request ---");
            System.out.println("Select an employee: ");
            for(Map.Entry<Integer, Employee> entry : employees.entrySet()){
                System.out.println(entry.getKey() + ". " + entry.getValue().getName());
            }
            System.out.println("Enter employee number: ");
            String employeeNumber = scanner.nextLine();

            if(employees.containsKey(Integer.parseInt(employeeNumber))){
                String startDate, endDate, expectedDeliveryDate;
                boolean isMedicalCertificateProvided, isPaidTimeOff;

                while(!leaveTypeSelected){
                    System.out.println("\nSelect leave type:");
                    System.out.println("1. Sick leave");
                    System.out.println("2. Vacation leave");
                    System.out.println("3. Maternity leave");
                    System.out.println("Enter leave type number: ");
                    String leaveType = scanner.nextLine();

                    switch(leaveType){
                        case "1":
                            System.out.println("Enter Start Date (YYYY-MM-DD): ");
                            startDate = scanner.nextLine();
                            System.out.println("Enter End Date (YYYY-MM-DD): ");
                            endDate = scanner.nextLine();

                            System.out.println("Is a medical certificate provided? (true/false): ");
                            isMedicalCertificateProvided = scanner.nextLine().equalsIgnoreCase("true");

                            SickLeaveRequest sickLeaveRequest = new SickLeaveRequest(leaveRequestCounter, employees.get(Integer.parseInt(employeeNumber)), startDate, endDate, "Sick Leave", isMedicalCertificateProvided);
                            sickLeaveRequestMap.put(sickLeaveRequest.requestId, sickLeaveRequest);

                            System.out.println("Successfully created Sick Leave Request for " + employees.get(Integer.parseInt(employeeNumber)).getName());
                            leaveRequestCounter++;
                            leaveTypeSelected = true;
                            leaveRequestCreated = true;
                            break;
                        case "2":
                            System.out.println("Enter Start Date (YYYY-MM-DD): ");
                            startDate = scanner.nextLine();
                            System.out.println("Enter End Date (YYYY-MM-DD): ");
                            endDate = scanner.nextLine();

                            System.out.println("Is leave paid time-off? (true/false): ");
                            isPaidTimeOff = scanner.nextLine().equalsIgnoreCase("true");

                            VacationLeaveRequest vacationLeaveRequest = new VacationLeaveRequest(leaveRequestCounter, employees.get(Integer.parseInt(employeeNumber)), startDate, endDate, "Vacation Leave", isPaidTimeOff);
                            vacationLeaveRequestMap.put(vacationLeaveRequest.requestId, vacationLeaveRequest);

                            System.out.println("Successfully created Vacation Leave Request for " + employees.get(Integer.parseInt(employeeNumber)).getName());
                            leaveRequestCounter++;
                            leaveTypeSelected = true;
                            leaveRequestCreated = true;
                            break;
                        case "3":
                            System.out.println("Enter Start Date (YYYY-MM-DD): ");
                            startDate = scanner.nextLine();
                            System.out.println("Enter End Date (YYYY-MM-DD): ");
                            endDate = scanner.nextLine();

                            System.out.println("When is expected delivery date? (YYYY-MM-DD): ");
                            expectedDeliveryDate = scanner.nextLine();

                            MaternityLeaveRequest maternityLeaveRequest = new MaternityLeaveRequest(leaveRequestCounter, employees.get(Integer.parseInt(employeeNumber)), startDate, endDate, "Vacation Leave", expectedDeliveryDate);
                            maternityLeaveRequestMap.put(maternityLeaveRequest.requestId, maternityLeaveRequest);

                            System.out.println("Successfully created Maternity Leave Request for " + employees.get(Integer.parseInt(employeeNumber)).getName());
                            leaveRequestCounter++;
                            break;
                        default:
                            System.out.println("Error: Option not available. Please choose between options 1-3.");
                            break;
                    }
                }
            } else {
                System.out.println("Error: Option not available. Please choose a valid employee number.");
            }
        }
    }

    public void processNextPendingRequest(Map<Integer, SickLeaveRequest> sickLeaveRequestMap, Map<Integer, VacationLeaveRequest> vacationLeaveRequestMap, Map<Integer, MaternityLeaveRequest> maternityLeaveRequestMap){
        boolean requestResult;

        System.out.println("--- Processing Next Pending Request ---");
        for(Map.Entry<Integer, SickLeaveRequest> entry : sickLeaveRequestMap.entrySet()){

            if(!entry.getValue().status.equalsIgnoreCase("pending")){
                continue;
            }

            System.out.println("Request ID: " + entry.getKey());
            System.out.println("Employee: " + entry.getValue().employee.getName());
            System.out.println("Leave Type: " + entry.getValue().leaveType);
            System.out.println("Dates: " + entry.getValue().startDate + " to " + entry.getValue().endDate);
            System.out.println("Status: " + entry.getValue().status);

            requestResult = entry.getValue().processRequest();

            if(requestResult){
                entry.getValue().approve("System");
            } else {
                entry.getValue().deny("System", "No Medical Certificate Provided for Sick Leave Request longer than 2 days.");
            }

            break;
        }

        for(Map.Entry<Integer, VacationLeaveRequest> entry : vacationLeaveRequestMap.entrySet()){

            if(!entry.getValue().status.equalsIgnoreCase("pending")){
                continue;
            }

            System.out.println("Request ID: " + entry.getKey());
            System.out.println("Employee: " + entry.getValue().employee.getName());
            System.out.println("Leave Type: " + entry.getValue().leaveType);
            System.out.println("Dates: " + entry.getValue().startDate + " to " + entry.getValue().endDate);
            System.out.println("Status: " + entry.getValue().status);

            requestResult = entry.getValue().processRequest();

            if(requestResult){
                entry.getValue().approve("System");
            } else {
                entry.getValue().deny("System", "No More Available Paid Time-Off/Vacation Leave Credits");
            }

            break;
        }

        for(Map.Entry<Integer, MaternityLeaveRequest> entry : maternityLeaveRequestMap.entrySet()){

            if(!entry.getValue().status.equalsIgnoreCase("pending")){
                continue;
            }

            System.out.println("Request ID: " + entry.getKey());
            System.out.println("Employee: " + entry.getValue().employee.getName());
            System.out.println("Leave Type: " + entry.getValue().leaveType);
            System.out.println("Dates: " + entry.getValue().startDate + " to " + entry.getValue().endDate);
            System.out.println("Status: " + entry.getValue().status);

            requestResult = entry.getValue().processRequest();

            if(requestResult){
                entry.getValue().approve("System");
            } else {
                entry.getValue().deny("System", "No More Available Maternity Leave Credits");
            }

            break;
        }
    }

    public void viewAllRequestHistories(Map<Integer, SickLeaveRequest> sickLeaveRequestMap, Map<Integer, VacationLeaveRequest> vacationLeaveRequestMap, Map<Integer, MaternityLeaveRequest> maternityLeaveRequestMap){

        System.out.println("Sick Leave Requests:");
        for(Map.Entry<Integer, SickLeaveRequest> entry : sickLeaveRequestMap.entrySet()){
            System.out.println("\nRequest ID: " + entry.getKey());
            entry.getValue().printStatusHistory();
        }

        System.out.println("\nVacation Leave Requests:");
        for(Map.Entry<Integer, VacationLeaveRequest> entry : vacationLeaveRequestMap.entrySet()){
            System.out.println("\nRequest ID: " + entry.getKey());
            entry.getValue().printStatusHistory();
        }

        System.out.println("\nMaternity Leave Requests:");
        for(Map.Entry<Integer, MaternityLeaveRequest> entry : maternityLeaveRequestMap.entrySet()){
            System.out.println("\nRequest ID: " + entry.getKey());
            entry.getValue().printStatusHistory();
        }
    }
}
