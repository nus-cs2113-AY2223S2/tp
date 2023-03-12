package seedu.rainyDay;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    public static void writeToFile(FinancialReport statements, String filePath) {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            ArrayList<FinancialStatement> storeStatements = new ArrayList<>();
            for (int i = 0; i < statements.getStatementCount(); i += 1) {
                storeStatements.add(statements.getFinancialStatement(i));
            }

            writeStream.writeObject(storeStatements);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<FinancialStatement> loadFromFile(String filePath)
            throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        ArrayList<FinancialStatement> statements = (ArrayList<FinancialStatement>) readStream.readObject();
        readStream.close();
        readData.close();

        return statements;
    }
}
