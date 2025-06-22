import java.util.Scanner;

public class FinancialForecasting {

    public static double predictFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return predictFutureValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static double predictFutureValueIterative(double currentValue, double growthRate, int years) {
        for (int i = 0; i < years; i++) {
            currentValue *= (1 + growthRate);
        }
        return currentValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Financial Forecasting Tool ===\n");

        System.out.print("Enter current value ($): ");
        double currentValue = scanner.nextDouble();

        System.out.print("Enter annual growth rate (in %): ");
        double ratePercent = scanner.nextDouble();
        double growthRate = ratePercent / 100;

        System.out.print("Enter number of years to predict: ");
        int years = scanner.nextInt();

        double futureRecursive = predictFutureValue(currentValue, growthRate, years);
        double futureIterative = predictFutureValueIterative(currentValue, growthRate, years);

        System.out.printf("\nPredicted Future Value (Recursive): $%.2f", futureRecursive);
        System.out.printf("\nPredicted Future Value (Iterative): $%.2f", futureIterative);

        scanner.close();
    }
}