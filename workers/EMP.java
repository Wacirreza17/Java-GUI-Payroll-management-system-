package workers;

public class EMP {
    private static String[] empIds = { "EMP001", "EMP002", "EMP003", "EMP004", "EMP005", "EMP006", "EMP007", "EMP008",
            "EMP009", "EMP010" };
    private static String[] passwords = { "1234", "1234", "1234", "1234", "1234", "1234", "1234", "1234", "1234",
            "1234" };
    private static String[] names = { "Abdullah", "Ashfaque", "Imran", "Wacir", "Messi", "Eminem", "Weekend", "Yamal",
            "pedri", "Maldini" };
    private static double[] salaries = { 5000, 5500, 6000, 4500, 6500, 5000, 5500, 6000, 4500, 6500 };
    private static double[] deductions = { 500, 550, 600, 450, 650, 500, 550, 600, 450, 650 };

    public static EmployeeData validateLogin(String empId, String password) {
        for (int i = 0; i < empIds.length; i++) {
            if (empId.equals(empIds[i]) && password.equals(passwords[i])) {
                return new EmployeeData(empIds[i], names[i], salaries[i], deductions[i]);
            }
        }
        return null;
    }

    public static class EmployeeData {
        public final String empId;
        public final String name;
        public final double salary;
        public final double deduction;

        public EmployeeData(String empId, String name, double salary, double deduction) {
            this.empId = empId;
            this.name = name;
            this.salary = salary;
            this.deduction = deduction;
        }
    }
}
