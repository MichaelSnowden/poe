package com.michaelsnowden.poe;

import org.testng.annotations.Test;

import static com.michaelsnowden.poe.Interpreter.run;

/**
 * @author michael.snowden
 */
public class EvalTest {
    @Test
    public void testEval() throws Exception {
        run(getClass().getResourceAsStream("/eval.poe"));
    }
}
