package com.michaelsnowden.poe;

import org.antlr.v4.runtime.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author michael.snowden
 */
public class Interpreter {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please supply the name of a file you wish to interpret");
            return;
        }
        String arg = args[0];
        File file = new File(arg);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                run(fileInputStream);
            } catch (IOException e) {
                System.out.println("An error occurred while trying to run this file");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("That file does not exist");
            e.printStackTrace();
        }
    }

    public static void run(InputStream inputStream) throws IOException {
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
