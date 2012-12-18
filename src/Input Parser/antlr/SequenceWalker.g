tree grammar SequenceWalker;

options {
	tokenVocab=Sequence;
	ASTLabelType=CommonTree;
}

@header {
	package sequence;
	import sequence.Sequence;
	import sequence.Sequence.Unit;
}

sequence returns [Sequence result]
	:	^(SEQUENCE {$result = new Sequence();}
			(u=unit {$result.addUnit(u);})+
		)
	;

unit returns [Unit result]
	:	^(UNIT WORD TERMINATOR) {$result = new Unit($WORD.text, $TERMINATOR.text);}
	;
