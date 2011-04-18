// $ANTLR 2.7.6 (2005-12-22): "projeto.g" -> "ProjetoParser.java"$

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

public class ProjetoParser extends antlr.LLkParser       implements ProjetoParserTokenTypes
 {

	TabelaDeSimbolos ts = new TabelaDeSimbolos();
	Simbolo s;
	int dtype;

protected ProjetoParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public ProjetoParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected ProjetoParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public ProjetoParser(TokenStream lexer) {
  this(lexer,1);
}

public ProjetoParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void prog() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			NumeroLinha.NLINHA=0;
			match(LITERAL_programa);
			match(PONTO);
			declara();
			bloco();
			match(LITERAL_fimprog);
			match(PONTO);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declara() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_declare);
			{
			switch ( LA(1)) {
			case LITERAL_int:
			{
				match(LITERAL_int);
				dtype=1;
				break;
			}
			case LITERAL_string:
			{
				match(LITERAL_string);
				dtype=2;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(ID);
			
							s = new Simbolo(LT(0).getText(), dtype);
							if(ts.exists(s.getNome()) == false){
								ts.add(s);
								System.out.println("add " + s.getNome());
							}else{
								System.err.println("Erro: Variavel já declarada");
								System.exit(0);
								}
							
			{
			_loop10:
			do {
				if ((LA(1)==VIRGULA)) {
					match(VIRGULA);
					match(ID);
					
											s = new Simbolo(LT(0).getText(), dtype);
											if(ts.exists(s.getNome()) == false){
												ts.add(s);
												System.out.println("add " + s.getNome());
											}else{
												System.err.println("Erro: Variavel \"" + LT(0).getText() + "\" ja declarada");
												System.exit(0);
												}
											
				}
				else {
					break _loop10;
				}
				
			} while (true);
			}
			match(PONTO);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void bloco() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt4=0;
			_loop4:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					cmd();
				}
				else {
					if ( _cnt4>=1 ) { break _loop4; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt4++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void cmd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_leia:
			{
				cmdleitura();
				break;
			}
			case LITERAL_escreva:
			{
				cmdescrita();
				break;
			}
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
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cmdleitura() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_leia);
			match(PAR1);
			match(ID);
			
											if(!ts.exists(LT(0).getText())){ 
												System.err.println("Erro: Variavel \"" + LT(0).getText() + "\" não declarada");
												System.exit(0);
											}
											
			match(PAR2);
			match(PONTO);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cmdescrita() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_escreva);
			match(PAR1);
			escreve();
			match(PAR2);
			match(PONTO);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cmdexpr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			match(IGUAL);
			expr();
			match(PONTO);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cmdif() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_se);
			match(PAR1);
			expr();
			match(OP_REL);
			expr();
			match(PAR2);
			match(LITERAL_entao);
			match(CHAVE1);
			{
			int _cnt16=0;
			_loop16:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					cmd();
				}
				else {
					if ( _cnt16>=1 ) { break _loop16; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt16++;
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
				int _cnt19=0;
				_loop19:
				do {
					if ((_tokenSet_1.member(LA(1)))) {
						cmd();
					}
					else {
						if ( _cnt19>=1 ) { break _loop19; } else {throw new NoViableAltException(LT(1), getFilename());}
					}
					
					_cnt19++;
				} while (true);
				}
				match(CHAVE2);
				break;
			}
			case LITERAL_fimprog:
			case ID:
			case LITERAL_leia:
			case LITERAL_escreva:
			case LITERAL_se:
			case CHAVE2:
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
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void escreve() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				break;
			}
			case TEXTO:
			{
				match(TEXTO);
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			expr_l();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			fator();
			termo_l();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void expr_l() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case MAIS:
			{
				{
				match(MAIS);
				termo();
				expr_l();
				}
				break;
			}
			case PONTO:
			case PAR2:
			case OP_REL:
			case MENOS:
			{
				{
				switch ( LA(1)) {
				case MENOS:
				{
					match(MENOS);
					termo();
					expr_l();
					break;
				}
				case PONTO:
				case PAR2:
				case OP_REL:
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
			recover(ex,_tokenSet_5);
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
			recover(ex,_tokenSet_7);
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
			case PONTO:
			case PAR2:
			case OP_REL:
			case DIVIDIR:
			case MAIS:
			case MENOS:
			{
				{
				switch ( LA(1)) {
				case DIVIDIR:
				{
					match(DIVIDIR);
					termo_l();
					break;
				}
				case PONTO:
				case PAR2:
				case OP_REL:
				case MAIS:
				case MENOS:
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
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void cmddo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_faca);
			match(CHAVE1);
			{
			int _cnt31=0;
			_loop31:
			do {
				if ((LA(1)==CMD)) {
					match(CMD);
					match(PONTO);
				}
				else {
					if ( _cnt31>=1 ) { break _loop31; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt31++;
			} while (true);
			}
			match(CHAVE2);
			match(LITERAL_enquanto);
			match(PAR1);
			expr();
			match(OP_REL);
			expr();
			match(PAR2);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"programa\"",
		"PONTO",
		"\"fimprog\"",
		"\"declare\"",
		"\"int\"",
		"\"string\"",
		"ID",
		"VIRGULA",
		"\"leia\"",
		"PAR1",
		"PAR2",
		"\"escreva\"",
		"TEXTO",
		"\"se\"",
		"OP_REL",
		"\"entao\"",
		"CHAVE1",
		"CHAVE2",
		"\"senao\"",
		"IGUAL",
		"NUM",
		"VEZES",
		"DIVIDIR",
		"MAIS",
		"MENOS",
		"\"faca\"",
		"CMD",
		"\"enquanto\"",
		"WS",
		"NL"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 168960L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 64L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2266176L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 16384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 278560L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 402931744L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 503595040L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
