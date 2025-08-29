package Part2;

import Part1.Employee;

import java.time.LocalDate;

public class SickLeaveRequest extends LeaveRequest {

    private LocalDate currentDate = LocalDate.now();
    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType, boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "Sick Leave");
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    @Override
    public boolean approve(String approverName) {
        super.status = "Approved";

        StatusChange status = new StatusChange(super.status, currentDate.toString(), approverName);
        addStatusHistory(status);

        System.out.println("The Sick Leave Request for employee: " + super.employee.getName() + " has been approved.\nApprover: " + approverName);
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        super.status = "Denied";

        StatusChange status = new StatusChange(super.status, currentDate.toString(), approverName);
        addStatusHistory(status);

        System.out.println("The Sick Leave Request for employee: " + super.employee.getName() + " has been denied.\nReason: " + reason + "\nApprover: " + approverName);
        return false;
    }

    @Override
    public int calculateLeaveDays() {
        return 5;
    }

    @Override
    public boolean processRequest(){
        System.out.println("Processing Sick Leave Request...");
        if(calculateLeaveDays() > 2){
            if(!medicalCertificateProvided){
                System.out.println("Sick Leave Request Denied since there was no Medical Certificate Provided for a Sick Leave greater than 2 days.");
                return false;
            }
        }

        System.out.println("Sick Leave Request has been approved.");
        return true;
    }
}
