<%--
  Created by IntelliJ IDEA.
  User: ZhouKun
  Date: 2019/6/29
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        contentType = "text/html;charset=UTF-8" %>
<html>
    <head>
        <title>
            琨琨图书管理系统</title>
        <style>
            a, input, select, option, h1, body, td, th, form {
                font-family: "Fantasque Sans Mono", "Inziu Iosevka CL", sans-serif;
            }
        </style>
    </head>
    <body bgcolor = "#f0f0f0">
        <h1>
            Welcome
            to
            周琨's
            Library
            System!</h1>
        <br><br>
        <h2>
            请选择您要进行的操作：</h2>
        <h3>
            查询数据</h3>
        <form action = "TomcatTest/ZKQuery"
              method = "get">
            表：<select
                name = "tableName">
            <option value = "book">
                book
            </option>
            <option value = "borrow">
                borrow
            </option>
            <option value = "libadmin">
                libadmin
            </option>
            <option value = "reader">
                reader
            </option>
            <option value = "storehouse">
                storehouse
            </option>
        </select>
            <input type = "submit"
                   value = "查询"/>
        </form>
        <br>
        <h3>
            添加数据</h3>
        <form action = "TomcatTest/ZKInsert"
              method = "get">
            表：<select
                name = "tableName">
            <option value = "book">
                book
            </option>
            <option value = "borrow">
                borrow
            </option>
            <option value = "libadmin">
                libadmin
            </option>
            <option value = "reader">
                reader
            </option>
            <option value = "storehouse">
                storehouse
            </option>
        </select>
            <input type = "submit"
                   value = "详细"/>
        </form>
        <br>
        <h3>
            删除数据</h3>
        <form action = "TomcatTest/ZKDelete"
              method = "get">
            表：<select
                name = "tableName">
            <option value = "book">
                book
            </option>
            <option value = "borrow">
                borrow
            </option>
            <option value = "libadmin">
                libadmin
            </option>
            <option value = "reader">
                reader
            </option>
            <option value = "storehouse">
                storehouse
            </option>
        </select>
            <input type = "submit"
                   value = "详细"/>
        </form>
        <br>
        <h3>
            修改数据</h3>
        <form action = "TomcatTest/ZKUpdate"
              method = "get">
            表：<select
                name = "tableName">
            <option value = "book">
                book
            </option>
            <option value = "borrow">
                borrow
            </option>
            <option value = "libadmin">
                libadmin
            </option>
            <option value = "reader">
                reader
            </option>
            <option value = "storehouse">
                storehouse
            </option>
        </select>
            <input type = "submit"
                   value = "详细"/>
        </form>
        <br>
    </body>
</html>
