package com.michaelsnowden.poe;

import org.antlr.v4.runtime.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author michael.snowden
 */
public class EvalTest {
    @Test
    public void testEval() throws Exception {
        run(getClass().getResourceAsStream("/eval.poe"));
    }

    private void run(InputStream inputStream) throws IOException {
        PoeProgramLexer l = new PoeProgramLexer(new ANTLRInputStream(inputStream));
        PoeProgramParser p = new PoeProgramParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int
                    charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        Map<String, Integer> variables = new HashMap<>();
        p.addParseListener(new PoeProgramBaseListener() {
            @Override
            public void exitConditional(PoeProgramParser.ConditionalContext ctx) {
                super.exitConditional(ctx);
                String variable = ctx.condition().Variable().getText();
                int value = Integer.parseInt(ctx.condition().Value().getText());
                if (variables.get(variable).equals(value)) {
                    System.out.println(ctx.conditionalDialogue().dialogue().Something());
                }
            }

            @Override
            public void exitUnconditionalDialogue(PoeProgramParser.UnconditionalDialogueContext ctx) {
                super.exitUnconditionalDialogue(ctx);
                System.out.println(ctx.dialogue().Something());
            }

            @Override
            public void exitAssignment(PoeProgramParser.AssignmentContext ctx) {
                super.exitAssignment(ctx);
                variables.put(ctx.Variable().getText(), Integer.valueOf(ctx.Value().getText()));
            }
        });
        p.program();
    }
}
