public class Numero
{

   private int valor;
   private int base;
   
   public Numero(int valor, int base)
   {
      this.valor = valor;
	  this.base = base;
   }
   public Numero(){}
   
   public void setValor(int valor)
   {
      this.valor =valor;
   }
   public void setBase(int base)
   {
      this.base = base;
   }
   
   public int getValor(){return valor;}
   public int getBase(){return base;}
}
