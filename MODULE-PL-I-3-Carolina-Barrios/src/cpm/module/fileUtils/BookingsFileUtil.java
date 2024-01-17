package cpm.module.fileUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cpm.module.model.book.Booking;

public class BookingsFileUtil {

	private final static String FILENAME = "files/bookings.dat";

	public static void saveToFile(List<Booking> orderList) {
		for (Booking d : orderList) {
			saveToFile(d);
		}
	}

	public static void saveToFile(Booking booking) {
		try {
			PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME, true)));
			file.append(booking.toString());
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

}
