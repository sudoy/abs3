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
@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {
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

			//GETパラメーターを取得
			sql = "SELECT id, date, classification, category_id,  note, price FROM account_books where id = ?";
			ps = con.prepareStatement(sql);
			//SELECT文にパラメーターの内容をセット
			ps.setString(1, req.getParameter("id"));

			//SELCT命令を実行
			rs = ps.executeQuery();

			//ResultSet→JavaBeansに変換する
			rs.next();
			int id = rs.getInt("id");
			Date date = rs.getDate("date");
			int classification = rs.getInt("classification");
			String note = rs.getString("note");
			int price = rs.getInt("price");
			int categoryId =rs.getInt("category_id");
			Abs3 list = new Abs3(id, date,classification,note,price,categoryId);
			req.setAttribute("list", list);

			//JSPへフォワード
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp")
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
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String date = req.getParameter("date");
		String classification = req.getParameter("classification");
		String categoryId = req.getParameter("categoryId");
		String note = req.getParameter("note");
		String price = req.getParameter("price");
		String id = req.getParameter("id");

		List<String> errors =  validate(date, categoryId, price);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp")
				.forward(req, resp);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "UPDATE account_books SET date = ?, classification = ?, category_id = ?, note= ?, price=? WHERE id = ?";
			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, date);
			ps.setString(2, classification);
			ps.setString(3, categoryId);
			ps.setString(4, note);
			ps.setString(5, price);
			ps.setString(6, id);

			//UPDATE命令を実行
			ps.executeUpdate();

			List<String> successes = new ArrayList<String>();
			successes.add("修正できました。");
			session.setAttribute("successes", successes);

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

		//日付の形式（yyyy/MM//dd）
		if(!date.equals("")) {
			try {
				LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu/MM/dd")
					.withResolverStyle(ResolverStyle.STRICT));

			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		//カテゴリーの必須入力（選択してくださいだとエラー）
		if(categoryId.equals("") || categoryId.equals("1")) {
			errors.add("カテゴリーは必須入力です。");
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
}
