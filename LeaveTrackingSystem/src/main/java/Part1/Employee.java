package Part1;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private int leaveBalance;

    public Employee(){
        this(0, "Unknown", "N/A");
        this.leaveBalance = 15;
    }

    public Employee(int employeeId, String name, String department){
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.leaveBalance = 20;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        if(leaveBalance < 0){
            System.out.println("Error: The provided leave balance is less than 0. Failed to update leave balance.");
        } else {
            this.leaveBalance = leaveBalance;
        }
    }

    public Boolean requestLeave(int numberOfDays){
        if(numberOfDays > leaveBalance){
            System.out.println("Error: The number of days provided exceeds the leave balance of the employee.");
            return false;
        } else {
            leaveBalance = leaveBalance - numberOfDays;
            return true;
        }
    }
}
