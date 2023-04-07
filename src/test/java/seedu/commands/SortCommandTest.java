package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.BorrowExpenditure;
import seedu.expenditure.EntertainmentExpenditure;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.LendExpenditure;
import seedu.expenditure.OtherExpenditure;
import seedu.expenditure.TransportExpenditure;
import seedu.expenditure.TuitionExpenditure;


import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class SortCommandTest {
    private final ExpenditureList testExpenditures = new ExpenditureList();

    @BeforeEach
    public void setUpExpenditureList() {
        testExpenditures.addExpenditure(
                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-02")));
        testExpenditures.addExpenditure(
                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-23")));
        testExpenditures.addExpenditure(
                new TransportExpenditure("circle line", 2.10, LocalDate.parse("2023-03-22")));
        testExpenditures.addExpenditure(
                new TuitionExpenditure("school", 8100, LocalDate.parse("2022-09-10"),
                        LocalDate.parse("2022-09-10")));
        testExpenditures.addExpenditure(
                new AccommodationExpenditure("rc", 3000, LocalDate.parse("2021-12-30"),
                        LocalDate.parse("2021-12-30")));
        testExpenditures.addExpenditure(
                new EntertainmentExpenditure("cod skins", 4.99, LocalDate.parse("2021-08-08")));
        testExpenditures.addExpenditure(
                new OtherExpenditure("water bottle", 11.99, LocalDate.parse("2023-03-29")));
        testExpenditures.addExpenditure(
                new BorrowExpenditure("loan", "dbs", 20000,
                        LocalDate.parse("2021-08-06"), LocalDate.parse("2025-06-10")));
        testExpenditures.addExpenditure(
                new LendExpenditure("lending to james", "james", 200,
                        LocalDate.parse("2023-03-06"), LocalDate.parse("2023-04-06")));
    }

    @Test
    public void test_originalExpenditureList() {
        assertEquals("1. [Academic] || Date: 2 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "2. [Food] || Date: 23 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "3. [Transport] || Date: 22 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                "4. [Tuition] || [ ] || Date: 10 Sep 2022 || Value: 8100.0 || Description: school\n" +
                "5. [Accommodation] || [ ] || Date: 30 Dec 2021 || Value: 3000.0 || Description: rc\n" +
                "6. [Entertainment] || Date: 8 Aug 2021 || Value: 4.99 || Description: cod skins\n" +
                "7. [Other] || Date: 29 Mar 2023 || Value: 11.99 || Description: water bottle\n" +
                "8. [Borrow] || Borrowed from: dbs || Date: 6 Aug 2021 || Value: 20000.0 || Description: loan || " +
                "By: 10 Jun 2025\n" +
                "9. [Lend] || Lent to: james || Date: 6 Mar 2023 || Value: 200.0 || Description: lending to james || " +
                "by: 6 Apr 2023", testExpenditures.toString());
    }

    @Test
    public void test_sortAscending() {
        SortCommand sortAscending = new SortCommand("ascend");
        CommandResult sortAscendingCommandResult = sortAscending.execute(testExpenditures);
        assertEquals("Here is your list of expenditures sorted in ascending amount: \n" +
                "1. [Academic] || Date: 2 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "2. [Transport] || Date: 22 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                "3. [Food] || Date: 23 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "4. [Entertainment] || Date: 8 Aug 2021 || Value: 4.99 || Description: cod skins\n" +
                "5. [Other] || Date: 29 Mar 2023 || Value: 11.99 || Description: water bottle\n" +
                "6. [Lend] || Lent to: james || Date: 6 Mar 2023 || Value: 200.0 || Description: lending to james || " +
                "by: 6 Apr 2023\n" +
                "7. [Accommodation] || [ ] || Date: 30 Dec 2021 || Value: 3000.0 || Description: rc\n" +
                "8. [Tuition] || [ ] || Date: 10 Sep 2022 || Value: 8100.0 || Description: school\n" +
                "9. [Borrow] || Borrowed from: dbs || Date: 6 Aug 2021 || Value: 20000.0 || Description: loan || " +
                "By: 10 Jun 2025",
                sortAscendingCommandResult.getCommandResult());
    }

    @Test
    public void test_sortDescending() {
        SortCommand sortDescending = new SortCommand("descend");
        CommandResult sortDescendingCommandResult = sortDescending.execute(testExpenditures);
        assertEquals("Here is your list of expenditures sorted in descending amount: \n" +
                "1. [Borrow] || Borrowed from: dbs || Date: 6 Aug 2021 || Value: 20000.0 || " +
                "Description: loan || By: 10 Jun 2025\n" +
                "2. [Tuition] || [ ] || Date: 10 Sep 2022 || Value: 8100.0 || Description: school\n" +
                "3. [Accommodation] || [ ] || Date: 30 Dec 2021 || Value: 3000.0 || Description: rc\n" +
                "4. [Lend] || Lent to: james || Date: 6 Mar 2023 || Value: 200.0 || Description: lending to james ||" +
                " by: 6 Apr 2023\n" +
                "5. [Other] || Date: 29 Mar 2023 || Value: 11.99 || Description: water bottle\n" +
                "6. [Entertainment] || Date: 8 Aug 2021 || Value: 4.99 || Description: cod skins\n" +
                "7. [Food] || Date: 23 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "8. [Academic] || Date: 2 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "9. [Transport] || Date: 22 Mar 2023 || Value: 2.1 || Description: circle line",
                sortDescendingCommandResult.getCommandResult());
    }

    @Test
    public void test_sortLatest() {
        SortCommand sortLatest = new SortCommand("latest");
        CommandResult sortLatestCommandResult = sortLatest.execute(testExpenditures);
        assertEquals("Here is your list of expenditures sorted from the latest date: \n" +
                "1. [Other] || Date: 29 Mar 2023 || Value: 11.99 || Description: water bottle\n" +
                "2. [Food] || Date: 23 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "3. [Transport] || Date: 22 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                "4. [Lend] || Lent to: james || Date: 6 Mar 2023 || Value: 200.0 || " +
                "Description: lending to james || by: 6 Apr 2023\n" +
                "5. [Academic] || Date: 2 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "6. [Tuition] || [ ] || Date: 10 Sep 2022 || Value: 8100.0 || Description: school\n" +
                "7. [Accommodation] || [ ] || Date: 30 Dec 2021 || Value: 3000.0 || Description: rc\n" +
                "8. [Entertainment] || Date: 8 Aug 2021 || Value: 4.99 || Description: cod skins\n" +
                "9. [Borrow] || Borrowed from: dbs || Date: 6 Aug 2021 || Value: 20000.0 || " +
                "Description: loan " + "|| By: 10 Jun 2025",
                sortLatestCommandResult.getCommandResult());
    }

    @Test
    public void test_sortEarliest() {
        SortCommand sortEarliest = new SortCommand("earliest");
        CommandResult sortEarliestCommandResult = sortEarliest.execute(testExpenditures);
        assertEquals("Here is your list of expenditures sorted from the earliest date: \n" +
                "1. [Borrow] || Borrowed from: dbs || Date: 6 Aug 2021 || Value: 20000.0 || " +
                        "Description: loan || By: 10 Jun 2025\n" +
                "2. [Entertainment] || Date: 8 Aug 2021 || Value: 4.99 || Description: cod skins\n" +
                "3. [Accommodation] || [ ] || Date: 30 Dec 2021 || Value: 3000.0 || Description: rc\n" +
                "4. [Tuition] || [ ] || Date: 10 Sep 2022 || Value: 8100.0 || Description: school\n" +
                "5. [Academic] || Date: 2 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "6. [Lend] || Lent to: james || Date: 6 Mar 2023 || Value: 200.0 || " +
                        "Description: lending to james || by: 6 Apr 2023\n" +
                "7. [Transport] || Date: 22 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                "8. [Food] || Date: 23 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "9. [Other] || Date: 29 Mar 2023 || Value: 11.99 || Description: water bottle",
                sortEarliestCommandResult.getCommandResult());
    }
}
