class ExprParser extends Parser;
{
   Numero n;
   int base, basetmp;
}

expr:   atom {base = basetmp;}
             ((
			   PLUS atom  
               |
               MINUS atom 
              )
			  {
			                  if (base != basetmp)
							  {
                                   System.err.println("Operacao com bases incompativeis");
								   System.exit(0);
							  }
			           
			  }
			  )* 
        FIM
        
    ;      


atom:   NUM
        {
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
		}
        SUFIXO { if (LT(0).getText().equals("b") && n.getValor() > 1) 
		            {
                        System.err.println("Erro semantico");
						System.exit(0);
                    }
				 if (LT(0).getText().equals("b"))  
				     basetmp = 2;
			     else
				     basetmp = 10;
               }
    ;

	

class ExprLexer extends Lexer;

options {
    k=2; // needed for newline junk
    charVocabulary='\u0000'..'\u007F'; // allow ascii
}


PLUS  : '+' ;

MINUS : '-' ;

NUM   : ('0'..'9')
	  ;
SUFIXO : 'b' | 'd' 
       ;


WS    : ( ' '
        | '\r' '\n'
        | '\n'
        | '\t'
        )
        {$setType(Token.SKIP);}
      ;
    
FIM   : '.' ;
