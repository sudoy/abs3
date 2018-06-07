<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="abs3.utils.HTMLUtils" %>
<jsp:include page="header.jsp"/>

		<div class="row justify-content-between">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">修正フォーム</h2>
			</div>
		</div>

		<hr class="mt-1">

		<form action="update.html?id=${param.id != null ? param.id :list.id}" method="post">
			<div class="form-group row">
				<label for="date" class="offset-2 col-sm-2 col-form-label font-weight-bold">日付 <span class="badge badge-danger">必須</span></label>
				<div class="col-2">
					<input type="text" class="form-control"  name="date" id="date" placeholder="日付" aria-describedby="dateHelp" value="${param.date != null ? param.date : HTMLUtils.formatDate(list.date)}" >
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
							<input type="radio" id="1" value="1" name="classification" class="custom-control-input" ${HTMLUtils.checkClassification(param.classification != null ? param.classification : list.classification, '1')}>
							<label class="custom-control-label" for="1">支出</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="2" value="2" name="classification" class="custom-control-input" ${HTMLUtils.checkClassification(param.classification != null ? param.classification : list.classification, '2')}>
							<label class="custom-control-label" for="2">収入</label>
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group row">
				<label for="categoryId" class="offset-2 col-sm-2 col-form-label font-weight-bold">カテゴリー <span class="badge badge-danger">必須</span></label>
				<div class="col-4">
					<select class="custom-select" id="categoryId" name="categoryId">
						<option value="1" ${param.categoryId != null ? HTMLUtils.selectCategory(param.categoryId, 1) : HTMLUtils.selectCategory(list.categoryId, 1)}>選択してください</option>
						<option value="2" ${param.categoryId != null ? HTMLUtils.selectCategory(param.categoryId, 2) : HTMLUtils.selectCategory(list.categoryId, 2)}>食費</option>
						<option value="3" ${param.categoryId != null ? HTMLUtils.selectCategory(param.categoryId, 3) : HTMLUtils.selectCategory(list.categoryId, 3)}>交際費</option>
						<option value="4" ${param.categoryId != null ? HTMLUtils.selectCategory(param.categoryId, 4) : HTMLUtils.selectCategory(list.categoryId, 4)}>日用品</option>
						<option value="5" ${param.categoryId != null ? HTMLUtils.selectCategory(param.categoryId, 5) : HTMLUtils.selectCategory(list.categoryId, 5)}>アルバイト代</option>
						<option value="6" ${param.categoryId != null ? HTMLUtils.selectCategory(param.categoryId, 6) : HTMLUtils.selectCategory(list.categoryId, 6)}>その他</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="note" class="offset-2 col-sm-2 col-form-label font-weight-bold" >備考</label>
				<div class="col-6">
					<textarea class="form-control" id="note"  name="note" placeholder="備考" rows="3" >${param.note != null ? param.note : list.note}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="amount" class="offset-2 col-sm-2 col-form-label font-weight-bold">金額 <span class="badge badge-danger">必須</span></label>
				<div class="col-2">
					<input type="text" class="form-control" id="amount" name="price" placeholder="金額" value="${param.price != null ? param.price : list.price}" >
				</div>
			</div>

			<div class="form-group row">
				<div class="offset-4 col-8">
					<a href="detail.html?id=${param.id != null ? param.id :list.id}" class="btn btn-secondary">キャンセル</a>
					<button type="submit" class="btn btn-primary"><span class="oi oi-check"></span> 修正OK</button>

				</div>
			</div>
		</form>
	</div>

 <jsp:include page="footer.jsp"/>