import java.util.ArrayList;
import java.util.List;

public class BalanceCalculator {

    public static List<Double> calculateDailyBalances(double[] dailySales, double initialBalance, double repaymentRate) {
        List<Double> balances = new ArrayList<>();
        double balance = initialBalance;

        for (double sales : dailySales) {
            if (balance <= 0) {
                balances.add(0.0);
                continue;
            }

            double repayment = repaymentRate * sales;
            repayment = Math.min(repayment, balance); // Prevent overpayment
            balance -= repayment;

            balances.add(Math.round(balance * 100.0) / 100.0); // Round to 2 decimal places
        }

        return balances;
    }

    public static void main(String[] args) {
        double[] salesForecast = {
                1000, 1200, 1500, 1100, 900, 1300, 1250, 1000, 950, 1200,
                1150, 1050, 1400, 1350, 1250, 1000, 950, 1100, 1200, 1300,
                1400, 1250, 1150, 1000, 950, 900, 850, 1200, 1100, 1000
        };

        double initialBalance = 15000;
        double repaymentRate = 0.10;

        List<Double> dailyBalances = calculateDailyBalances(salesForecast, initialBalance, repaymentRate);

        // Output the results
        for (int i = 0; i < dailyBalances.size(); i++) {
            System.out.printf("Day %2d: GBP%.2f%n", i + 1, dailyBalances.get(i));
        }
    }
}
