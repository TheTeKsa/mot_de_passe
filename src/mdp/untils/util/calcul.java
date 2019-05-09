package mdp.untils.util;

public class calcul {

	public static void main(String[] args) {
		int res = 0;
		for (int i = 0; i<15; i++) {
			res = res + (2*i +1 )/2 + i + 1;
		}
		res -= 15;
		System.out.println(res);

	}

}
