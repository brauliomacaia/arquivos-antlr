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

        public int getSimbolo(String str)
        {
             for (int i=0;i<v.size();i++)
				{
				Simbolo s = (Simbolo)v.get(i);
					if (str.equals(s.getNome()))
					return i;
					
				}
             
			 return -1;
        }

        public void setText(int i, String text)
        {
        Simbolo s = (Simbolo)v.get(i);
        s.setText(text);
        v.set(i, s);
        }

        public String getText(int i)
        {
        Simbolo s = (Simbolo)v.get(i);
        return s.getText();
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