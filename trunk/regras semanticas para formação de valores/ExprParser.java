// $ANTLR 2.7.6 (2005-12-22): "expr2.g" -> "ExprParser.java"$

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

   Numero n;
   int base, basetmp;

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

	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			atom();
			base = basetmp;
			{
			_loop4:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						atom();
						break;
					}
					case MINUS:
					{
						match(MINUS);
						atom();
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					
								                  if (base != basetmp)
												  {
					System.err.println("Operacao com bases incompativeis");
													   System.exit(0);
												  }
								           
								
				}
				else {
					break _loop4;
				}
				
			} while (true);
			}
			match(FIM);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void atom() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(NUM);
			
			int val = Integer.parseInt(LT(0).getText());
			switch(val)
			{
						     case 0:
							 case 1:
							    n = new Numero();
								n.setValor(val);
								break;
							 case 2:
							 case 3:
							 case 4:
							 case 5:
							 case 6:
							 case 7:
							 case 8:
							 case 9:
							    n = new Numero(val,10);
								break;
			}			 
					
			match(SUFIXO);
			if (LT(0).getText().equals("b") && n.getValor() > 1) 
					            {
			System.err.println("Erro semantico");
									System.exit(0);
			}
							 if (LT(0).getText().equals("b"))  
							     basetmp = 2;
						     else
							     basetmp = 10;
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"PLUS",
		"MINUS",
		"FIM",
		"NUM",
		"SUFIXO",
		"WS"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 112L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	
	}
