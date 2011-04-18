import java.util.Vector;

public class TabelaDeSimbolos
{
    private Vector v;
	
	public TabelaDeSimbolos()
	{
	   v = new Vector();
	}
	
	public void add(Simbolo s)
	{
	    v.add(s);
	}
	
	public boolean exists(String str)
	{
	    for (int i=0;i<v.size();i++)
		{
		    Simbolo s = (Simbolo)v.get(i);
			if (str.equals(s.getNome()))
			   return true;
		}
		return false;
	}
	
	public void printAll()
	{
	   for (int i=0;i<v.size();i++)
	   {
	       Simbolo s = (Simbolo)v.get(i);
	       System.out.print("ID = "+s.getNome() + " Tipo: ");
		   if (s.getTipo() == Simbolo.INTEGER) System.out.println("INTEGER");
		   else System.out.println("STRING");
	   }
	}
   
}