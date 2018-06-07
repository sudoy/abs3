<%@page contentType="text/html; charset=UTF-8" %>
 <jsp:include page="header.jsp"/>



		<div class="row justify-content-between">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">検索フォーム</h2>
			</div>
		</div>

		<hr class="mt-1">

		<form action="result.html" method="post">
			<div class="form-group row">
				<label for="date" class="offset-1 col-sm-2 col-form-label font-weight-bold">日付</label>

				<div class="col-2">
					<input type="text" name="date1" class="form-control" id="date" placeholder="日付" aria-describedby="dateHelp" value="2018/05/31">
				</div>
				<div class="col-1">
					<input type="text" readonly class="form-control-plaintext text-center" id="staticEmail" value="～">
				</div>
				<div class="col-2">
					<input type="text" name="date2" class="form-control" id="date" placeholder="日付" aria-describedby="dateHelp" value="2018/05/31">
				</div>
				<div class="col-4">
					<small id="dateHelp"  class="text-muted align-bottom">「YYYY/MM/DD」形式で入力してください。</small>
				</div>
			</div>

			<fieldset class="form-group">
				<div class="row">
					<legend class="offset-1 col-form-label col-2 pt-0 font-weight-bold">カテゴリー</legend>
					<div class="col-sm-8">
						<div class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" id="category-all" name="categoryId" class="custom-control-input category-all" checked>
							<label class="custom-control-label" for="category-all">全て</label>
						</div>
					</div>
					<div class="offset-3 col-sm-8">
						<div class="custom-control custom-checkbox custom-control-inline">
 							<input type="checkbox" id="category1" value="2" name="categoryId1" class="custom-control-input category" checked>
							<label class="custom-control-label"  for="category1">食費</label>
						</div>
						<div class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" id="category2" value="3" name="categoryId2" class="custom-control-input category" checked>
							<label class="custom-control-label" for="category2">交際費</label>
						</div>
						<div class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" id="category3" value="4" name="categoryId3" class="custom-control-input category" checked>
							<label class="custom-control-label" for="category3">日用品</label>
						</div>
						<div class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" id="category4" value="5" name="categoryId4" class="custom-control-input category" checked>
							<label class="custom-control-label" for="category4">アルバイト代</label>
						</div>
						<div class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" id="category5" value="6" name="categoryId5" class="custom-control-input category" checked>
							<label class="custom-control-label" for="category5">その他</label>
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group row">
				<label for="note" class="offset-1 col-sm-2 col-form-label font-weight-bold">備考 <span class="badge badge-success	">部分一致</span></label>
				<div class="col-5">
					<input type="text" class="form-control" name="note" id="note" placeholder="備考">
				</div>
			</div>

			<div class="form-group row">
				<div class="offset-3 col-8">
					<a href="index.html" class="btn btn-secondary">キャンセル</a>
					<button type="submit" class="btn btn-primary "><span class="oi oi-magnifying-glass"></span> 検 索</button>
				</div>
			</div>
		</form>
	</div>

 <jsp:include page="footer.jsp"/>