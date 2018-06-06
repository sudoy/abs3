package abs3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abs3.beans.Abs3;
import abs3.utils.DBUtils;
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try{
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT * FROM account_books ORDER BY id;";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECT命令を実行
			rs = ps.executeQuery();

			//ResultSetをJavaBeansに変換
			List<Abs3> list = new ArrayList<>();

			while(rs.next()) {
				Abs3 abs3 = new Abs3(rs.getInt("id"),
						rs.getDate("date"),
						rs.getString("classification"),
						rs.getString("category"),
						rs.getString("note"),
						rs.getInt("price"));

				list.add(abs3);
			}

			//JavaBeansをJSPへ渡す
			req.setAttribute("list", list);

			//foward→index.jsp
			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp")
				.forward(req, resp);
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

}
