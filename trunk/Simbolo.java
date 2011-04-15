public class Simbolo
{
   public static final int INTEGER=1;
   public static final int STRING=2;
   
   private String nome;
   private int    tipo;
   
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
   
   public String getNome() {return nome;}
   public int    getTipo() {return tipo;}
   
   
}