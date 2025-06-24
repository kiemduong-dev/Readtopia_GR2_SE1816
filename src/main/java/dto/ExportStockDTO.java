package dto;

import java.sql.Date;

public class ExportStockDTO {
    private int exportID;
    private String customerName;
    private Date exportDate;
    private int staffID;
    private String staffName;
    private double total;
    private String reason;

    public ExportStockDTO() {}

    public ExportStockDTO(int exportID, String customerName, Date exportDate, int staffID, String reason) {
        this.exportID = exportID;
        this.customerName = customerName;
        this.exportDate = exportDate;
        this.staffID = staffID;
        this.reason = reason;
    }

    public ExportStockDTO(int exportID, String customerName, Date exportDate, String staffName, double total) {
        this.exportID = exportID;
        this.customerName = customerName;
        this.exportDate = exportDate;
        this.staffName = staffName;
        this.total = total;
    }

    public int getExportID() {
        return exportID;
    }

    public void setExportID(int exportID) {
        this.exportID = exportID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    

}