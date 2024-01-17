package cpm.module.fileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import cpm.module.model.book.Discount;
import cpm.module.model.book.DiscountType;

public class DiscountFileUtil {

	private final static String FILENAME = "files/disccounts.dat";

	public static List<Discount> loadFile() {
		String line;
		String[] productData = null;
		List<Discount> discountList = new ArrayList<>();

		try {
			BufferedReader file = new BufferedReader(new FileReader(FILENAME));
			while (file.ready()) {
				line = file.readLine();
				productData = line.split(";");
				if (productData.length == 2) {
					discountList.add(new Discount(DiscountType.get(productData[1]), productData[0]));
				}

			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
		return discountList;
	}

	public static Discount loadDiscountFor(String id) {
		List<Discount> discounts = loadFile();
		for (Discount d : discounts) {
			if (d.getId().equals(id)) {
				return d;
			}
		}
		return null;
	}

	public static void saveToFile(List<Discount> discountList) {
		try {
			PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME)));
			for (Discount d : discountList) {
				file.println(d.toString());
			}
			file.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

}
