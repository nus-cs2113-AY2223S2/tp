package seedu.badmaths.matrix;

import seedu.badmaths.matrix.exception.ExceptionPrinter;
import seedu.badmaths.matrix.exception.UnknownOperatorException;
import seedu.badmaths.matrix.exception.ExceptionChecker;

public class Parser {
    ExceptionChecker check = new ExceptionChecker();
    ExceptionPrinter ep = new ExceptionPrinter();

    public Tensor2D parse(String command) {
        CalType type;
        Tensor2D result;
        Execute ex = new Execute();

        command = command.replace(" ", "");

        try {
            check.checkUnknownOperator(command);

            type = parseOp(command);
            result = ex.execute(type, command);

            return result;
        } catch (UnknownOperatorException e) {
            ep.printUnknownOperatorExceptionLog();
            return null;
        }
    }

    protected CalType parseOp(String command){
        if(command.contains("+")) {
            return CalType.ADDITION;
        } else if(command.contains("-")) {
            return CalType.SUBTRACTION;
        } else if(command.contains(".*")) {
            return CalType.MULTIPLICATION;
        } else if(command.contains("*")) {
            return CalType.ELEMENT_WISE_DOT_PRODUCT;
        } else {
            return CalType.UNKNOWN;
        }
    }

    public static Tensor2D parseMatrix(String command) {
        int[][] tensor;
        int rowNum;
        int colNum;

        command = command.replace("[", "");
        command = command.replace("]", "");

        String[] rows;
        String[] column;

        rows = command.split(";");
        rowNum = rows.length;
        colNum = rows[0].split(",").length;

        assert rowNum == 1 || colNum == rows[1].split(",").length;

        tensor = new int[rowNum][colNum];
        for(int i = 0; i < rowNum; i++) {
            for(int j = 0; j < colNum; j++) {
                column = rows[i].split(",");
                tensor[i][j] = Integer.parseInt(column[j]);
            }
        }

        return new Tensor2D(tensor);
    }

    enum CalType {
        ADDITION, SUBTRACTION, MULTIPLICATION, ELEMENT_WISE_DOT_PRODUCT, UNKNOWN
    }
}
