class Projeto extends Parser;

cmdif	:	"se"
			PAR1 expr OP_REL expr PAR2
			"então"
			CHAVE1 (cmd)+ CHAVE2
			("senao"
			CHAVE1 (cmd)+ CHAVE2)?
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

expr_l	:	MAIS expr_l
			| (MENOS expr_l)?
			
		;

expr	:	termo
			expr_l
		;

cmdexpr	:	ID
			IGUAL
			expr
		;
		
cmd	:	cmdexpr
		| cmdif
	;
	
bloco	:	(cmd)+
		;
		

prog	:	"programa"
			declara
			bloco
			"fimprog"
		;

declara	:	"declare"
			ID
			(ID)*
		;

class ProjetoLexer extends Lexer;

options {
    k=9; // needed for newline junk
    charVocabulary='\u0000'..'\u007F'; // allow ascii
}		

ID	:	('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')*
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