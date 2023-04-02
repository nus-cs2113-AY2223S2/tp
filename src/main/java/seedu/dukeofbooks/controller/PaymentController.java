package seedu.dukeofbooks.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import seedu.dukeofbooks.data.exception.PaymentUnsuccessfulException;
import seedu.dukeofbooks.data.book.Book;
import seedu.dukeofbooks.data.book.BorrowableItem;

public class PaymentController {
    public static void makePayment(BorrowableItem toReturn, double amount) throws PaymentUnsuccessfulException {
        try (Scanner in = new Scanner(System.in)) {
            do {
                System.out.printf("Payment method:\n"
                        + "1.PayNow\n"
                        + "2.Debit/Credit card\n"
                        + "3.NETS\n"
                        + "4.Cash\n"
                        + "5.Cancel Payment\n");
                int choice = in.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid Choice!");
                } else if (choice > 0 && choice < 4) {
                    break;
                } else if (choice == 5) {
                    throw new PaymentUnsuccessfulException("Payment Unsuccessful...");
                }
            } while (true);
        }
        String tid = generateTID(toReturn);
        System.out.println("Payment Successful! Transaction ID: " + tid);
    }

    public static String generateTID(BorrowableItem toReturn) {
        Book book = (Book) toReturn;
        String tid;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddhhmm");
        Date tempDate = new Date();
        String date = sdf.format(tempDate);
        tid = book.getIsbn() + date;
        return tid;
    }
}
