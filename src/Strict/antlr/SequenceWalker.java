// $ANTLR 3.4 SequenceWalker.g 2012-12-15 02:55:31

	package sequence;
	import sequence.Sequence;
	import sequence.Sequence.Unit;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SequenceWalker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEQUENCE", "TERMINATOR", "UNIT", "WORD"
    };

    public static final int EOF=-1;
    public static final int SEQUENCE=4;
    public static final int TERMINATOR=5;
    public static final int UNIT=6;
    public static final int WORD=7;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public SequenceWalker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public SequenceWalker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return SequenceWalker.tokenNames; }
    public String getGrammarFileName() { return "SequenceWalker.g"; }



    // $ANTLR start "sequence"
    // SequenceWalker.g:14:1: sequence returns [Sequence result] : ^( SEQUENCE (u= unit )+ ) ;
    public final Sequence sequence() throws RecognitionException {
        Sequence result = null;


        Unit u =null;


        try {
            // SequenceWalker.g:15:2: ( ^( SEQUENCE (u= unit )+ ) )
            // SequenceWalker.g:15:4: ^( SEQUENCE (u= unit )+ )
            {
            match(input,SEQUENCE,FOLLOW_SEQUENCE_in_sequence41); 

            result = new Sequence();

            match(input, Token.DOWN, null); 
            // SequenceWalker.g:16:4: (u= unit )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==UNIT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // SequenceWalker.g:16:5: u= unit
            	    {
            	    pushFollow(FOLLOW_unit_in_sequence51);
            	    u=unit();

            	    state._fsp--;


            	    result.addUnit(u);

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "sequence"



    // $ANTLR start "unit"
    // SequenceWalker.g:20:1: unit returns [Unit result] : ^( UNIT WORD TERMINATOR ) ;
    public final Unit unit() throws RecognitionException {
        Unit result = null;


        CommonTree WORD1=null;
        CommonTree TERMINATOR2=null;

        try {
            // SequenceWalker.g:21:2: ( ^( UNIT WORD TERMINATOR ) )
            // SequenceWalker.g:21:4: ^( UNIT WORD TERMINATOR )
            {
            match(input,UNIT,FOLLOW_UNIT_in_unit75); 

            match(input, Token.DOWN, null); 
            WORD1=(CommonTree)match(input,WORD,FOLLOW_WORD_in_unit77); 

            TERMINATOR2=(CommonTree)match(input,TERMINATOR,FOLLOW_TERMINATOR_in_unit79); 

            match(input, Token.UP, null); 


            result = new Unit((WORD1!=null?WORD1.getText():null), (TERMINATOR2!=null?TERMINATOR2.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "unit"

    // Delegated rules


 

    public static final BitSet FOLLOW_SEQUENCE_in_sequence41 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_unit_in_sequence51 = new BitSet(new long[]{0x0000000000000048L});
    public static final BitSet FOLLOW_UNIT_in_unit75 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_WORD_in_unit77 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_TERMINATOR_in_unit79 = new BitSet(new long[]{0x0000000000000008L});

}