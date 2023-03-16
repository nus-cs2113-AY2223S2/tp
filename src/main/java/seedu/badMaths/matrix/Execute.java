package seedu.badMaths.matrix;

import java.util.HashMap;
import java.util.Map;

public class Execute {
    Map<String, Tensor2D> cache = new HashMap<>(500);

    public void parse(String command){
        int assignIndex;
        String assign = "";
        String calculation = "";

        command = command.replace(" ", "");

        if(command.contains("=")) {
            assignIndex = command.indexOf("=");
            assign = command.substring(0, assignIndex);
            calculation = command.substring(assignIndex + 1);

            cache.put(assign, execute(calculation));
        }else if(cache.containsKey(command)){
            assign = command;
        }

        System.out.println(assign);
        System.out.println(cache.get(assign));
    }

    public Tensor2D execute(String command) {
        Tensor2D result;

        if(command.contains("*")){
            result = executeMul(command);
        }else if(command.contains("[") && command.contains("]")){
            result = parseMatrix(command);
        }else{
            result = executeTranspose(command);
        }

        return result;
    }

    public Tensor2D executeMul(String command){
        Calculate c = new Calculate();

        String[] operator = command.split("\\*");

        Tensor2D t1 = executeTranspose(operator[0]);
        Tensor2D t2 = executeTranspose(operator[1]);

        return c.mul(t1, t2);
    }

    public Tensor2D executeTranspose(String command){
        String operator;
        if(command.contains(".T")){
            operator = command.replace(".T", "");
            return cache.get(operator).t();
        }else{
            return cache.get(command);
        }
    }

    public Tensor2D parseMatrix(String command) {
        int[][] tensor;
        int rowNum, colNum;

        command = command.replace("[", "");
        command = command.replace("]", "");

        String[] rows;
        String[] column;

        rows = command.split(";");
        rowNum = rows.length;
        colNum = rows[0].split(",").length;

        tensor = new int[rowNum][colNum];
        for(int i=0; i<rowNum; i++){
            for(int j=0; j<colNum; j++){
                column = rows[i].split(",");
                tensor[i][j] = Integer.parseInt(column[j]);
            }
        }

        return new Tensor2D(tensor);
    }
}
