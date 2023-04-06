package seedu.badmaths.matrix;

import seedu.badmaths.matrix.exception.ExceptionPrinter;
import seedu.badmaths.matrix.exception.UnknownOperatorException;
import seedu.badmaths.matrix.exception.ExceptionChecker;

public class Parser {
    ExceptionChecker check = new ExceptionChecker();
    ExceptionPrinter ep = new ExceptionPrinter();

    public Tensor2D parse(String command) {
        CalType type;

        command = command.replace(" ", "");

        try {
            check.checkUnknownOperator(command);

            if(command.contains("+")) {
                type = CalType.ADDITION;
            } else if(command.contains("-")) {
                type = CalType.SUBTRACTION;
            } else if(command.contains(".*")) {
                type = CalType.MULTIPLICATION;
            } else if(command.contains("*")) {
                type = CalType.ELEMENT_WISE_DOT_PRODUCT;
            } else {
                type = CalType.UNKNOWN;
            }

            Tensor2D op1;
            Tensor2D op2;
            Tensor2D result = null;
            String[] op;

            Calculate c = new Calculate();
            Execute e = new Execute();

            switch(type) {
            case ADDITION:
                result = e.executeAdd(command);
                break;
            case SUBTRACTION:
                result = e.executeSub(command);
                break;
            case MULTIPLICATION:
                result = e.executeMul(command);
                break;
            case ELEMENT_WISE_DOT_PRODUCT:
                result = e.executeDot(command);
                break;
            default:
                break;
            }

            return result;
        } catch (UnknownOperatorException e) {
            ep.printUnknownOperatorExceptionLog();
            return null;
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
