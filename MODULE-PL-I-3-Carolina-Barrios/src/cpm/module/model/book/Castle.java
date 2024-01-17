package cpm.module.model.book;

import java.util.List;

public class Castle {

	private String code;
	private String name;
	private String description;
	private String country;
	private int price;
	private List<Enchantment> enchantments;

	public Castle(String code, String name, String description, String country, int price,
			List<Enchantment> enchantments) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.country = country;
		this.price = price;
		this.enchantments = enchantments;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCountry() {
		return country;
	}

	public int getPrice() {
		return price;
	}

	public List<Enchantment> getEnchantments() {
		return enchantments;
	}

}
