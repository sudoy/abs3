package abs3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String date = req.getParameter("date");
		String classification = req.getParameter("classification");
		String categoryId = req.getParameter("categoryId");
		String note = req.getParameter("note");
		String price = req.getParameter("price");

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

//	private List<String> validate(String date, String categoryId, String price) {
//		List<String> errors = new ArrayList<>();
//
//		if(date.equals("")){
//			errors.add("日付は必須入力です。");
//		}
//
//		if(categoryId.equals("")) {
//			errors.add("カテゴリーは必須入力です。");
//		}
//
//		if(!date.equals("")) {
//			try {
//				LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu/MM/dd")
//						.withResolverStyle(ResolverStyle.STRICT));
//
//			}catch(Exception e) {
//				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
//			}
//		}
//
//		if(price.equals("")){
//			errors.add("金額は必須入力です。");
//
//		}
//		return errors;
//
//	}

}
