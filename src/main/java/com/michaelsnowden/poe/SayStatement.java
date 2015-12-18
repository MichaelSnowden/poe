package com.michaelsnowden.poe;

/**
 * @author michael.snowden
 */
public class SayStatement implements Statement {
    private final String text;

    public SayStatement(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        System.out.println(text);
    }
}
