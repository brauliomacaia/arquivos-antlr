class ProjetoParser extends Parser;

{
	TabelaDeSimbolos ts = new TabelaDeSimbolos();
	TabelaReservada tr = new TabelaReservada();
	Simbolo s;
	Simbolo id1 = null;
	Simbolo id2;
	int dtype;
}

prog	:	{NumeroLinha.NLINHA=0;}"programa" NOME
		    PONTO 
			(declara)*
			bloco
			"fimprog" 
			PONTO{}
		;

bloco	:	(cmd)+
		;

cmd	:	(cmdleitura
		| cmdescrita
		| cmdexpr
		| cmdif
		| cmddo
		| cmdwhile)
	;

declara	:	"declare" ("int" {dtype=1;}| "string" {dtype=2;})
			
			ID {				
				s = new Simbolo(LT(0).getText(), dtype);
				if(tr.exists(s.getNome())){
					System.err.println("Erro: Variavel com nome de palavra reservada");
					System.exit(0);
				}
				if(ts.exists(s.getNome()) == false){
					ts.add(s);
				}else{
					System.err.println("Erro: Variavel \"" + s.getNome() + "\" ja' declarada");
					System.exit(0);
				}
			}
			(VIRGULA ID {
						s = new Simbolo(LT(0).getText(), dtype);
						if(tr.exists(s.getNome())){
							System.err.println("Erro: Variavel com nome de palavra reservada");
							System.exit(0);
						}
						if(ts.exists(s.getNome()) == false){
							ts.add(s);
						}else{
							System.err.println("Erro: Variavel \"" + s.getNome() + "\" ja' declarada");
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
				s = new Simbolo(LT(0).getText(), 2);
				System.out.println(s.getNome());
			}
		| TEXTO
			{
				s = new Simbolo(LT(0).getText(), 2);
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
			{
				if(id1 == null){
					String nome = (LT(0).getText());
					id1 = ts.busca(nome);
				}else{
					String nome = LT(0).getText();
					id2 = ts.busca(nome);
					if(id1.getTipo() != id2.getTipo()){
						System.out.println("Variaveis de tipos diferentes");
						System.exit(0);
					}
				}
			}
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
			(cmd)+
			CHAVE2 "enquanto" PAR1 expr OP_REL expr PAR2 PONTO
		;
		
		
cmdwhile	:	"enquanto" PAR1 expr OP_REL expr PAR2
				CHAVE1
					(cmd)+
				CHAVE2
			;
		

class ProjetoLexer extends Lexer;

options {
    k=9; // needed for newline junk
    charVocabulary='\u0000'..'\u007F'; // allow ascii
}		

ID	:	('a'..'z') ('a'..'z'|'A'..'Z'|'0'..'9')*
	;

NOME	:	('A'..'Z')*
		;
	
IGUAL	: ":="
		;
		
		
MAIS	:	'+'
		;
		
MENOS	:	'-'
		;
		
NUM	:	('0'..'9')+
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

