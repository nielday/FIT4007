public class Transaction {
    private final double amount;  // Số tiền giao dịch
    private final String description;  // Mô tả giao dịch
    private final String category;  // Loại giao dịch (ví dụ: "Thu nhập", "Chi tiêu")

    // Constructor để khởi tạo đối tượng Transaction
    public Transaction(double amount, String description, String category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    // Phương thức trả về số tiền của giao dịch
    public double getAmount() {
        return amount;
    }

    // Phương thức toString để hiển thị thông tin giao dịch
    @Override
    public String toString() {
        return String.format("Số tiền: %.2f VND, Mô tả: %s, Loại: %s", amount, description, category);
    }

    public String getCategory() {
        return category;
    }
}
