class ProjetoParser extends Parser;

{
	TabelaDeSimbolos ts = new TabelaDeSimbolos();
	TabelaReservada tr = new TabelaReservada();
	Simbolo s;
	int dtype;
}

prog	:	{NumeroLinha.NLINHA=0;}"programa" 
		    PONTO 
			(declara)*
			bloco
			"fimprog" 
			PONTO
		;

bloco	:	(cmd)+
		;

cmd	:	(cmdleitura
		|cmdescrita
		|cmdexpr
		|cmdif)
	;

declara	:	"declare" ("int" {dtype=1;}| "string" {dtype=2;})
			
			ID {				
				s = new Simbolo(LT(0).getText(), dtype);
				//System.out.println("nome da variavel " + s.getNome());
				if(tr.exists(s.getNome())){
					System.out.println("Erro: Variavel com nome de palavra reservada");
					System.exit(0);
				}
				if(ts.exists(s.getNome()) == false){
					ts.add(s);
					//System.out.println("add " + s.getNome());
				}else{
					System.err.println("Erro: Variavel ja' declarada");
					System.exit(0);
					}
				}
			(VIRGULA ID {
						s = new Simbolo(LT(0).getText(), dtype);
						//System.out.println("nome da variavel " + s.getNome());
						if(tr.exists(s.getNome())){
							System.out.println("Erro: Variavel com nome de palavra reservada");
							System.exit(0);
						}
						if(ts.exists(s.getNome()) == false){
							ts.add(s);
							//System.out.println("add " + s.getNome());
						}else{
							System.err.println("Erro: Variavel \"" + LT(0).getText() + "\" ja' declarada");
							System.exit(0);
							}
						}
			)*
			PONTO
		;


cmdleitura	:	"leia" PAR1 ID {
								if(!ts.exists(LT(0).getText())){ 
									System.err.println("Erro: Variavel \"" + LT(0).getText() + "\" nao declarada");
									System.exit(0);
								}
								}								
								
				PAR2 PONTO
			;
			
cmdescrita	:	"escreva" PAR1 escreve PAR2 PONTO
			;

escreve	:	ID 
			{
				s = new Simbolo(LT(0).getText(), 0);
				System.out.println(s.getNome());
			}
		| TEXTO
			{
				s = new Simbolo(LT(0).getText(), 0);
				System.out.println(s.getNome());
			}
		;

cmdif	:	"se"
			PAR1 expr OP_REL expr PAR2
			"entao"
			CHAVE1 (cmd)+ CHAVE2
			("senao"
			CHAVE1 (cmd)+ CHAVE2)?
		;

cmdexpr	:	ID IGUAL expr PONTO
		;

expr	:	termo expr_l
		;
		
fator	:	NUM
			| ID
			| PAR1 expr PAR2			
		;

termo_l	:	VEZES termo_l
			| (DIVIDIR termo_l)?
			
		;

termo	:	fator
			termo_l
		;

expr_l	:	(MAIS termo expr_l)
			| (MENOS termo expr_l)?
			
		;

cmddo	:	"faca" CHAVE1
			(CMD PONTO)+
			CHAVE2 "enquanto" PAR1 expr OP_REL expr PAR2
		;
		
		
cmdwhile	:	"enquanto" PAR1 expr OP_REL expr Par2
				CHAVE1
					(CMD PONTO)+
				CHAVE2
			;
		

class ProjetoLexer extends Lexer;

options {
    k=9; // needed for newline junk
    charVocabulary='\u0000'..'\u007F'; // allow ascii
}		

ID	:	('a'..'z') ('a'..'z'|'A'..'Z'|'0'..'9')*
	;

	
	
IGUAL	: ":="
		;
		
		
MAIS	:	'+'
		;
		
MENOS	:	'-'
		;
		
NUM	:	('0'..'9') | ('0'..'9')+ VIRGULA ('0'..'9')+
	;
	
	
PAR1	:	'('
		;

PAR2	:	')'
		;
		
CHAVE1	:	'{'
		;
		
CHAVE2	:	'}'
		;
VIRGULA :    ','		
		;
		
OP_REL	:	'<'
			| '>'
			| "<="
			| ">="
			| "!="
			| "=="
		;
		
TEXTO	:	'"'
			('0'..'9' | 'a'..'z' | 'A'..'Z' | "''")+
			'"'
		;
		
PONTO	:	'.'
		;
		
WS      : (' ' | '\t') {$setType(Token.SKIP);}
        ;
		
NL      : ('\n' | "\n\r" | "\r\n") {$setType(Token.SKIP); System.out.println("Linha:" + ++NumeroLinha.NLINHA);}
        ;

