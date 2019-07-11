import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ZKInsert
 */
@WebServlet("/ZKInsert")
public class ZKInsert extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZKInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statement stmt = null;
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Insert";
        String tableName = new String(request.getParameter("tableName").getBytes("ISO8859-1"), "UTF-8");
        try {
            out.print(Util.htmlDefaultSet(title));
            out.print("<h1 align=\"center\">" + title + " into " + tableName + "</h1>\n");
            Util.ConnectSQL();


            // 执行 SQL
            stmt = Util.ct.createStatement();
            String sql;
            sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();
            int colNum = rsm.getColumnCount();
            out.print("<h3>Please input values of the row you want to insert:</h3>");
            out.println("<form action=\"ZKInsertResult\"\n" +
                    " method=\"get\">" +
                    "<input type=\"radio\" checked=\"checked\" name=\"tableName\" value=\"" + tableName + "\" /><br>");
            for (int i = 1; i <= colNum; i++) {
                out.println("<p><label>" + rsm.getColumnName(i) + ": </label><input type = \"text\" name = \"" + rsm.getColumnName(i) + "\"/></p><br>");
            }
            out.println("<input type = \"submit\" value = \"Insert\"/>");
            out.println("</form>");
            out.println("<br><a href = \"../index.jsp\">back to menu</a><br>");
            out.println("</body></html>");

            // 完成后关闭
            rs.close();
            stmt.close();
            Util.ct.close();

        } catch (SQLException se) {
            // 处理 JDBC 错误
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