package seedu.rainyDay.data;

import java.time.LocalDate;

public class FinancialStatement {
    private static final String INFLOW_WORD = "in";
    private static final String OUTFLOW_WORD = "out";
    private static final String INFLOW_SYMBOL = "+";
    private static final String OUTFLOW_SYMBOL = "-";
    public String description;
    public FlowDirection flowDirection;
    public double value;
    public String category;
    public LocalDate date;
    private boolean isIgnored;

    public FinancialStatement(String description, String flowDirection, double value, String category, LocalDate date) {
        this.description = description;
        if (flowDirection.equals(INFLOW_WORD)) {
            this.flowDirection = FlowDirection.INFLOW;
        } else {
            this.flowDirection = FlowDirection.OUTFLOW;
        }
        this.value = value;
        this.category = category;
        this.date = date;
        this.isIgnored = false;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        assert (!this.description.isEmpty());
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FlowDirection getFlowDirection() {
        return this.flowDirection;
    }

    public void setFlowDirection(FlowDirection flowDirection) {
        this.flowDirection = flowDirection;
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

    public void setValue(double value) {
        this.value = value;
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

    public int getMonthAndYear() {
        return date.getYear() * 12 + date.getMonthValue();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFullStatement() {
        return String.format("%s for %s, %s$%.2f", getFlowDirectionWord(), getDescription(), getFlowSymbol(),
                getValue());
    }

    public String getStatementForList() {
        return String.format("%s %s$%.2f (%s)", getDescription(), getFlowSymbol(), getValue(), getFlowDirectionWord());
    }

    public boolean isIgnored() {
        return this.isIgnored;
    }

    public void setIgnore(boolean ignoreStatus) {
        this.isIgnored = ignoreStatus;
    }
}
