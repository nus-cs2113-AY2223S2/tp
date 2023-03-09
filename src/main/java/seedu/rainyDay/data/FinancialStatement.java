package seedu.rainyDay.data;

import java.io.Serializable;

public class FinancialStatement implements Serializable {
    private static final String INFLOW_WORD = "in";
    private static final String OUTFLOW_WORD = "out";
    private static final String INFLOW_SYMBOL = "+";
    private static final String OUTFLOW_SYMBOL = "-";

    public String description;
    public FlowDirection flowDirection;
    public int value;

    public FinancialStatement(String description, String flowDirection, int value) {
        this.description = description;
        if (flowDirection.equals(INFLOW_WORD)) {
            this.flowDirection = FlowDirection.INFLOW;
        } else {
            this.flowDirection = FlowDirection.OUTFLOW;
        }
        this.value = value;
    }

    protected String getDescription() {
        return this.description;
    }

    public String getFlowDirection() {
        if (flowDirection == FlowDirection.INFLOW) {
            return INFLOW_WORD;
        } else {
            return OUTFLOW_WORD;
        }
    }

    public int getValue() {
        return this.value;
    }

    public String getFlowSymbol() {
        if (this.flowDirection == FlowDirection.INFLOW) {
            return INFLOW_SYMBOL;
        }
        return OUTFLOW_SYMBOL;
    }

    public String getFullStatement() {
        String symbolValue = String.join("", this.getFlowSymbol(), "$", String.valueOf(this.value));
        String flowInformation = String.join("", "(", this.getFlowDirection(), ")");
        String statement = String.join(" ", this.description, symbolValue, flowInformation);
        return statement;
    }
}
