package abs3.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

import abs3.beans.Abs3;

public class HTMLUtils {

	public static String formatDate(Date d) {
		if(d == null) {
			return "";
		}
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 return sdf.format(d);

	}

	public static String formatCategoryId(Abs3 abs3) {
		if(abs3.getCategoryId() == 2) {
			return "食費";
		}else if(abs3.getCategoryId() == 3) {
			return "交際費";
		}else if(abs3.getCategoryId() == 4) {
			return "日用品";
		}else if(abs3.getCategoryId() == 5) {
			return "アルバイト代";
		}else if(abs3.getCategoryId() == 6) {
			return "その他";
		}
		return "";
	}

	public static String formatClassification(Abs3 abs3) {
		if(abs3.getClassification() == 1) {
			return "支出";
		}else if(abs3.getClassification() == 2){
			return "収入";
		}
		return "";
	}

	public static String checkClassification(int param, int value) {
		if(param == value) {
			return "checked";
		}else {
			return "";
		}
	}

	public static String selectCategory(int param, int value) {
		if(param == value) {
			return "selected";
		}else {
			return "";
		}
	}

}
