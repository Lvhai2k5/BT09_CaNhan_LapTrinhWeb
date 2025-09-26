<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body class="container mt-4">
<h2 class="mb-3">Danh sách sản phẩm</h2>

<!-- Bảng sản phẩm render từ Model (Spring Controller) -->
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Ảnh</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.description}</td>
            <td>
                <c:if test="${not empty p.image}">
                    <img src="${p.image}" width="80"/>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr/>

<!-- AJAX fetch GraphQL để hiển thị danh sách -->
<h3 class="mt-4">Load sản phẩm qua GraphQL (AJAX)</h3>
<button id="btnLoad" class="btn btn-primary mb-2">Tải sản phẩm</button>
<table class="table table-striped" id="tblGraphql">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
    $(document).ready(function () {
        $("#btnLoad").click(function () {
            const query = `
                query {
                    products {
                        id
                        name
                        price
                    }
                }
            `;

            $.ajax({
                url: '/graphql',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({query: query}),
                success: function (res) {
                    let rows = "";
                    res.data.products.forEach(p => {
                        rows += `<tr>
                                    <td>${p.id}</td>
                                    <td>${p.name}</td>
                                    <td>${p.price}</td>
                                 </tr>`;
                    });
                    $("#tblGraphql tbody").html(rows);
                },
                error: function (xhr) {
                    alert("Lỗi khi load GraphQL: " + xhr.responseText);
                }
            });
        });
    });
</script>

</body>
</html>
