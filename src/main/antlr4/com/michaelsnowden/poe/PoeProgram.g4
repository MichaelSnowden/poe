grammar PoeProgram;

program: sentence ('. ' sentence)* '.' EOF;
sentence: unconditionalDialogue | assignment | conditional;
assignment: Variable ' is ' Value;
conditional: 'If ' condition ', then ' conditionalDialogue;
condition: Variable ' is ' Value;
unconditionalDialogue: 'Say ' Something;
conditionalDialogue: dialogue;
dialogue: 'say ' Something;

Variable: 'X' | 'Y' | 'Z';
Value: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
Something: 'hi' | 'bye';