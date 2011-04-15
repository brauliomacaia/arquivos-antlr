class ExprParser extends Parser;
{
   TabelaDeSimbolos ts = new TabelaDeSimbolos();
   int posIni, posFinal;
   int tipo;
   String var;
   int valor;
}

prog	:   "Programa" FIM { System.out.println("Início da Compilação");} decl bloco "fimprog" FIM { System.out.println("Compilou programa");} 
		;

bloco	: 	(cmd)+
		;

cmd	:	(cmdleitura| cmdescrita | cmdexpr | cmdif)
	;

cmdleitura
	:	"leia" LPAR ID RPAR {System.out.println("Comando leia!");}  FIM
	;

cmdescrita
	:	"escreva" LPAR esc { Ut.esc("Li parametro escreva");}RPAR FIM {Ut.esc("Li comando escrita");}
	;

esc : ID | TEXTO
;

cmdif
	:	"se" LPAR {Ut.esc("Li se (");} 
	            expr {Ut.esc("Li "+LT(0).getText());} OP_REL {Ut.esc("Li "+LT(0).getText());} expr {Ut.esc("Li "+LT(0).getText());} 
			 RPAR "entao" 
			 LBRA {Ut.esc("Li ) entao {");}
			    (cmd)+ 
			 RBRA 
			 ("senao" 
			      LBRA 
				      (cmd)+ 
				  RBRA)?
	;

cmdexpr	:	ID {var = LT(0).getText(); Ut.esc(var);} ATR expr FIM {Ut.esc("Li expr inteiro");} 
	;

expr	: 	termo expr1 {Ut.esc("analisei expr1");}
	    ;

expr1	:	PLUS termo expr1 {Ut.esc("analisei uma soma");} | MINUS termo expr1 {Ut.esc("analisei uma subtração");} |
	    ;

termo 	:	fator termo1
	    ;

termo1	:	MULT fator termo1 {Ut.esc("analisei uma multiplicação");}| DIV fator termo1 {Ut.esc("analisei uma divisão");}|
	;

fator 	: 	NUMERO {int i = ts.getSimbolo(var);
				  if (i >= 0)
				  {  
				      String aux = LT(0).getText();
					  ts.setVal(i, Integer.parseInt(aux));
					  System.out.println(var + " = " + ts.getVal(i));
					  }
				  }
				  else
					  System.err.println("Variável " + var + " não declarada!" ); 
				  }
	        | ID {int i = ts.getSimbolo(var);
				  if (i >= 0)
				  {  
				      String aux = LT(0).getText();
					  if (ts.exists(aux))
					  {
					  ts.setVal(i, ts.getSimbolo(aux).getVal());
					  System.out.println(var + " = " + ts.getVal(i));
					  }
				  }
				  else
					  System.err.println("Variável " + var + " não declarada!" ); 
				  } 
		    | LPAR expr RPAR 
			| TEXTO {int i = ts.getSimbolo(var);
				  if (i >= 0)
				  {  
					  ts.setText(i, LT(0).getText());
					  System.out.println(var + " = " + ts.getText(i));
				  }
				  else
					  System.err.println("Variável " + var + " não declarada!" ); 
				  } 
		;


decl   :     (bloco_decl)+
                 {ts.printAll();}
       ;

bloco_decl   :  (declara)+ FIM
             ;

declara :
           tipo var_lista
        ;

tipo    : INT   { tipo = Simbolo.INTEGER; }
        | STRING { tipo = Simbolo.STRING;  }
        ;


var_lista : ID { if (ts.exists(LT(0).getText()))
                 {
				    System.err.println("ID ja declarado "+LT(0).getText());
					//System.exit(0);
				 }
				 else
				 {
				     Simbolo s = new Simbolo(LT(0).getText(), tipo);
					 ts.add(s);
				 }
			   }
           (
		     VIRGULA

			 ID { if (ts.exists(LT(0).getText()))
                 {
				    System.err.println("ID Já Declarado "+LT(0).getText());

				 }
				 else
				 {
				     Simbolo s = new Simbolo(LT(0).getText(), tipo);
					 ts.add(s);
				 }
			   }


		   )*
          ;


class ExprLexer extends Lexer;

options {
    k=9; // needed for newline junk
    charVocabulary='\u0000'..'\u007F'; // allow ascii
}

ID : SMALL (SMALL | LARGE | NUM)*;

NUMERO : (NUM)+
	   ;

VIRGULA : ','
	    ;

WS    : ( ' '
        | '\r' '\n'
        | '\n'
        | '\t'
        )
        {$setType(Token.SKIP);}
      ;

FIM     :       '.'
        ;

PLUS	:	'+'
	;

MINUS 	:	'-'
	;

MULT	:	'*'
	;

DIV	:       '/'
	;

TEXTO   : '"' ( SMALL | LARGE | NUM )* '"'
        ;

LPAR : '('
         ;

RPAR : ')'
          ;

LBRA : '{'
     ;

RBRA : '}'
     ;
	 
ATR : ":="
	;

OP_REL: '<' | "<=" | '>' | ">=" | "!=" | "=="
	;

protected
SMALL : ('a'..'z');
protected
LARGE : ('A'..'Z');
protected
NUM : ('0'..'9')
	;

PROGRAMA : "Programa"
		 ;

INT : "Int"
	;
	
STRING: "String"
	  ;
	