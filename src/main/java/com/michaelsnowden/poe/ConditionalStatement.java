package com.michaelsnowden.poe;

/**
 * @author michael.snowden
 */
public class ConditionalStatement implements Sentence {
    private final Condition condition;
    private final Statement statement;

    public ConditionalStatement(Condition condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void evaluate() {
        if (condition.isTrue()) {
            statement.execute();
        }
    }
}
