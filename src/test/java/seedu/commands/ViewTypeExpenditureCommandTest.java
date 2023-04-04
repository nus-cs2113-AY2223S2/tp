//package seedu.commands;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.expenditure.AcademicExpenditure;
//import seedu.expenditure.ExpenditureList;
//import seedu.expenditure.FoodExpenditure;
//import seedu.expenditure.TransportExpenditure;
//import seedu.expenditure.TuitionExpenditure;
//import seedu.expenditure.AccommodationExpenditure;
//import java.time.LocalDate;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import seedu.exceptions.WrongInputException;
//
//public class ViewTypeExpenditureCommandTest {
//    ExpenditureList testExpenditures = new ExpenditureList();
//
//    @BeforeEach
//    public void setUpExpenditureList() {
//        testExpenditures.addExpenditure(
//                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-01")));
//        testExpenditures.addExpenditure(
//                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-21")));
//        testExpenditures.addExpenditure(
//                new TransportExpenditure("circle line", 2.10, LocalDate.parse("2023-03-21")));
//        testExpenditures.addExpenditure(
//                new TuitionExpenditure("school", 8100, LocalDate.parse("2023-01-27")));
//        testExpenditures.addExpenditure(
//                new AccommodationExpenditure("rc", 3000, LocalDate.parse("2023-02-02")));
//        testExpenditures.addExpenditure(
//                new AcademicExpenditure("NUS", 2.2, LocalDate.parse("2023-02-02")));
//    }
//
//    @Test
//    public void test_viewTypeExpenditureCommand_onNoType() throws WrongInputException {
//        ViewTypeExpenditureCommand testViewTypeOneType = new ViewTypeExpenditureCommand("lend");
//        assertEquals("Here are the specified expenditures: \n" +
//                        "Total amount: 0.00",
//                testViewTypeOneType.execute(testExpenditures).getCommandResult());
//    }
//
//    @Test
//    public void test_viewTypeExpenditureCommand_onOneType() throws WrongInputException {
//        ViewTypeExpenditureCommand testViewTypeOneType = new ViewTypeExpenditureCommand("food");
//        assertEquals("Here are the specified expenditures: \n" +
//                        "1. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
//                        "Total amount: 4.50",
//                testViewTypeOneType.execute(testExpenditures).getCommandResult());
//    }
//
//    @Test
//    public void test_viewTypeExpenditureCommand_onTwoType() throws WrongInputException {
//        ViewTypeExpenditureCommand testViewTypeOneType = new ViewTypeExpenditureCommand("academic");
//        assertEquals("Here are the specified expenditures: \n" +
//                        "1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
//                        "2. [Academic] || Date: 2 Feb 2023 || Value: 2.2 || Description: NUS\n" +
//                        "Total amount: 4.30",
//                testViewTypeOneType.execute(testExpenditures).getCommandResult());
//    }
//}
