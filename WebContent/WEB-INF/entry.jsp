<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="abs3.utils.HTMLUtils" %>

<jsp:include page="header.jsp"/>

		<div class="row justify-content-between">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">登録フォーム</h2>
			</div>
		</div>

		<hr class="mt-1">

		<form action="entry.html" method="post">
			<div class="form-group row">
				<label for="date" class="offset-2 col-sm-2 col-form-label font-weight-bold">日付 <span class="badge badge-danger">必須</span></label>
				<div class="col-2">
					<input type="text" class="form-control" id="date" name="date" placeholder="日付" aria-describedby="dateHelp" value="${param.date}">
				</div>
				<div class="col-4">
					<small id="dateHelp" class="text-muted align-bottom">「YYYY/MM/DD」形式で入力してください。</small>
				</div>
			</div>

			<fieldset class="form-group">
				<div class="row">
					<legend class="offset-2 col-form-label col-2 pt-0 font-weight-bold">区分</legend>
					<div class="col-sm-8">
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="division1" name="classification" value="1" class="custom-control-input" ${HTMLUtils.checkClassification(param.classification, '1')}>
							<label class="custom-control-label" for="division1" >支出</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="division2" name="classification" value="2" class="custom-control-input" ${HTMLUtils.checkClassification(param.classification, '2')}>
							<label class="custom-control-label" for="division2">収入</label>
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group row">
				<label for="category" class="offset-2 col-sm-2 col-form-label font-weight-bold" >カテゴリー <span class="badge badge-danger">必須</span></label>
				<div class="col-4">
					<select class="custom-select" id="categoryId" name="categoryId">
						<option value="1" ${HTMLUtils.selectCategory(param.categoryId, 1)}>選択してください</option>
						<option value="2" ${HTMLUtils.selectCategory(param.categoryId, 2)}>食費</option>
						<option value="3" ${HTMLUtils.selectCategory(param.categoryId, 3)}>交際費</option>
						<option value="4" ${HTMLUtils.selectCategory(param.categoryId, 4)}>日用品</option>
						<option value="5" ${HTMLUtils.selectCategory(param.categoryId, 5)}>アルバイト代</option>
						<option value="6" ${HTMLUtils.selectCategory(param.categoryId, 6)}>その他</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="note" class="offset-2 col-sm-2 col-form-label font-weight-bold">備考</label>
				<div class="col-6">
					<textarea class="form-control" id="note" placeholder="備考" rows="3" name="note">${param.note}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="amount" class="offset-2 col-sm-2 col-form-label font-weight-bold">金額 <span class="badge badge-danger">必須</span></label>
				<div class="col-2">
					<input type="text" class="form-control" id="amount" placeholder="金額" name="price" value="${param.price}">
				</div>
			</div>

			<div class="form-group row">
				<div class="offset-4 col-8">
					<a href="index.html" class="btn btn-secondary">キャンセル</a>
					<button type="submit" class="btn btn-primary "><span class="oi oi-check"></span> 登録OK</button>
				</div>
			</div>
		</form>
	</div>

<jsp:include page="footer.jsp"/>