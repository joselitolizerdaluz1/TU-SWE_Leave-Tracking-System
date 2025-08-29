package Part1;

public class LeaveTracker {

    public static void main(String[] args){
        Employee employee1 = new Employee(1234, "Joselito Daluz", "Technology & Operations");
        Employee employee2 = new Employee(5678, "Lizer Daluz", "Security");

        System.out.println("Employee Name: " + employee1.getName() + " | Initial Leave Balance: " + employee1.getLeaveBalance());

        employee1.requestLeave(2);

        System.out.println("Employee Name: " + employee1.getName() + " | Current Leave Balance: " + employee1.getLeaveBalance());

        employee1.requestLeave(19);

        LeaveRequest leaveRequest1 = new LeaveRequest(202501, employee1, "08-29-2025","09-01-2025");

        System.out.println("\nLeave Request: " + leaveRequest1.getRequestId() + "\nName: " + leaveRequest1.getEmployee().getName() + " | Start Date: " + leaveRequest1.getStartDate() + " | End Date: " + leaveRequest1.getEndDate());
    }
}
