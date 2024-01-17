package cpm.module.model.book;

import java.util.Date;

public class Booking {

	private User user;
	private String castleCode;
	private Date date;
	private int days;
	private int rooms;
	private int people;
	private double finalPrice;
	private String comments;
	private String castleName;
	private boolean discountApplied = false;

	public Booking(User user, String castleCode, String castleName, Date date, int days, int rooms, int people,
			double finalPrice) {
		super();
		this.user = user;
		this.castleCode = castleCode;
		this.castleName = castleName;
		this.date = date;
		this.days = days;
		this.rooms = rooms;
		this.people = people;
		this.finalPrice = finalPrice;
	}

	public Booking(String castleCode, String castleName, Date date, int days, int rooms, int people,
			double finalPrice) {
		super();
		this.castleCode = castleCode;
		this.castleName = castleName;
		this.date = date;
		this.days = days;
		this.rooms = rooms;
		this.people = people;
		this.finalPrice = finalPrice;
	}

	public String getCastleName() {
		return castleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCastleCode() {
		return castleCode;
	}

	public Date getDate() {
		return date;
	}

	public int getDays() {
		return days;
	}

	public int getRooms() {
		return rooms;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public int getPeople() {
		return people;
	}

	public void applyDiscount(Discount discount) {
		if (user != null && !discountApplied) {
			if (DiscountType.EXTRA10.equals(discount.getDiscount())) {
				finalPrice = finalPrice * 0.9;
			} else if (DiscountType.EXTRA25.equals(discount.getDiscount())) {
				finalPrice = finalPrice * 0.75;
			}
			discountApplied = true;
		}
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isDiscountApplied() {
		return discountApplied;
	}

	@Override
	public String toString() {
		return user.getId() + ";" + user.getName() + ";" + user.getSurname() + ";" + user.getEmail() + ";" + castleCode
				+ ";" + date.toString() + ";" + days + ";" + rooms + ";" + finalPrice + ";" + comments + '\n';
	}

}
