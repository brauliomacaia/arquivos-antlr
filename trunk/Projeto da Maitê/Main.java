import antlr.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	
        ExprLexer lexer = new ExprLexer(new BufferedReader(new FileReader("prog.txt")));
        
		//ExprLexer lexer = new ExprLexer(System.in);
		
        ExprParser parser = new ExprParser(lexer);
        
        parser.prog();
    }
}

