// package chching;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import chching.command.BalanceCommand;
// import chching.record.Expense;
// import chching.record.ExpenseList;
// import chching.record.Income;
// import chching.record.IncomeList;

// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// public class CommandTest {
//     private final PrintStream standardOut = System.out;
//     private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

//     @BeforeEach
//     public void setUp() {
//         System.setOut(new PrintStream(outputStreamCaptor));
//     }

//     @Test
//     public void getBalance_expected() {
//         Expense expenseOne = new Expense("entertainment", "beach party", "23 may 2023", 50);
//         ExpenseList listE = new ExpenseList();
//         listE.addRecord(expenseOne);
        
//         Income incomeOne = new Income("entertainment", "beach party", "23 may 2023", 50);
//         IncomeList listI = new IncomeList();
//         listI.addRecord(incomeOne);

        
//     }
// }

