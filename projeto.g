class ProjetoParser extends Parser;

{
	TabelaDeSimbolos ts = new TabelaDeSimbolos();
	Simbolo s;
	int dtype;
}

prog	:	{NumeroLinha.NLINHA=0;}"programa" 
		    PONTO 
			declara 
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
				if(ts.exists(s.getNome()) == false){
					ts.add(s);
					System.out.println("add");
				}else{
					System.err.println("Variavel já declarada");
					System.exit(0);
					}
				}
			(VIRGULA ID)*
			PONTO
		;


cmdleitura	:	"leia" PAR1 ID PAR2 PONTO
			;
			
cmdescrita	:	"escreva" PAR1 escreve PAR2 PONTO
			;

escreve	:	ID | TEXTO
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
		
NUM	:	('0'..'9')
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

