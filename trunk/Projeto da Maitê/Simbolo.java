public class Simbolo
{
   public static final int INTEGER=1;
   public static final int STRING=2;
   
   private String nome;
   private int    tipo;
   private int    val;
   private String text;
   
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

   public void setText(String text)
   {
       this.text = text;
   }

   public void setVal(int val)
   {
   this.val = val;
   }
   
   public String getNome() {return nome;}
   public int    getTipo() {return tipo;}

    public String getText() {
        return text;
    }

    public int getVal() {
        return val;
    }

   
   
}