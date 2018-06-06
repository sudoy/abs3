package abs3.beans;

import java.sql.Date;

public class Abs3 {
	private int id;
	private Date date;
	private int classification;
	private String note;
	private int price;
	private int category_id;

	public Abs3(int id, Date date, int classification, String note, int price, int category_id) {
		super();
		this.id = id;
		this.date = date;
		this.classification = classification;
		this.note = note;
		this.price = price;
		this.category_id = category_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
