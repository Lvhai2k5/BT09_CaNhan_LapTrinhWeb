<%@ page contentType="text/html;charset=UTF-8" %>
<html><head><title>Register</title></head>
<body>
<div style="width:360px;margin:80px auto;background:#fff;padding:20px;border-radius:8px">
  <h2 style="text-align:center">Đăng ký</h2>
  <form method="post" action="${pageContext.request.contextPath}/register">
    <label>Họ tên</label><br/><input name="fullname" required/><br/>
    <label>Email</label><br/><input name="email" type="email" required/><br/>
    <label>Password</label><br/><input name="password" type="password" required/><br/>
    <label>Phone</label><br/><input name="phone"/><br/><br/>
    <button type="submit">Tạo tài khoản</button>
  </form>
  <p style="text-align:center"><a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
</div>
</body></html>
