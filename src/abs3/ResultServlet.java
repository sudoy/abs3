package abs3;

import java.io.IOException;
import java.sql.Connection;
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

@WebServlet("/result.html")
public class ResultServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		getServletContext().getRequestDispatcher("/WEB-INF/result.jsp")
				.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String note = req.getParameter("note");
		String date = req.getParameter("date1");
		String date2 = req.getParameter("date2");

		List<String> errors =  validate(date);
		if(errors.size() > 0) {
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/search.jsp")
				.forward(req, resp);
			return;
		}

		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT id,date,classification, category_id, note, price "
					+ "FROM account_books where note = ? "
					+ "or date between ? and ?"
					+ "order by  date";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			ps.setString(1, note);
			ps.setString(2, date );
			ps.setString(3, date2 );

			//SELECT命令を実行
			rs = ps.executeQuery();


			List<Abs3> list = new ArrayList<>();

			while(rs.next()) {
				Abs3 abs3 = new Abs3(rs.getInt("id"),
						rs.getDate("date"),
						rs.getInt("classification"),
						rs.getString("note"),
						rs.getInt("price"),
						rs.getInt("category_id"));

				list.add(abs3);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);
			//foward
			getServletContext().getRequestDispatcher("/WEB-INF/result.jsp")
				.forward(req, resp);
			//resp.sendRedirect("result.html");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			//終了処理
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){
			}
		}
	}
	private List<String> validate(String date) {
		List<String> errors = new ArrayList<>();


		//日付の形式（yyyy/MM//dd）
		if(!date.equals("")) {
			try {
				LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu/MM/dd")
					.withResolverStyle(ResolverStyle.STRICT));
			}catch(Exception e) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		return errors;

	}


}
