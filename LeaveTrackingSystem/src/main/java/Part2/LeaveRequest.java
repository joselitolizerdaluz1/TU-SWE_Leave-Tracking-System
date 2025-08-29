package Part2;

import Part1.Employee;

import java.util.ArrayList;

public abstract class LeaveRequest implements Approvable{
    protected int requestId;
    protected Employee employee;
    protected String startDate;
    protected String endDate;
    protected String status;
    protected String leaveType;
    private ArrayList<StatusChange> statusHistory = new ArrayList<>();

    public LeaveRequest(int requestId, Employee employee, String startDate, String endDate, String leaveType){
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = "Pending";
    }

    public abstract int calculateLeaveDays();

    public boolean processRequest(){
        System.out.println("Processing Generic Leave Request...");
        return false;
    }

    public class StatusChange {
        private String newStatus;
        private String changeDate;
        private String changedBy;

        public StatusChange(String newStatus, String changeDate, String changedBy){
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }
    }

    public void printStatusHistory(){
        for(StatusChange history : statusHistory){
            System.out.println("New Status: " + history.newStatus + " | Change Date: " + history.changeDate + " | Changed By: " + history.changedBy);
        }
    }

    public void addStatusHistory(StatusChange statusChange){
        this.statusHistory.add(statusChange);
    }
}
