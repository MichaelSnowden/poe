package com.michaelsnowden.poe;

import java.util.Map;

/**
 * @author michael.snowden
 */
public class EqualityCondition implements Condition {
    private final String variable;
    private final Integer value;
    private final Map<String, Integer> variables;

    public EqualityCondition(String variable, Integer value, Map<String, Integer> variables) {
        this.variable = variable;
        this.value = value;
        this.variables = variables;
    }

    @Override
    public boolean isTrue() {
        return variables.get(variable).equals(value);
    }
}
