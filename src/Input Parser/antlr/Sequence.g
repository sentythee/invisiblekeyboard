grammar Sequence;

options {
	output=AST;
	ASTLabelType=CommonTree;
}

tokens {
	UNIT;
	SEQUENCE;
}

@parser::header {
	package sequence;
}

@lexer::header {
	package sequence;
}



sequence
	:	unit+
		-> ^(SEQUENCE unit+)  
	; 
	
unit
	:	WORD TERMINATOR
		-> ^(UNIT WORD TERMINATOR)
	;


WORD
	:	('a'..'z')+
	;

TERMINATOR
	:	', '
	|	'. '
	|	'.'
	|	'\n'
	|	' '
	;

