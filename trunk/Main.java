import antlr.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	
        ProjetoLexer lexer = new ProjetoLexer(new BufferedReader(new FileReader("arquivo.txt")));
        
		ProjetoParser parser = new ProjetoParser(lexer);
        
        parser.prog();
    }
}

