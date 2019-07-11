import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

class Util {
    static Connection ct = null;

    public static void ConnectSQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydatabase?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                    "root", "root");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String htmlDefaultSet(String title) {
        String docType = "<!DOCTYPE html>\n";
        return docType +
                "<html>\n" +
                "<head>" +
                "<title>" + title + "</title>" +
                "<meta charset = \"utf-8\"/>" +
                "<style>\n" +
                "fieldset{\n" +
                "            background-color: #f1f1f1;\n" +
                "            border: none;\n" +
                "            border-radius: 2px;\n" +
                "            margin-bottom: 12px;\n" +
                "            overflow: hidden;\n" +
                "            padding: 0 .625em;\n" +
                "        }\n" +
                "\n" +
                "        select, label{\n" +
                "            cursor: pointer;\n" +
                "            display: inline-block;\n" +
                "            padding: 3px 6px;\n" +
                "            text-align: right;\n" +
                "            width: 150px;\n" +
                "            vertical-align: top;\n" +
                "        }\n" +
                "\n" +
                "        input{\n" +
                "            font-size: inherit;\n" +
                "        }\n" +
                "        a, input, select, option, h1, body, td, th, form {\n" +
                "            font-family: \"Fantasque Sans Mono\", \"Inziu Iosevka CL\", serif;\n" +
                "        }\n" +
                "    </style>" +
                "</head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n";
    }
}