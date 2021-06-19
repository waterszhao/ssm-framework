<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>书籍展示</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header">
                    <h1>书籍列表</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2 column ">
                    <a href="${pageContext.request.contextPath}/book/toAddBook" class="btn btn-primary ">新增书籍信息</a>
                </div>
                <div class="col-md-2 column ">
                    <a href="${pageContext.request.contextPath}/book/allBook" class="btn btn-primary ">查看所有书籍信息</a>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-2"><span style="color: red;font-size: larger">${error}</span></div>
                <div class="col-md-4 center">
                    <form class="form-inline" action="${pageContext.request.contextPath}/book/queryByName" method="get">
                        <input type="text" name="name" placeholder="请输入想要查询的书本名称" class="text">&nbsp;&nbsp;
                        <input type="submit" value="查询" class="btn btn-primary">
                    </form>
                </div>

            </div>
        </div><br/>
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <tr>
                        <th>书籍编号</th>
                        <th>书籍名称</th>
                        <th>书籍数量</th>
                        <th>书籍详情</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <c:forEach var="book" items="${books}">
                    <tr>
                        <th>${book.bookID}</th>
                        <th>${book.bookName}</th>
                        <th>${book.bookCount}</th>
                        <th>${book.detail}</th>
                        <th>
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toUpdateBook?bookID=${book.bookID}">修改</a> &nbsp; | &nbsp;
                            <a class="btn-primary btn" href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                        </th>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            </tbody>

        </div>
    </div>
</body>
</html>
