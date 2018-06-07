package abs3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import abs3.beans.Abs3;
import abs3.utils.DBUtils;
@WebServlet("/copy.html")
public class CopyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "SELECT id, date, classification, category_id,  note, price FROM account_books where id = ?";

			ps= con.prepareStatement(sql);

			//SELECT文にパラメーターの内容をセット
			ps.setString(1, req.getParameter("id"));

			//SELCT命令を実行
			rs=ps.executeQuery();

			//ResultSet→JavaBeansに変換する
			rs.next();
			int id = rs.getInt("id");
			Date date =rs.getDate("date");
			int classification =rs.getInt("classification");
			String note =rs.getString("note");
			int price=rs.getInt("price");
			int categoryId=rs.getInt("category_id");
			Abs3 list = new Abs3(id, date,classification,note,price,categoryId);
			req.setAttribute("list", list);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/copy.jsp")
			.forward(req, resp);

		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
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

		if(date.equals("")){
			errors.add("日付は必須入力です。");
		}

		if(categoryId.equals("")) {
			errors.add("カテゴリーは必須入力です。");
		}

		if(!date.equals("")) {
			try {
				LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu/MM/dd")
						.withResolverStyle(ResolverStyle.STRICT));

			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		if(price.equals("")){
			errors.add("金額は必須入力です。");

		}

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
