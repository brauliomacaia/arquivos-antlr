
class ExprParser extends Parser;
{
   
   TabelaDeSimbolos ts;
   int posIni, posFinal;
   int tipo;
   
   
}

decl   : {ts = new TabelaDeSimbolos();}
         (declara)+
		 {ts.printAll();}
		 END
       ;

declara : 
           tipo var_lista FIM 
        ;
		
tipo    : "int"   { tipo = Simbolo.INTEGER; }
        | "float" { tipo = Simbolo.REAL;  }
		;

var_lista : ID { if (ts.exists(LT(0).getText()))
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
           (
		     VIRGULA 
			 
			 ID { if (ts.exists(LT(0).getText()))
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
		   
		   
		   )*
          ;

class ExprLexer extends Lexer;

options {
    k=2; // needed for newline junk
    charVocabulary='\u0000'..'\u007F'; // allow ascii
}


ID  : ('a'..'z')+ 
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
    
FIM   : ';' ;

END   : '.' ;
