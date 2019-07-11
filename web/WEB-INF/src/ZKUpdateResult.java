import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

/**
 * Servlet implementation class ZKUpdateResult
 */
@WebServlet("/ZKUpdateResult")
public class ZKUpdateResult extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZKUpdateResult() {
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
        PrintWriter out = response.getWriter();

        String title = "Insert";
        String tableName = new String(request.getParameter("tableName").getBytes("ISO8859-1"), "UTF-8");
        Enumeration<String> pNames = request.getParameterNames();
        try {
            out.print(Util.htmlDefaultSet(title));
            Util.ConnectSQL();


            // 执行 SQL
            stmt = Util.ct.createStatement();
            String sql, tmp;
            sql = "update " + tableName + " set ";
            int cnt = 3, index = 0;
            if ("borrow".equals(tableName))
                cnt = 5;
            while (cnt-- != 0)
                pNames.nextElement();
            while (pNames.hasMoreElements()) {
                tmp = pNames.nextElement();
                String para = request.getParameter(tmp);
                if (para != "") {
                    if (index == 0)
                        index++;
                    else
                        sql += ", ";
                    sql += tmp + " = ";
                    sql += "\'";
                    sql += para;
                    sql += "\'";
                }
            }
            sql += " where ";
            pNames = request.getParameterNames();
            pNames.nextElement();
            cnt = 1;
            if ("borrow".equals(tableName))
                cnt = 2;
            while (pNames.hasMoreElements() && cnt-- != 0) {
                tmp = pNames.nextElement();
                String para = request.getParameter(tmp);
                sql += para + " = ";
                sql += "\'";
                tmp = pNames.nextElement();
                para = request.getParameter(tmp);
                sql += para;
                sql += "\'";
                if (pNames.hasMoreElements() && cnt != 0)
                    sql += " and ";
            }
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