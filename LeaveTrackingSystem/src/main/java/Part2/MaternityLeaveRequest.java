package Part2;

import Part1.Employee;

import java.time.LocalDate;

public class MaternityLeaveRequest extends LeaveRequest {

    private LocalDate currentDate = LocalDate.now();
    private String expectedDeliveryDate;

    public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType, String expectedDeliveryDate) {
        super(requestId, employee, startDate, endDate, "Maternity Leave");
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    @Override
    public boolean approve(String approverName) {
        super.status = "Approved";

        StatusChange status = new StatusChange(super.status, currentDate.toString(), approverName);
        addStatusHistory(status);

        System.out.println("The Maternity Leave Request for employee: " + super.employee.getName() + " has been approved.\nApprover: " + approverName);
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        super.status = "Denied";

        StatusChange status = new StatusChange(super.status, currentDate.toString(), approverName);
        addStatusHistory(status);

        System.out.println("The Maternity Leave Request for employee: " + super.employee.getName() + " has been denied.\nReason: " + reason + "\nApprover: " + approverName);
        return false;
    }

    @Override
    public int calculateLeaveDays() {
        return 90;
    }

    @Override
    public boolean processRequest(){
        System.out.println("Processing Maternity Leave Request...");
        return true;
    }
}
