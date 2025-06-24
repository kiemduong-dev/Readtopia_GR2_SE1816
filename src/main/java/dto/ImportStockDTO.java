package dto;

import java.sql.Date;

public class ImportStockDTO {
    private int ISID;
    private int supID;
    private int staffID;
    private Date importDate;
    private int ISStatus;
    private String supName;
    private String staffName;
    private double totalPrice;

    public ImportStockDTO() {}

    public ImportStockDTO(int ISID, int supID, Date importDate, int staffID, int ISStatus, String supName, String staffName, double totalPrice) {
        this.ISID = ISID;
        this.supID = supID;
        this.importDate = importDate;
        this.staffID = staffID;
        this.ISStatus = ISStatus;
        this.supName = supName;
        this.staffName = staffName;
        this.totalPrice = totalPrice;
    }

    public ImportStockDTO(int ISID, int supID, Date importDate, int staffID, int ISStatus) {
        this.ISID = ISID;
        this.supID = supID;
        this.importDate = importDate;
        this.staffID = staffID;
        this.ISStatus = ISStatus;

    }

    public int getISID() {
        return ISID;
    }

    public void setISID(int ISID) {
        this.ISID = ISID;
    }

    public int getSupID() {
        return supID;
    }

    public void setSupID(int supID) {
        this.supID = supID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getISStatus() {
        return ISStatus;
    }

    public void setISStatus(int ISStatus) {
        this.ISStatus = ISStatus;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImportStockDTO{");
        sb.append("ISID=").append(ISID);
        sb.append(", supID=").append(supID);
        sb.append(", staffID=").append(staffID);
        sb.append(", importDate=").append(importDate);
        sb.append(", ISStatus=").append(ISStatus);
        sb.append(", supName=").append(supName);
        sb.append(", staffName=").append(staffName);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }
    
}
