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
           converte(al.get(i));
       }
        try {
            imprime();
        } catch (IOException ex) {
            Logger.getLogger(GeradorCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void converte(String token){
        if(token.equals("programa")){
            CodigoJava.add("public class CodigoJava { \n");
        }
    }

    public void imprime() throws IOException{
        fwriter = new FileWriter(new File("CodigoJava.java"),true);
        buffer = new BufferedWriter(fwriter);  
        
        buffer.write(CodigoJava.get(0));
		
		buffer.close();  

    }
}
