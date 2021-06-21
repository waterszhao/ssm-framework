<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>hello</title>

  <style>
    body{
      background: linear-gradient(115deg, #3fdabd 0%, #d1da46 100%);
      font-family:"DejaVu Sans Mono","楷体",serif;
    }
    a{
      line-height: 30px;
      font-size: 30px;
      text-decoration: none;
      color: black;
    }
    a:hover {
      color: #8eff8a;
    }

  </style>

</head>
<body>
<div style="text-align: center;">
  <h1>Hello Waters!</h1>
  <h2>Hello Strangers!</h2>
  <a href="${pageContext.request.contextPath}/book/allBook">
    我的管理系统(beta)
  </a>
</div>
</body>
</html>