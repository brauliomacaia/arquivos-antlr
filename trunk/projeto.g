class ProjetoParser extends Parser;

{
	TabelaDeSimbolos ts = new TabelaDeSimbolos();
	TabelaReservada tr = new TabelaReservada();
	GeradorCodigo gc = new GeradorCodigo();
	Simbolo s;
	Simbolo id1 = null;
	Simbolo id2;
	int dtype;
}

prog	:	{NumeroLinha.NLINHA=0;}"programa"
			{
				gc.add("programa");
			}
			NOME
			{
				gc.add(LT(0).getText() + " { \nScanner _xretf = new Scanner(System.in);\n");
			}
		    PONTO 
			(declara)*
			bloco
			"fimprog" 
			PONTO
			{
				gc.gerarCodigo();
			}
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

declara	:	"declare" ("int" {dtype=1; gc.add(LT(0).getText());}| "string" {dtype=2; gc.add(LT(0).getText());})
			
			ID {				
				s = new Simbolo(LT(0).getText(), dtype);
				gc.add(s.getNome());
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
			(VIRGULA {gc.add(LT(0).getText() + " ");} 
			ID {
				s = new Simbolo(LT(0).getText(), dtype);
				gc.add(s.getNome());
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
			PONTO {gc.add(LT(0).getText());}
		;


cmdleitura	:	"leia" 
				PAR1 ID {
							gc.add(LT(0).getText());
							if(!ts.exists(LT(0).getText())){ 
								System.err.println("Erro: Variavel \"" + LT(0).getText() + "\" nao declarada");
								System.exit(0);
							}
							s = ts.busca(LT(0).getText());
							if(s.getTipo() == 1){
								gc.add(" = _xretf.nextInt();\n");
							}else{
								gc.add(" = _xretf.next();\n");
							}
						}								
								
				PAR2 PONTO
			;
			
cmdescrita	:	"escreva" {gc.add(LT(0).getText());} PAR1 {gc.add(LT(0).getText());} escreve PAR2 {gc.add(LT(0).getText());} PONTO {gc.add(LT(0).getText());}
			;

escreve	:	ID 
			{				
				gc.add(LT(0).getText());				
			}
			| TEXTO
			{
				gc.add(LT(0).getText());				
			}
		;

cmdif	:	"se" {gc.add(LT(0).getText());}
			PAR1 {gc.add(LT(0).getText());} expr OP_REL {gc.add(LT(0).getText());} expr PAR2 {gc.add(LT(0).getText() + "\n");}
			"entao"
			CHAVE1 {gc.add(LT(0).getText()+ "\n");} (cmd)+ CHAVE2 {gc.add(LT(0).getText());}
			("senao" {gc.add(LT(0).getText());}
			CHAVE1 {gc.add(LT(0).getText()+"\n");} (cmd)+ CHAVE2 {gc.add(LT(0).getText());})?
		;

cmdexpr	:	ID {gc.add(LT(0).getText());} IGUAL {gc.add(LT(0).getText());} expr PONTO {gc.add(LT(0).getText());}
		;

expr	:	termo expr_l
		;
		
fator	:	NUM {gc.add(LT(0).getText());}
			| ID
			{
				
				if(id1 == null){
					gc.add(LT(0).getText());
					String nome = (LT(0).getText());
					id1 = ts.busca(nome);
				}else{
					gc.add(LT(0).getText());
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

termo_l	:	VEZES {gc.add(LT(0).getText());} termo_l
			| (DIVIDIR {gc.add(LT(0).getText());} termo_l)?
			
		;

termo	:	fator
			termo_l
		;

expr_l	:	(MAIS {gc.add(LT(0).getText());} termo expr_l)
			| (MENOS {gc.add(LT(0).getText());} termo expr_l)?
			
		;

cmddo	:	"faca" {gc.add(LT(0).getText());} CHAVE1 {gc.add(LT(0).getText()+"\n");}
			(cmd)+
			CHAVE2 {gc.add(LT(0).getText());} "enquanto" {gc.add(LT(0).getText());} PAR1 {gc.add(LT(0).getText());} expr OP_REL {gc.add(LT(0).getText());} expr {gc.add(LT(0).getText());} PAR2 {gc.add(LT(0).getText());} PONTO
		;
		
		
cmdwhile	:	"enquanto" {gc.add(LT(0).getText());} PAR1 {gc.add(LT(0).getText());} expr OP_REL {gc.add(LT(0).getText());} expr PAR2 {gc.add(LT(0).getText() + "\n");}
				CHAVE1 {gc.add(LT(0).getText());}
					(cmd)+
				CHAVE2 {gc.add(LT(0).getText());}
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

