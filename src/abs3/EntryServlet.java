package abs3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abs3.utils.DBUtils;


@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp")
		.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		String date = req.getParameter("date");
		String classification = req.getParameter("classification");
		String categoryId = req.getParameter("categoryId");
		String note = req.getParameter("note");
		String price = req.getParameter("price");

		List<String> errors =  validate(date, categoryId, price);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp")
				.forward(req, resp);
			return;
		}

		List<String> successes = new ArrayList<>();
		successes.add("「 " + date  + " " + changeToCategory(categoryId) + " " + price + "」を登録しました。");
		session.setAttribute("successes", successes);

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = DBUtils.getConnection();

			sql = "INSERT INTO account_books(date, classification, note, price,category_id)values (?,?,?,?,?)";
			//INSERT命令の準備
			ps = con.prepareStatement(sql);
			//INSERT命令にポストデータの内容をセット
			ps.setString(1, date);
			ps.setString(2, classification);
			//ps.setString(4, req.getParameter("note"));
			//			ps.setString(3, note.equals("選択して下さい") ? null : note);
			ps.setString(3, note);
			ps.setString(4, price);
			ps.setString(5, categoryId);

			ps.executeUpdate();

			resp.sendRedirect("index.html");
		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}

	}

	private List<String> validate(String date, String categoryId, String price) {
		List<String> errors = new ArrayList<>();

		//日付の必須入力
		if(date.equals("")){
			errors.add("日付は必須入力です。");
		}

		//カテゴリーの必須入力（選択してくださいだとエラー）
		if(categoryId.equals("") || categoryId.equals("1")) {
			errors.add("カテゴリーは必須入力です。");
		}

		//日付の形式（yyyy/MM//dd）
		if(!date.equals("")) {
			try {
				LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu/MM/dd")
					.withResolverStyle(ResolverStyle.STRICT));
			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		//金額の必須入力
		if(price.equals("")){
			errors.add("金額は必須入力です。");
		}

		//金額数字のみにする
		if(!price.equals("")) {
		    try {
		        Integer.parseInt(price);
		    } catch (NumberFormatException e) {
		        errors.add("金額は数字で入力してください。");
		    }
		}
		return errors;

	}

	private String changeToCategory(String categoryId) {
		if(categoryId.equals("2")) {
			return "食費";
		}else if(categoryId.equals("3")) {
			return "交際費";
		}else if(categoryId.equals("4")) {
			return "日用品";
		}else if(categoryId.equals("5")) {
			return "アルバイト代";
		}else if(categoryId.equals("6")) {
			return "その他";
		}
		return "";
	}

}
