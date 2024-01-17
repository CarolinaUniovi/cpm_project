package cpm.module.model.book;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DiscountType {

	EXTRA10("EXTRA10"), EXTRA25("EXTRA25"), NONE(" ");

	private String code;
	private String percentage;
	private static final Map<String, DiscountType> lookup = new HashMap<String, DiscountType>();

	DiscountType(String string) {
		this.code = string;
		percentage = "";
		switch (code) {
		case "EXTRA10":
			percentage = "10%";
			break;
		case "EXTRA25":
			percentage = "25%";
			break;
		}
	}

	public String getCode() {
		return code;
	}

	public static DiscountType get(String code) {
		return lookup.get(code);
	}

	public String getPercentage() {
		return percentage;
	}

	static {
		for (DiscountType w : EnumSet.allOf(DiscountType.class))
			lookup.put(w.getCode(), w);
	}

}
