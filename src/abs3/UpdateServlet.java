package abs3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			ps= con.prepareStatement(sql);
			//SELECT文にパラメーターの内容をセット
			ps.setString(1, req.getParameter("id"));

			//SELCT命令を実行
			rs=ps.executeQuery();

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
				if(con != null){con.close();}
				if(ps != null){ps.close();}
				if(rs != null){rs.close();}
			}catch(Exception e){}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try{
			//データベースの接続を確立
			con = DBUtils.getConnection();

			sql = "UPDATE account_books SET date = ?, classification = ?, category_id = ?, note= ?, price=? WHERE id = ?";
			//INSERT命令の準備
			ps = con.prepareStatement(sql);

			//INSERT命令にポストデータの内容をセット
			ps.setString(1, req.getParameter("date"));
			ps.setString(2, req.getParameter("classification"));
			ps.setString(3, req.getParameter("category_id"));
			ps.setString(4, req.getParameter("note"));
			ps.setString(5, req.getParameter("price"));
			ps.setString(6, req.getParameter("id"));


			//コマンドプロンプトで確認
			//Sysout(ps);

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
