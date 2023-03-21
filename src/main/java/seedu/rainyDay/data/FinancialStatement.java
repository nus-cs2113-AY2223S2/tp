package seedu.rainyDay.data;

import java.io.Serializable;
import java.time.LocalDate;

public class FinancialStatement implements Serializable {
    private static final String INFLOW_WORD = "in";
    private static final String OUTFLOW_WORD = "out";
    private static final String INFLOW_SYMBOL = "+";
    private static final String OUTFLOW_SYMBOL = "-";

    public String description;
    public FlowDirection flowDirection;
    public double value;
    public String category;
    public LocalDate date = null;

    public FinancialStatement(String description, String flowDirection, double value, String category, LocalDate date) {
        this.description = description;
        if (flowDirection.equals(INFLOW_WORD)) {
            this.flowDirection = FlowDirection.INFLOW;
        } else {
            this.flowDirection = FlowDirection.OUTFLOW;
        }
        this.value = value;
        this.category = category;
        if (date != null) {
            this.date = date;
        }
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        assert (!this.description.isEmpty());
        return this.description;
    }

    public FlowDirection getFlowDirection() {
        return this.flowDirection;
    }

    public String getFlowDirectionWord() {
        if (flowDirection == FlowDirection.INFLOW) {
            return INFLOW_WORD;
        }
        return OUTFLOW_WORD;
    }

    public double getValue() {
        assert (this.value > 0);
        return this.value;
    }

    public String getFlowSymbol() {
        if (this.flowDirection == FlowDirection.INFLOW) {
            return INFLOW_SYMBOL;
        }
        return OUTFLOW_SYMBOL;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getFullStatement() {
        return String.format("%s for %s, %s$%.2f", getFlowDirectionWord(), getDescription(), getFlowSymbol(),
                getValue());
    }

    public String getStatementForList() {
        return String.format("%s %s$%.2f (%s)", getDescription(), getFlowSymbol(), getValue(), getFlowDirectionWord());
    }
}
