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
		String category = req.getParameter("category");
		String price = req.getParameter("price");
		String note = req.getParameter("note");

		List<String> errors =  validate(date, category, price);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp")
			.forward(req, resp);
 			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;




		try {
			con = DBUtils.getConnection();


			sql = "INSERT INTO account_books(date, classification, note, price,category_id)values (?,?,?,?,?)";
			//INSERT命令の準備
			ps = con.prepareStatement(sql);
			//INSERT命令にポストデータの内容をセット
			ps.setString(1, req.getParameter("date"));
			ps.setString(2, req.getParameter("classification"));
			//ps.setString(4, req.getParameter("note"));
			//			ps.setString(3, note.equals("選択して下さい") ? null : note);
			ps.setString(3, req.getParameter("note"));
			ps.setString(4, req.getParameter("price"));
			if(req.getParameter("category") == "1") {
				ps.setString(5, null);

			}else {
				ps.setString(5, req.getParameter("category"));
			}

			ps.executeUpdate();






		}catch(Exception e){
			throw new ServletException(e);

		}finally{
			try{

				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){}
		}

	}

	private List<String> validate(String date, String category, String price) {
		List<String> errors = new ArrayList<>();
		if(date.equals("")){
			errors.add("日付は必須入力です。");

		}
		if(category.equals("")) {
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
		return errors;

	}

}
