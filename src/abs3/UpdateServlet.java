package abs3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

		String id = req.getParameter("id");

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;


		try {
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT id, date, classification, category, note, price "
					+ "FROM account_books WHERE id = ?";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//?にidをセット
			ps.setString(1, id);

			//SELECT命令を実行
			rs = ps.executeQuery();

			//ResultSetをJavaBeansに変換
			rs.next();

			Abs3 abs3 = new Abs3(rs.getInt("id"),
					rs.getDate("date"),
					rs.getString("classification"),
					rs.getString("category"),
					rs.getString("note"),
					rs.getInt("price"));

			//JavaBeansをJSPへ渡す
			req.setAttribute("abs3", abs3);

			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp")
				.forward(req, resp);
		} catch (Exception e) {
			throw new ServletException(e);
		}finally {
			//終了処理
			try {
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		String id = req.getParameter("id");
		String date = req.getParameter("date");
		String classification = req.getParameter("classification");
		String category = req.getParameter("category");
		String note = req.getParameter("note");
		String price = req.getParameter("price");


		List<String> errors = validate(id, date, price);

		//Listにエラーをaddした回数が一回以上の場合の処理
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
			con = DBUtils.getConnection();

			//UPDATE文
			sql = "UPDATE account_books SET date = ?, classification = ?, category = ?, note = ?, price = ? WHERE id = ?";

			//UPDATE命令の準備
			ps = con.prepareStatement(sql);

			//UPDATE命令にポストデータの内容をセット
			ps.setString(1, date);
			ps.setString(2, classification.equals("")? null : classification);
			ps.setString(3, category);
			ps.setString(4, note);
			ps.setString(5, price);
			ps.setString(6, id);

			//UPDATE命令を実行
			ps.executeUpdate();

			List<String> successes = new ArrayList<>();
			successes.add("更新しました。");
			session.setAttribute("successes", successes);

			//処理後は入力フォームにリダイレクト
			resp.sendRedirect("index.html");

		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			//終了処理
			try{
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}

	}

	private List<String> validate(String id, String date, String price) {
		//エラーメッセージを入れるためのListを作る
		List<String> errors = new ArrayList<>();

		//idの必須入力
		if(id.equals("")) {
			errors.add("不正なアクセスです。");
		}

		//日付の必須入力チェック
		if(date.equals("")) {
			errors.add("題名は必須入力です。");
		}else {
			//日付の形式yyyy/MM/ddだけにする
		    try {
		    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		    	df.setLenient(false);
				String s = df.format(df.parse(date));

				if(date.equals(s)) {

				}else {
					errors.add("日付は「YYYY/MM/DD」形式で入力してください。");
				}
			} catch (ParseException e) {
				errors.add("日付は「YYYY/MM/DD」形式で入力してください。");
			}
		}

		//金額の必須入力チェック
		if(price.equals("")) {
			errors.add("金額は必須入力です。");
		}

		return errors;
	}
}
