package cpm.module.model;

import cpm.module.model.book.User;

public class LoggedUser {

	private static User loggedUser = null;

	public static User getInstance() {
		return loggedUser;
	}

	public static User getInstance(String id, String name, String surname, String email) {
		if (loggedUser == null) {
			loggedUser = new User(id, name, surname, email);
		}
		return loggedUser;
	}

	public static User getInstance(String id) {
		if (loggedUser == null) {
			loggedUser = new User(id);
		}
		return loggedUser;
	}

	public static void restartInstance() {
		loggedUser = null;
	}

}
