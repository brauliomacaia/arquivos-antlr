import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeradorCodigo{

    ArrayList<String> al;
    ArrayList<String> CodigoJava;
    FileWriter fwriter;
    BufferedWriter buffer;

    public GeradorCodigo(){
        al = new ArrayList<String>();
        CodigoJava = new ArrayList<String>();
    }

    public void add(String s){
        al.add(s);
	}

    public void gerarCodigo(){
       for(int i = 0; i < al.size(); i++){
           converte(al.get(i), i);
       }
        try {
            imprime();
        } catch (IOException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void converte(String token, int index){
        if(token.equals("programa")){
            CodigoJava.add(index, "public class ");		
        }else if(token.equals("string")){
			CodigoJava.add(index, "String ");
		}else if(token.equals(".")){
			CodigoJava.add(index, ";\n");
		}else if(token.equals("int")){
			CodigoJava.add(index, "int ");
		}else if(token.equals("se")){
			CodigoJava.add(index, "if ");
		}else if(token.equals("escreva")){
			CodigoJava.add(index, "System.out.println");
		}else if(token.equals("senao")){
			CodigoJava.add(index, "else\n");
		}else if(token.equals(":=")){
			CodigoJava.add(index, " = ");
		}else if(token.equals("faca")){
			CodigoJava.add(index, "do");
		}else if(token.equals("enquanto")){
			CodigoJava.add(index, "\nwhile");
		}		
		else{
			CodigoJava.add(index, token);
		}
    }

    public void imprime() throws IOException{
        fwriter = new FileWriter(new File("CodigoJava.txt"),true);
        buffer = new BufferedWriter(fwriter);  
        
        for(int i = 0; i < CodigoJava.size(); i++){
			buffer.write(CodigoJava.get(i));
		}
			
		
		buffer.close();  

    }
}
