// $ANTLR 3.4 Sequence.g 2012-12-15 02:44:24

	package sequence;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SequenceLexer extends Lexer {
    public static final int EOF=-1;
    public static final int SEQUENCE=4;
    public static final int TERMINATOR=5;
    public static final int UNIT=6;
    public static final int WORD=7;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public SequenceLexer() {} 
    public SequenceLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SequenceLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "Sequence.g"; }

    // $ANTLR start "WORD"
    public final void mWORD() throws RecognitionException {
        try {
            int _type = WORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sequence.g:34:2: ( ( 'a' .. 'z' )+ )
            // Sequence.g:34:4: ( 'a' .. 'z' )+
            {
            // Sequence.g:34:4: ( 'a' .. 'z' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Sequence.g:
            	    {
            	    if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WORD"

    // $ANTLR start "TERMINATOR"
    public final void mTERMINATOR() throws RecognitionException {
        try {
            int _type = TERMINATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Sequence.g:38:2: ( ', ' | '. ' | '.' | '\\n' | ' ' )
            int alt2=5;
            switch ( input.LA(1) ) {
            case ',':
                {
                alt2=1;
                }
                break;
            case '.':
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==' ') ) {
                    alt2=2;
                }
                else {
                    alt2=3;
                }
                }
                break;
            case '\n':
                {
                alt2=4;
                }
                break;
            case ' ':
                {
                alt2=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // Sequence.g:38:4: ', '
                    {
                    match(", "); 



                    }
                    break;
                case 2 :
                    // Sequence.g:39:4: '. '
                    {
                    match(". "); 



                    }
                    break;
                case 3 :
                    // Sequence.g:40:4: '.'
                    {
                    match('.'); 

                    }
                    break;
                case 4 :
                    // Sequence.g:41:4: '\\n'
                    {
                    match('\n'); 

                    }
                    break;
                case 5 :
                    // Sequence.g:42:4: ' '
                    {
                    match(' '); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TERMINATOR"

    public void mTokens() throws RecognitionException {
        // Sequence.g:1:8: ( WORD | TERMINATOR )
        int alt3=2;
        int LA3_0 = input.LA(1);

        if ( ((LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
            alt3=1;
        }
        else if ( (LA3_0=='\n'||LA3_0==' '||LA3_0==','||LA3_0=='.') ) {
            alt3=2;
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("", 3, 0, input);

            throw nvae;

        }
        switch (alt3) {
            case 1 :
                // Sequence.g:1:10: WORD
                {
                mWORD(); 


                }
                break;
            case 2 :
                // Sequence.g:1:15: TERMINATOR
                {
                mTERMINATOR(); 


                }
                break;

        }

    }


 

}