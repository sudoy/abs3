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


@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();
			sql= "delete from account_books where id = ? ";

			//準備
			ps = con.prepareStatement(sql);

			//ポストデータをセット
			ps.setString(1, id);

			//INSERT命令を実行
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


}
