// $ANTLR 2.7.6 (2005-12-22): "decl.g" -> "ExprParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class ExprParser extends antlr.LLkParser       implements ExprParserTokenTypes
 {

   
   TabelaDeSimbolos ts;
   int posIni, posFinal;
   int tipo;
   
   

protected ExprParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public ExprParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected ExprParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public ExprParser(TokenStream lexer) {
  this(lexer,1);
}

public ExprParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void decl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			ts = new TabelaDeSimbolos();
			{
			int _cnt3=0;
			_loop3:
			do {
				if ((LA(1)==LITERAL_int||LA(1)==LITERAL_float)) {
					declara();
				}
				else {
					if ( _cnt3>=1 ) { break _loop3; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt3++;
			} while (true);
			}
			ts.printAll();
			match(END);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declara() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			tipo();
			var_lista();
			match(FIM);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void tipo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_int:
			{
				match(LITERAL_int);
				tipo = Simbolo.INTEGER;
				break;
			}
			case LITERAL_float:
			{
				match(LITERAL_float);
				tipo = Simbolo.REAL;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void var_lista() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			if (ts.exists(LT(0).getText()))
			{
							    System.err.println("ID ja declarado "+LT(0).getText());
								System.exit(0);
							 }
							 else
							 {
							     Simbolo s = new Simbolo(LT(0).getText(), tipo);
								 ts.add(s);
							 }
						
			{
			_loop8:
			do {
				if ((LA(1)==VIRGULA)) {
					match(VIRGULA);
					match(ID);
					if (ts.exists(LT(0).getText()))
					{
									    System.err.println("ID ja declarado "+LT(0).getText());
										System.exit(0);
									 }
									 else
									 {
									     Simbolo s = new Simbolo(LT(0).getText(), tipo);
										 ts.add(s);
									 }
								
				}
				else {
					break _loop8;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"END",
		"FIM",
		"\"int\"",
		"\"float\"",
		"ID",
		"VIRGULA",
		"WS"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 208L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 32L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
