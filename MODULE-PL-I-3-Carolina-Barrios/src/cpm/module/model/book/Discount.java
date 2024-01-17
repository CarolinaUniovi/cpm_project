package cpm.module.model.book;

public class Discount {

	private DiscountType discount;
	private String id;

	public Discount(DiscountType discount, String id) {
		super();
		this.discount = discount;
		this.id = id;
	}

	public DiscountType getDiscount() {
		return discount;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		String string = id + ";" + discount;
		return string;
	}

}
