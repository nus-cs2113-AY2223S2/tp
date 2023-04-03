package seedu.duke.data.ipptcalculator;


public class IPPTCmd {
    private final IPPTCalc ipptCalculator;

    public IPPTCmd(String[] userCommands) throws Exception {
        int ageGroup = Integer.parseInt(userCommands[0]);
        String userRunTimeInput = userCommands[1];
        int pushupReps = Integer.parseInt(userCommands[2]);
        int situpReps = Integer.parseInt(userCommands[3]);
        ipptCalculator = new IPPTCalc(ageGroup,userRunTimeInput,pushupReps,situpReps);
    }


    public void executeCommand(){
        UserScore userScore = new UserScore();
        try {
            int runScore = ipptCalculator.calculateRun();
            int pushupScore = ipptCalculator.calculatePushup();
            int situpScore = ipptCalculator.calculateSitup();
            userScore.setRunScore(runScore);
            userScore.setPushupScore(pushupScore);
            userScore.setSitupScore(situpScore);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

