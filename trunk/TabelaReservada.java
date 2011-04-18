public class TabelaReservada
{
	String palavrasReservadas[] = {"declara", "fimprog", "se", "entao", "senao", "faca", "enquanto"};
	
	public boolean exists(String str){
		for(String s : palavrasReservadas){
			if(s.equals(str))
				return true;
		}
		return false;
	} 
	
}