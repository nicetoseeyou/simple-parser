package lab.nice.predicate;

import java.util.List;
import java.util.Objects;

public class Rule {
    public String operand;
    public ComparisonOperator comparisonOperator;
    public List<String> condition;

    public Rule() {
    }

    public Rule(String operand, ComparisonOperator comparisonOperator, List<String> condition) {
        this.operand = operand;
        this.comparisonOperator = comparisonOperator;
        this.condition = condition;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void setCondition(List<String> condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(operand, rule.operand) &&
                comparisonOperator == rule.comparisonOperator &&
                Objects.equals(condition, rule.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, comparisonOperator, condition);
    }

    @Override
    public String toString() {
        return "{" +
                "operand='" + operand + '\'' +
                ", comparisonOperator=" + comparisonOperator +
                ", condition=" + condition +
                '}';
    }
}
