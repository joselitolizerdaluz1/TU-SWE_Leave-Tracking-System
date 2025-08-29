package Part2;

import Part1.Employee;

import java.time.LocalDate;

public class VacationLeaveRequest extends LeaveRequest {

    private LocalDate currentDate = LocalDate.now();
    private boolean isPaidTimeOff;

    public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType, boolean isPaidTimeOff) {
        super(requestId, employee, startDate, endDate, "Vacation Leave");
        this.isPaidTimeOff = isPaidTimeOff;
    }

    @Override
    public boolean approve(String approverName) {
        super.status = "Approved";

        StatusChange status = new StatusChange(super.status, currentDate.toString(), approverName);
        addStatusHistory(status);

        System.out.println("The Vacation Leave Request for employee: " + super.employee.getName() + " has been approved.\nApprover: " + approverName);
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        super.status = "Denied";

        StatusChange status = new StatusChange(super.status, currentDate.toString(), approverName);
        addStatusHistory(status);

        System.out.println("The Vacation Leave Request for employee: " + super.employee.getName() + " has been denied.\nReason: " + reason + "\nApprover: " + approverName);
        return false;
    }

    @Override
    public int calculateLeaveDays() {
        return 10;
    }

    @Override
    public boolean processRequest(){
        System.out.println("Processing Vacation Leave Request...");
        return true;
    }
}
