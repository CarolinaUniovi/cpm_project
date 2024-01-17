package cpm.module.model.book;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Enchantment {

	AP("Ap"), DE("De"), EN("En"), OB("Ob"), OI("Ol"), RU("Ru");

	private static final Map<String, Enchantment> lookup = new HashMap<String, Enchantment>();

	Enchantment(String code) {
		this.code = code;
		switch (code) {
		case "Ap":
			fullName = "Ghost aparitions";
			break;
		case "De":
			fullName = "Temperature drop";
			break;
		case "En":
			fullName = "Lights blinking";
			break;
		case "Ob":
			fullName = "Moving objects";
			break;
		case "Ol":
			fullName = "Nauseous smells";
			break;
		case "Ru":
			fullName = "Strange noises";
			break;
		}
	}

	private String code;
	private String fullName;

	public String getCode() {
		return code;
	}

	public String getFullName() {
		return fullName;
	}

	public static Enchantment get(String code) {
		return lookup.get(code);
	}

	static {
		for (Enchantment w : EnumSet.allOf(Enchantment.class))
			lookup.put(w.getCode(), w);
	}

	@Override
	public String toString() {
		return fullName;
	}

}
