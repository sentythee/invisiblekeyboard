package sequence;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public final class Sequences {
	
	private Sequences() {
		
	}
	
	public static Sequence fromAsciiString(String string) {
		byte[] stringBytes = string.getBytes(Charset.forName("ASCII"));
		ByteArrayInputStream byteStream = new ByteArrayInputStream(stringBytes);
		
		return Sequences.fromInputStream(byteStream);
	}
	
	public static Sequence fromInputStream(InputStream stream) {
		try {
			ANTLRInputStream input = new ANTLRInputStream(stream);
			
			SequenceLexer lexer = new SequenceLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			SequenceParser parser = new SequenceParser(tokens);
			SequenceParser.sequence_return sequence = parser.sequence();
			CommonTree tree = (CommonTree) sequence.getTree();
			CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
		
			SequenceWalker walker = new SequenceWalker(nodes);
			return walker.sequence();
			
		} catch(Exception e) {
			throw new ParseException(e);
		}
	}
	
	public static class ParseException extends RuntimeException {
		public ParseException(Throwable t) {
			super(t);
		}
	}

}
