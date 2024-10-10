import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

public class ExpenseApp extends Application {
    ExpenseManager expenseManager = new ExpenseManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản lý chi tiêu cá nhân");

        // TextField cho tháng và ngân sách
        TextField monthInput = new TextField();
        monthInput.setPromptText("Tháng (1-12)");

        TextField budgetInput = new TextField();
        budgetInput.setPromptText("Ngân sách hàng tháng");

        // Buttons
        Button addButton = new Button("Thêm khoản chi");
        Button showButton = new Button("Hiển thị chi tiêu");
        Button deleteButton = new Button("Xóa khoản chi");
        Button exportButton = new Button("Xuất ra TXT");
        Button setBudgetButton = new Button("Thiết lập ngân sách");
        Button showBudgetButton = new Button("Hiển thị ngân sách");
        Button calculateBudgetButton = new Button("Tính ngân sách còn lại"); // Button mới
        Button resetButton = new Button("Đặt lại toàn bộ"); // Button reset

        // TextArea for displaying expenses and remaining budget
        TextArea expensesDisplay = new TextArea();
        expensesDisplay.setEditable(false);
        TextArea remainingBudgetDisplay = new TextArea(); // TextArea mới
        remainingBudgetDisplay.setEditable(false);

        // Adding event listeners
        addButton.setOnAction(e -> {
            String category = categoryInput.getText();
            double amount;
            LocalDate date;

            try {
                amount = Double.parseDouble(amountInput.getText());
                date = LocalDate.parse(dateInput.getText());
                expenseManager.addExpense(new Expense(0, category, amount, date));

                categoryInput.clear();
                amountInput.clear();
                dateInput.clear();
            } catch (NumberFormatException ex) {
                showAlert("Lỗi nhập liệu", "Số tiền phải là một số hợp lệ.");
            } catch (Exception ex) {
                showAlert("Lỗi nhập liệu", "Định dạng ngày không hợp lệ. Vui lòng sử dụng YYYY-MM-DD.");
            }
        });

        showButton.setOnAction(e -> {
            expensesDisplay.clear();
            try {
                int month = Integer.parseInt(monthInput.getText());
                int year = LocalDate.now().getYear(); // Lấy năm hiện tại, hoặc có thể yêu cầu nhập năm

                // Kiểm tra tháng hợp lệ
                if (month < 1 || month > 12) {
                    throw new NumberFormatException("Tháng phải nằm trong khoảng từ 1 đến 12.");
                }

                // Lấy chi tiêu theo tháng
                List<Expense> expenses = expenseManager.getExpensesByMonthAndYear(month, year);
                if (expenses.isEmpty()) {
                    expensesDisplay.appendText("Không có chi tiêu nào cho tháng " + month + ".\n");
                } else {
                    for (Expense expense : expenses) {
                        expensesDisplay.appendText(expense.getId() + ": " + expense.getCategory() + " - "
                                + expense.getAmount() + " - " + expense.getDate() + "\n");
                    }
                }
            } catch (NumberFormatException ex) {
                showAlert("Lỗi nhập liệu", "Tháng phải là một số hợp lệ và nằm trong khoảng từ 1 đến 12.");
            } catch (Exception ex) {
                showAlert("Lỗi", "Có lỗi xảy ra khi hiển thị chi tiêu: " + ex.getMessage());
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(categoryInput.getText());
                expenseManager.deleteExpense(id);
                categoryInput.clear();
            } catch (NumberFormatException ex) {
                showAlert("Lỗi nhập liệu", "ID phải là một số hợp lệ.");
            }
        });

        exportButton.setOnAction(e -> {
            expenseManager.exportExpensesToTxt("expenses.txt");
        });

        setBudgetButton.setOnAction(e -> {
            try {
                int month = Integer.parseInt(monthInput.getText());
                double budget = Double.parseDouble(budgetInput.getText());

                // Kiểm tra tháng hợp lệ
                if (month < 1 || month > 12) {
                    throw new NumberFormatException("Tháng phải nằm trong khoảng từ 1 đến 12.");
                }

                expenseManager.setMonthlyBudget(month, budget);
                monthInput.clear();
                budgetInput.clear();
            } catch (NumberFormatException ex) {
                showAlert("Lỗi nhập liệu", "Vui lòng nhập tháng và ngân sách hợp lệ. Tháng phải là số nguyên từ 1 đến 12.");
            }
        });

        showBudgetButton.setOnAction(e -> {
            try {
                int month = Integer.parseInt(monthInput.getText());

                // Kiểm tra tháng hợp lệ
                if (month < 1 || month > 12) {
                    throw new NumberFormatException("Tháng phải nằm trong khoảng từ 1 đến 12.");
                }

                double budget = expenseManager.getMonthlyBudget(month);
                expensesDisplay.appendText("Ngân sách cho tháng " + month + ": " + budget + "\n");
            } catch (NumberFormatException ex) {
                showAlert("Lỗi nhập liệu", "Tháng phải là một số hợp lệ và nằm trong khoảng từ 1 đến 12.");
            }
        });

        // Sự kiện cho nút tính ngân sách còn lại
        calculateBudgetButton.setOnAction(e -> {
            try {
                int month = Integer.parseInt(monthInput.getText());

                // Kiểm tra tháng hợp lệ
                if (month < 1 || month > 12) {
                    throw new NumberFormatException("Tháng phải nằm trong khoảng từ 1 đến 12.");
                }

                double remainingBudget = expenseManager.getRemainingBudgetByMonth(month);
                remainingBudgetDisplay.clear();
                remainingBudgetDisplay.appendText("Ngân sách còn lại cho tháng " + month + ": " + remainingBudget + "\n");
            } catch (NumberFormatException ex) {
                showAlert("Lỗi nhập liệu", "Tháng phải là một số hợp lệ và nằm trong khoảng từ 1 đến 12.");
            } catch (Exception ex) {
                showAlert("Lỗi", "Có lỗi xảy ra khi tính toán ngân sách: " + ex.getMessage());
            }
        });

        // Sự kiện cho nút đặt lại toàn bộ
        resetButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận đặt lại");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa tất cả dữ liệu?");
            alert.setContentText("Tất cả dữ liệu chi tiêu sẽ bị xóa.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    expenseManager.resetAllExpenses(); // Gọi phương thức để xóa toàn bộ
                    categoryInput.clear();
                    amountInput.clear();
                    dateInput.clear();
                    monthInput.clear();
                    budgetInput.clear();
                    expensesDisplay.clear();
                    remainingBudgetDisplay.clear();
                }
            });
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                categoryInput, amountInput, dateInput, addButton, showButton, deleteButton,
                monthInput, budgetInput, setBudgetButton, showBudgetButton, calculateBudgetButton,
                expensesDisplay, remainingBudgetDisplay, exportButton, resetButton // Thêm nút reset
        );

        // Scene
        Scene scene = new Scene(layout, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create table if not exists
        expenseManager.createNewTable();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
