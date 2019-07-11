import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ZKInsertResult
 */
@WebServlet("/ZKInsertResult")
public class ZKInsertResult extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZKInsertResult() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statement stmt = null;
        // 设置响应内容类型
        response.setContentType("text/html;charset = UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = "Insert";
        String tableName = new String(request.getParameter("tableName").getBytes("ISO8859-1"), "UTF-8");
        Enumeration<String> pNames = request.getParameterNames();
        try {
            out.print(Util.htmlDefaultSet(title));
            Util.ConnectSQL();


            // 执行 SQL
            stmt = Util.ct.createStatement();
            String sql;
            sql = "insert into " + tableName + "(";
            pNames.nextElement();
            while (pNames.hasMoreElements()) {
                sql += pNames.nextElement();
                if (pNames.hasMoreElements())
                    sql += ", ";
            }
            sql += ") values(";
            pNames = request.getParameterNames();
            pNames.nextElement();
            while (pNames.hasMoreElements()) {
                sql += "\'";
                sql += request.getParameter(pNames.nextElement());
                sql += "\'";
                if (pNames.hasMoreElements())
                    sql += ", ";
            }
            sql += ")";
            out.print(sql + "<br>");
            int res = stmt.executeUpdate(sql);
            out.print(res != 0 ? (res + " row affected.<br>") : ("Error!<br>"));
            out.println("<br><a href = \"../index.jsp\">back to menu</a><br>");
            // 完成后关闭
            stmt.close();
            Util.ct.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
            out.print("Error! Check the log please.<br>");
            se.printStackTrace(out);
            out.println("<br><a href = \"../index.jsp\">back to menu</a><br>");
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 最后是用于关闭资源的块
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (Util.ct != null)
                    Util.ct.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}