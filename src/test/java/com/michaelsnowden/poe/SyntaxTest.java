package com.michaelsnowden.poe;

import org.antlr.v4.runtime.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author michael.snowden
 */
public class SyntaxTest {
    @Test
    public void testSyntax() throws Exception {
        verify("/test.poe");
    }

    private void verify(String file) throws IOException {
        PoeProgramLexer l = new PoeProgramLexer(new ANTLRInputStream(getClass().getResourceAsStream(file)));
        PoeProgramParser p = new PoeProgramParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int
                    charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        p.program();
    }
}
