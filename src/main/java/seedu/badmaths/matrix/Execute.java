package seedu.badmaths.matrix;

public class Execute {

    public Tensor2D execute(Parser.CalType type, String command){
        Tensor2D result = null;

        switch(type) {
        case ADDITION:
            result = executeAdd(command);
            break;
        case SUBTRACTION:
            result = executeSub(command);
            break;
        case MULTIPLICATION:
            result = executeMul(command);
            break;
        case ELEMENT_WISE_DOT_PRODUCT:
            result = executeDot(command);
            break;
        default:
            break;
        }

        return result;
    }

    public Tensor2D executeMul(String command) {
        Calculate c = new Calculate();

        String[] operator = command.split("\\.\\*");

        Tensor2D result;
        Tensor2D t1 = executeTranspose(operator[0]);
        Tensor2D t2 = executeTranspose(operator[1]);

        result = c.mul(t1, t2);
        assert result != null;

        return result;
    }

    public Tensor2D executeDot(String command) {
        Calculate c = new Calculate();

        String[] operator = command.split("\\*");

        Tensor2D result;
        Tensor2D t1 = executeTranspose(operator[0]);
        Tensor2D t2 = executeTranspose(operator[1]);

        result = c.dot(t1, t2);
        assert result != null;

        return result;
    }

    public Tensor2D executeAdd(String command) {
        Calculate c = new Calculate();

        String[] operator = command.split("\\+");

        Tensor2D result;
        Tensor2D t1 = executeTranspose(operator[0]);
        Tensor2D t2 = executeTranspose(operator[1]);

        result = c.add(t1, t2);
        assert result != null;

        return result;
    }

    public Tensor2D executeSub(String command) {
        Calculate c = new Calculate();

        String[] operator = command.split("-");

        Tensor2D result;
        Tensor2D t1 = executeTranspose(operator[0]);
        Tensor2D t2 = executeTranspose(operator[1]);

        result = c.sub(t1, t2);
        assert result != null;

        return result;
    }

    public Tensor2D executeTranspose(String command) {
        String operator;
        if(command.contains(".T")) {
            operator = command.replace(".T", "");
            return Parser.parseMatrix(operator).t();
        } else {
            return Parser.parseMatrix(command);
        }
    }
}
