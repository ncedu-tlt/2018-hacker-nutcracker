package program;

import program.view.View;



public class Main {
	public static void main(String[] args) {
		System.out.println("Добро пожаловать!");
		View view = View.getInstance();
		view.hello();
	}
}


