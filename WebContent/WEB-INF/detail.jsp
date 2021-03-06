<%@page contentType="text/html; charset=UTF-8" %>
<jsp:include page="header.jsp"/>
<%@page import="abs3.utils.HTMLUtils" %>

		<div class="row justify-content-between">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">詳細フォーム</h2>
			</div>
		</div>

		<hr class="mt-1">

		<form action="detail.html" method="post">
		<input type="hidden" name="id" value="${list.id}"/>
			<div class="form-group row">
				<label for="date" class="offset-2 col-sm-2 col-form-label font-weight-bold">日付</label>
				<div class="col-2">
					<input type="text" class="form-control"  name="date" id="date" placeholder="日付" aria-describedby="dateHelp" value="${HTMLUtils.formatDate(list.date)}" readonly>
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
							<input type="radio" id="1" name="classification" class="custom-control-input" value="1" ${HTMLUtils.checkClassification(list.classification, '1')} disabled>
							<label class="custom-control-label" for="division1">支出</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="2" name="classification" class="custom-control-input" value="2" ${HTMLUtils.checkClassification(list.classification, '2')} disabled>
							<label class="custom-control-label" for="division2">収入</label>
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group row">
				<label for="category" class="offset-2 col-sm-2 col-form-label font-weight-bold">カテゴリー</label>
				<div class="col-4">
					<select class="custom-select" id="category" name="category_id" disabled>
						<option ${HTMLUtils.selectCategory(list.categoryId, 1)}>選択して下さい</option>
						<option ${HTMLUtils.selectCategory(list.categoryId, 2)}>食費</option>
						<option ${HTMLUtils.selectCategory(list.categoryId, 3)}>交際費</option>
						<option ${HTMLUtils.selectCategory(list.categoryId, 4)}>日用品</option>
						<option ${HTMLUtils.selectCategory(list.categoryId, 5)}>アルバイト代</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="note" class="offset-2 col-sm-2 col-form-label font-weight-bold" >備考</label>
				<div class="col-6">
					<textarea class="form-control" id="note"  name="note" placeholder="備考" rows="3" readonly>${list.note}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="amount" class="offset-2 col-sm-2 col-form-label font-weight-bold">金額</label>
				<div class="col-2">
					<input type="text" class="form-control" id="amount" name="price" placeholder="金額" value="${list.price}" readonly>
				</div>
			</div>

			<div class="form-group row">
				<div class="offset-4 col-6">
					<a href="index.html" class="btn btn-secondary">キャンセル</a>
					<a href="update.html?id=${param.id != null ? param.id :list.id}" class="btn btn-primary"><span class="oi oi-pencil"></span> 修 正</a>
				</div>
				<div class="col-2 text-right">
					<a href="delete.html?id=${list.id}" class="btn btn-danger delete-btn"><span class="oi oi-trash"></span> 削 除</a>
				</div>
			</div>
		</form>
	</div>

 <jsp:include page="footer.jsp"/>