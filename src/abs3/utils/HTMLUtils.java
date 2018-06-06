package abs3.utils;

import abs3.beans.Abs3;

public class HTMLUtils {

	public static String formatCategoryId(Abs3 abs3) {
		if(abs3.getCategoryId() == 1) {
			return "";
		}else if(abs3.getCategoryId() == 2) {
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
}
