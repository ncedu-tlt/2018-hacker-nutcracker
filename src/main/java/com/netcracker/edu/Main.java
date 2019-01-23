package com.netcracker.edu;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		View view = new View();
		view.menu();
	}
}