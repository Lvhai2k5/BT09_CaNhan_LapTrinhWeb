<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html><head><title>Login</title>
<style>
body{font-family:Arial;background:#f5f5f5} .box{width:360px;margin:80px auto;background:#fff;padding:20px;border-radius:8px}
.error{color:red;text-align:center}
</style></head>
<body>
<div class="box">
  <h2 style="text-align:center">Đăng nhập</h2>
  <form method="post" action="${pageContext.request.contextPath}/login">
    <label>Email</label><br/><input name="email" type="email" required/><br/>
    <label>Password</label><br/><input name="password" type="password" required/><br/><br/>
    <button type="submit">Login</button>
  </form>
  <p style="text-align:center">Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
  <c:if test="${not empty error}"><p class="error">${error}</p></c:if>
</div>
</body></html>
