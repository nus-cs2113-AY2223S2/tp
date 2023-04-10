package seedu.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.expenditure.EntertainmentExpenditure;
import seedu.expenditure.ExpenditureList;

public class EntertainmentExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_academicExpenditureCommand_execute() {
        AcademicExpenditureCommand testAcademicExpenditureCommand = new AcademicExpenditureCommand(
                "new laptop",
                1500.5,
                LocalDate.parse("2023-02-01"));
        assertEquals("Added academic expenditure: " +
                "[Academic] || Date: 1 Feb 2023 || Value: 1500.5 || Description: new laptop",
                testAcademicExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Feb 2023 || Value: 1500.5 || Description: new laptop",
                testExpenditures.toString());
    }

    @Test
    public void test_entertainmentExpenditureCommand_execute() {
        EntertainmentExpenditureCommand testEntertainmentExpenditureCommand = new EntertainmentExpenditureCommand(
                "darts", 10, LocalDate.parse("2023-03-01"));
        assertEquals(
                "Added entertainment expenditure: [Entertainment] || Date: 1 Mar 2023 || Value: 10.0 "
                        + "|| Description: darts",
                testEntertainmentExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Entertainment] || Date: 1 Mar 2023 || Value: 10.0 || Description: darts",
                testExpenditures.toString());
    }

    @Test
    public void test_entertainmentExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new EntertainmentExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        EntertainmentExpenditureCommand testEntertainmentExpenditureCommand = new EntertainmentExpenditureCommand(
                "darts",
                10,
                LocalDate.parse("2023-03-01"));
        assertEquals(
                "Added entertainment expenditure: [Entertainment] || Date: 1 Mar 2023 || Value: 10.0 "
                        + "|| Description: darts",
                testEntertainmentExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals(
                "1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n"
                        + "2. [Entertainment] || Date: 1 Mar 2023 || Value: 10.0 || Description: darts",
                testExpenditures.toString());
    }
}
