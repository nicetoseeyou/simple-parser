package lab.nice.predicate;

import java.util.Objects;

public class RuleEntry {
    public LogicOperator logicOperator;
    public Rule rule;

    public RuleEntry(){
    }

    public RuleEntry(LogicOperator logicOperator){
        this(logicOperator, null);
    }

    public RuleEntry(Rule rule){
        this(null, rule);
    }

    public RuleEntry(LogicOperator logicOperator, Rule rule){
        this.logicOperator = logicOperator;
        this.rule = rule;
    }

    public LogicOperator getLogicOperator() {
        return logicOperator;
    }

    public void setLogicOperator(LogicOperator logicOperator) {
        this.logicOperator = logicOperator;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleEntry ruleEntry = (RuleEntry) o;
        return logicOperator == ruleEntry.logicOperator &&
                Objects.equals(rule, ruleEntry.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logicOperator, rule);
    }

    @Override
    public String toString() {
        return "{" +
                "logicOperator=" + logicOperator +
                ", rule=" + rule +
                '}';
    }
}
