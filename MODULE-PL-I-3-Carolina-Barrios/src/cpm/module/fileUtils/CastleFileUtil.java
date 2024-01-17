package cpm.module.fileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cpm.module.model.book.Castle;
import cpm.module.model.book.Enchantment;

public class CastleFileUtil {

	private final static String FILENAME = "files/castles.dat";

	public static List<Castle> loadFile() {
		String line;
		String[] productData = null;
		String[] enchantments = null;
		List<Castle> castleList = new ArrayList<>();

		try {
			BufferedReader file = new BufferedReader(new FileReader(FILENAME));
			while (file.ready()) {
				line = file.readLine();
				productData = line.split(";");
				if (productData.length == 6) {
					enchantments = productData[5].split("-");
					List<Enchantment> enchantmentsList = new ArrayList<>();
					for (String e : enchantments) {
						enchantmentsList.add(Enchantment.get(e));
					}
					castleList.add(new Castle(productData[0], productData[1], productData[2], productData[3],
							Integer.parseInt(productData[4]), enchantmentsList));
				}

			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
		return castleList;
	}

	public static Castle loadCastle(String castleCode) {
		String line;
		String[] productData = null;
		String[] enchantments = null;
		Castle castle = null;
		boolean found = false;

		try {
			BufferedReader file = new BufferedReader(new FileReader(FILENAME));
			while (file.ready() && !found) {
				line = file.readLine();
				productData = line.split(";");
				if (productData.length == 6) {
					if (productData[0].equals(castleCode)) {
						enchantments = productData[5].split("-");
						List<Enchantment> enchantmentsList = new ArrayList<>();
						for (String e : enchantments) {
							enchantmentsList.add(Enchantment.get(e));
						}
						castle = new Castle(productData[0], productData[1], productData[2], productData[3],
								Integer.parseInt(productData[4]), enchantmentsList);
						found = true;
					}

				}

			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
		return castle;
	}

}
