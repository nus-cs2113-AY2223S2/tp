package seedu.bankwithus.user;

import seedu.bankwithus.common.SaveGoal;
import seedu.bankwithus.common.WithdrawalChecker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Account {
    public SaveGoal saveGoal;
<<<<<<< HEAD
    private final String name;
    private String balance;
    private final WithdrawalChecker withdrawalChecker;

=======
    private String name;
    private BigDecimal balance;
    private WithdrawalChecker withdrawalChecker;
    //@@author Sherlock-YH
>>>>>>> 5d19a837abbe8039203ad4db889582c026cfe4ca
    /**
     * Instantiates an account object.
     *
     * @param name    initialise in the name of the account
     * @param balance initialise the balance of the account
     */
    public Account(String name, String balance, String amtToSave, LocalDate untilWhen) {
        this.name = name;
        this.balance = new BigDecimal(balance).setScale(2, RoundingMode.CEILING);
        this.withdrawalChecker = new WithdrawalChecker();
        this.saveGoal = new SaveGoal(new BigDecimal(amtToSave), untilWhen);
    }

    //@@author tyuyang
    public Account(String name, String balance, String totalAmtWithdrawn,
<<<<<<< HEAD
                   LocalDate lastWithdrawnDate, String amtToSave, String untilWhen) {
=======
            LocalDate lastWithdrawnDate, String amtToSave, LocalDate untilWhen) {
>>>>>>> 5d19a837abbe8039203ad4db889582c026cfe4ca
        this.name = name;
        this.balance = new BigDecimal(balance).setScale(2, RoundingMode.CEILING);;
        this.withdrawalChecker = new WithdrawalChecker(totalAmtWithdrawn, lastWithdrawnDate);
        this.saveGoal = new SaveGoal(new BigDecimal(amtToSave), untilWhen);
    }
    //@@author Sherlock-YH
    public String getAccountName() {
        return name;
    }
    //@@author Sherlock-YH
    public BigDecimal getAccountBalance() {
        return balance;
    }

    public WithdrawalChecker getWithdrawalChecker() {
        return withdrawalChecker;
    }

    //@@author xiaoge26
    public void addBalance(BigDecimal balanceToBeAdded) {
        this.balance = this.balance.add(balanceToBeAdded).setScale(2, RoundingMode.CEILING);
    }

    //@@author manushridiv
    public void subtractBalance(BigDecimal currentBalance, BigDecimal withdrawal) {
        this.balance = currentBalance.subtract(withdrawal);
        withdrawalChecker.updateTotalAmtWithdrawn(withdrawal); //check later
    }

    //@@author vishnuvk47
    public void setSaveGoal(SaveGoal saveGoal, String args, String untilWhenStr) {

        this.saveGoal = saveGoal;
    }

    public String getName() {
        return this.name;
    }
    public SaveGoal getSaveGoal() {
        return this.saveGoal;
    }
}




