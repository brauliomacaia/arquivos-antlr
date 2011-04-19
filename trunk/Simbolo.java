public class Simbolo
{
   public static final int INTEGER=1;
   public static final int STRING=2;
   
   private String nome;
   private int    tipo;
   int valorInt = 0;
   String valorStr = null;
   
   public Simbolo()
   {
   }
   
   public Simbolo(String nome, int tipo)
   {
      this.nome = nome;
	  this.tipo = tipo;
   }
   
   public void setNome(String nome)
   {
      this.nome = nome;
   }
   
   public void setTipo(int tipo)
   {
      this.tipo = tipo;
   }
   public void setValor(int valor){
		this.valorInt = valor;
   }
   public void setValor(String valor){
		this.valorStr = valor;
   }
   public int getValorInt(){
		return valorInt;
   }
   public String getValorStr(){
		return valorStr;
   }
   
   public String getNome() {return nome;}
   public int    getTipo() {return tipo;}
   
   
}