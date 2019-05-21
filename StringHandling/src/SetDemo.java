import java.util.HashSet;
import java.util.Set;

public class SetDemo {

	public static void main(String[] args) {
		
		Set<String> a=new HashSet<String>();
		a.add("Shivaji");
		a.add("Bipin");
		a.add(null);
		System.out.println(a.add(null));;
		System.out.println(a);
	}
}
