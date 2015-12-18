grammar Program;

program: (sentence '.')*;
sentence: Variable ' is ' Value | 'If ' condition ', then ' consequent;
condition: Variable ' is ' Value;
consequent: 'Say ' Something;

Variable: 'x' | 'y' | 'z';
Value: '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
Something: 'hi' | 'bye';