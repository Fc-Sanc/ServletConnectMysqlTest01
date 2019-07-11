import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ZKQuery
 */
@WebServlet("/ZKQuery")
public class ZKQuery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZKQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statement stmt = null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = "��ѯ���";
        try {
            out.print(Util.htmlDefaultSet(title));
            out.print("<h1 align=\"center\">" + title + "</h1>\n");
            Util.ConnectSQL();

            String tableName = new String(request.getParameter("tableName").getBytes("ISO8859-1"), "UTF-8");
            out.print("<h2 align=\"center\">" + tableName + "</h2>");
            // ִ�� SQL
            stmt = Util.ct.createStatement();
            String sql;
            sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();
            int colNum = rsm.getColumnCount();
            out.println("<table border = \"1\" align = \"center\">" +
                    "<tr>");
            for (int i = 1; i <= colNum; i++) {
                out.println("<th>" + rsm.getColumnName(i) + "</th>");
            }
            out.println("</tr>");
            // չ����������ݿ�
            while (rs.next()) {
                // �������
                out.println("<tr>");
                for (int i = 1; i <= colNum; i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
                out.println("</tr>");
                out.println("<br/>");
            }
            out.println("</table>");
            out.println("<br><a href = \"../index.jsp\">back to menu</a><br>");
            out.println("</body></html>");

            // ��ɺ�ر�
            rs.close();
            stmt.close();
            Util.ct.close();

        } catch (SQLException se) {
            // ���� JDBC ����
            se.printStackTrace();
        } catch (Exception e) {
            // ���� Class.forName ����
            e.printStackTrace();
        } finally {
            // ��������ڹر���Դ�Ŀ�
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