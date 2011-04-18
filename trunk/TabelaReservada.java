public class TabelaReservada
{
	String palavrasReservadas[] = {"int", "String"};
	
	public boolean exists(String str){
		for(String s : palavrasReservadas){
			if(s.equals(str))
				return true;
		}
		return false;
	} 
	
}