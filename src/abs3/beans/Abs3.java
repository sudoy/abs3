package abs3.beans;

import java.sql.Date;

public class Abs3 {
	private int id;
	private Date date;
	private String classification;
	private String category;

	public Abs3(int id, Date date, String classification, String category, String note, int price) {
		super();
		this.id = id;
		this.date = date;
		this.classification = classification;
		this.category = category;
		this.note = note;
		this.price = price;
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
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	private String note;
	private int price;
}
