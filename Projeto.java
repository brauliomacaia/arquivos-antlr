// $ANTLR 2.7.6 (2005-12-22): "projeto.g" -> "Projeto.java"$

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

public class Projeto extends antlr.LLkParser       implements ProjetoTokenTypes
 {

protected Projeto(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public Projeto(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected Projeto(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public Projeto(TokenStream lexer) {
  this(lexer,1);
}

public Projeto(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void cmdif() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_se);
			match(PAR1);
			expr();
			match(OP_REL);
			expr();
			match(PAR2);
			match(LITERAL_então);
			match(CHAVE1);
			{
			int _cnt3=0;
			_loop3:
			do {
				if ((LA(1)==LITERAL_se||LA(1)==ID)) {
					cmd();
				}
				else {
					if ( _cnt3>=1 ) { break _loop3; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt3++;
			} while (true);
			}
			match(CHAVE2);
			{
			switch ( LA(1)) {
			case LITERAL_senao:
			{
				match(LITERAL_senao);
				match(CHAVE1);
				{
				int _cnt6=0;
				_loop6:
				do {
					if ((LA(1)==LITERAL_se||LA(1)==ID)) {
						cmd();
					}
					else {
						if ( _cnt6>=1 ) { break _loop6; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt6++;
				} while (true);
				}
				match(CHAVE2);
				break;
			}
			case LITERAL_se:
			case CHAVE2:
			case ID:
			case LITERAL_fimprog:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			expr_l();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void cmd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				cmdexpr();
				break;
			}
			case LITERAL_se:
			{
				cmdif();
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
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void fator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case NUM:
			{
				match(NUM);
				break;
			}
			case ID:
			{
				match(ID);
				break;
			}
			case PAR1:
			{
				match(PAR1);
				expr();
				match(PAR2);
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
	
	public final void termo_l() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case VEZES:
			{
				match(VEZES);
				termo_l();
				break;
			}
			case LITERAL_se:
			case OP_REL:
			case PAR2:
			case CHAVE2:
			case ID:
			case DIVIDIR:
			case MAIS:
			case MENOS:
			case LITERAL_fimprog:
			{
				{
				switch ( LA(1)) {
				case DIVIDIR:
				{
					match(DIVIDIR);
					termo_l();
					break;
				}
				case LITERAL_se:
				case OP_REL:
				case PAR2:
				case CHAVE2:
				case ID:
				case MAIS:
				case MENOS:
				case LITERAL_fimprog:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
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
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			fator();
			termo_l();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void expr_l() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case MAIS:
			{
				match(MAIS);
				expr_l();
				break;
			}
			case LITERAL_se:
			case OP_REL:
			case PAR2:
			case CHAVE2:
			case ID:
			case MENOS:
			case LITERAL_fimprog:
			{
				{
				switch ( LA(1)) {
				case MENOS:
				{
					match(MENOS);
					expr_l();
					break;
				}
				case LITERAL_se:
				case OP_REL:
				case PAR2:
				case CHAVE2:
				case ID:
				case LITERAL_fimprog:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
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
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void cmdexpr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			match(IGUAL);
			expr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void bloco() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt18=0;
			_loop18:
			do {
				if ((LA(1)==LITERAL_se||LA(1)==ID)) {
					cmd();
				}
				else {
					if ( _cnt18>=1 ) { break _loop18; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt18++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void prog() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_programa);
			declara();
			bloco();
			match(LITERAL_fimprog);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void declara() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_declare);
			match(ID);
			{
			_loop22:
			do {
				if ((LA(1)==ID)) {
					match(ID);
				}
				else {
					break _loop22;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"se\"",
		"PAR1",
		"OP_REL",
		"PAR2",
		"\"ent\u00e3o\"",
		"CHAVE1",
		"CHAVE2",
		"\"senao\"",
		"NUM",
		"ID",
		"VEZES",
		"DIVIDIR",
		"MAIS",
		"MENOS",
		"IGUAL",
		"\"programa\"",
		"\"fimprog\"",
		"\"declare\"",
		"TEXTO"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 1057808L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 1058000L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 1303760L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 1254608L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 1048576L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 8208L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	
	}
