<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="abs3.utils.HTMLUtils" %>
<jsp:include page="header.jsp"/>



		<div class="row pt-4">
			<div class="col">
				<nav class="float-left">
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href="#"><span class="oi oi-chevron-left"></span><span class="oi oi-chevron-left"></span> 前年</a>
						</li>
						<li class="page-item">
							<a class="page-link" href="#"><span class="oi oi-chevron-left"></span> 前月</a>
						</li>
					</ul>
				</nav>
			</div>

			<div class="col text-center">
				<h2 class="font-weight-bold"><span class="oi oi-calendar"></span> 2018年5月</h2>
			</div>

			<div class="col">
				<nav class="float-right">
					<ul class="pagination">
						<li class="page-item disabled">
							<a class="page-link" href="#">翌月 <span class="oi oi-chevron-right"></span></a>
						</li>
						<li class="page-item disabled">
							<a class="page-link" href="#">翌年 <span class="oi oi-chevron-right"></span><span class="oi oi-chevron-right"></span></a>
						</li>
					</ul>
				</nav>
			</div>
		</div>

		<div class="row pt-1">
			<div class="col">
				<div class="card bg-light border-info mb-4">
					<div class="card-header text-center"><span class="oi oi-yen"></span> 今月の収入合計 <small class="text-muted">（先月比）</small></div>
					<div class="card-body">
						<p class="card-text text-center">120,000 <small class="text-info">（+ 12,000）</small></p>
					</div>
				</div>
			</div>

			<div class="col">
				<div class="card bg-light border-dark mb-4">
					<div class="card-header text-center"><span class="oi oi-yen"></span> 今月の支出合計 <small class="text-muted">（先月比）</small></div>
					<div class="card-body">
						<p class="card-text text-center">-1,230 <small class="text-danger">（- 12,000）</small></p>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" style="width: 90px;">#</th>
							<th scope="col" style="width: 120px;">日付</th>
							<th scope="col">カテゴリー</th>
							<th scope="col">備考</th>
							<th scope="col" style="width: 120px;">金額</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="abs3" items="${list}">
							<c:choose>
								<c:when test="${abs3.classification == 0 || abs3.classification == '1'}">
									<tr class="table-light">
								</c:when>
								<c:otherwise>
									<tr class="table-info">
								</c:otherwise>
							</c:choose>
								<th scope="row">
									<div class="btn-group">
										<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											操作
										</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="detail.html?id=${abs3.id}"><span class="oi oi-spreadsheet"></span> 詳細 </a>
											<a class="dropdown-item" href="copy.html?id=${abs3.id}"><span class="oi oi-paperclip"></span> コピー</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item delete-btn" href="delete.html?id=${abs3.id}"><span class="oi oi-trash"></span> 削除</a>
										</div>
									</div>
								</th>
								<td>${HTMLUtils.formatDate(abs3.date)}</td>
								<td>${HTMLUtils.formatCategoryId(abs3)}</td>
								<td>${abs3.note}</td>
								<c:choose>
									<c:when test="${abs3.classification == '1'}">
										<td class="text-right">-${HTMLUtils.formatPrice(abs3.price)}</td>
									</c:when>
									<c:otherwise>
										<td class="text-right">${HTMLUtils.formatPrice(abs3.price)}</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

 <jsp:include page="footer.jsp"/>