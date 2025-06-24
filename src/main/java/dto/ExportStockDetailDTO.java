package dto;

public class ExportStockDetailDTO {
    private int exportDetailID;
    private int exportID;
    private int bookID;
    private int quantity;
    private double price;
    private String reason;

    public ExportStockDetailDTO() {}

    public ExportStockDetailDTO(int exportDetailID, int exportID, int bookID, int quantity, double price, String reason) {
        this.exportDetailID = exportDetailID;
        this.exportID = exportID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.price = price;
        this.reason = reason;
    }

    public int getExportDetailID() {
        return exportDetailID;
    }

    public void setExportDetailID(int exportDetailID) {
        this.exportDetailID = exportDetailID;
    }

    public int getExportID() {
        return exportID;
    }

    public void setExportID(int exportID) {
        this.exportID = exportID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}