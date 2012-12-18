// $ANTLR 3.4 Sequence.g 2012-12-15 03:06:04

	package sequence;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class SequenceParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEQUENCE", "TERMINATOR", "UNIT", "WORD"
    };

    public static final int EOF=-1;
    public static final int SEQUENCE=4;
    public static final int TERMINATOR=5;
    public static final int UNIT=6;
    public static final int WORD=7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public SequenceParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public SequenceParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return SequenceParser.tokenNames; }
    public String getGrammarFileName() { return "Sequence.g"; }


    public static class sequence_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sequence"
    // Sequence.g:23:1: sequence : ( unit )+ -> ^( SEQUENCE ( unit )+ ) ;
    public final SequenceParser.sequence_return sequence() throws RecognitionException {
        SequenceParser.sequence_return retval = new SequenceParser.sequence_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        SequenceParser.unit_return unit1 =null;


        RewriteRuleSubtreeStream stream_unit=new RewriteRuleSubtreeStream(adaptor,"rule unit");
        try {
            // Sequence.g:24:2: ( ( unit )+ -> ^( SEQUENCE ( unit )+ ) )
            // Sequence.g:24:4: ( unit )+
            {
            // Sequence.g:24:4: ( unit )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==WORD) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Sequence.g:24:4: unit
            	    {
            	    pushFollow(FOLLOW_unit_in_sequence61);
            	    unit1=unit();

            	    state._fsp--;

            	    stream_unit.add(unit1.getTree());

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


            // AST REWRITE
            // elements: unit
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 25:3: -> ^( SEQUENCE ( unit )+ )
            {
                // Sequence.g:25:6: ^( SEQUENCE ( unit )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(SEQUENCE, "SEQUENCE")
                , root_1);

                if ( !(stream_unit.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_unit.hasNext() ) {
                    adaptor.addChild(root_1, stream_unit.nextTree());

                }
                stream_unit.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sequence"


    public static class unit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unit"
    // Sequence.g:28:1: unit : WORD TERMINATOR -> ^( UNIT WORD TERMINATOR ) ;
    public final SequenceParser.unit_return unit() throws RecognitionException {
        SequenceParser.unit_return retval = new SequenceParser.unit_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token WORD2=null;
        Token TERMINATOR3=null;

        CommonTree WORD2_tree=null;
        CommonTree TERMINATOR3_tree=null;
        RewriteRuleTokenStream stream_WORD=new RewriteRuleTokenStream(adaptor,"token WORD");
        RewriteRuleTokenStream stream_TERMINATOR=new RewriteRuleTokenStream(adaptor,"token TERMINATOR");

        try {
            // Sequence.g:29:2: ( WORD TERMINATOR -> ^( UNIT WORD TERMINATOR ) )
            // Sequence.g:29:4: WORD TERMINATOR
            {
            WORD2=(Token)match(input,WORD,FOLLOW_WORD_in_unit88);  
            stream_WORD.add(WORD2);


            TERMINATOR3=(Token)match(input,TERMINATOR,FOLLOW_TERMINATOR_in_unit90);  
            stream_TERMINATOR.add(TERMINATOR3);


            // AST REWRITE
            // elements: TERMINATOR, WORD
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 30:3: -> ^( UNIT WORD TERMINATOR )
            {
                // Sequence.g:30:6: ^( UNIT WORD TERMINATOR )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(UNIT, "UNIT")
                , root_1);

                adaptor.addChild(root_1, 
                stream_WORD.nextNode()
                );

                adaptor.addChild(root_1, 
                stream_TERMINATOR.nextNode()
                );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unit"

    // Delegated rules


 

    public static final BitSet FOLLOW_unit_in_sequence61 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_WORD_in_unit88 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_TERMINATOR_in_unit90 = new BitSet(new long[]{0x0000000000000002L});

}