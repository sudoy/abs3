<%@page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">

	<title>My家計簿アプリ|TOP</title>
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand mb-0" href="index.html">My家計簿アプリ</a>
			<form class="form-inline">
				<a class="btn btn-outline-success mr-2" href="search.html"><span class="oi oi-magnifying-glass"></span> 検 索</a>
				<a class="btn btn-outline-info" href="entry.html"><span class="oi oi-plus"></span> 登 録</a>
			</form>
		</div>
	</nav>

	<div class="container pt-6">

		<div class="row">
			<div class="col">
				<div class="alert alert-success alert-dismissible fade show" role="alert">
					<h4 class="alert-heading h5 font-weight-bold"><span class="oi oi-pin"></span> 成功しました！</h4>
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul>
						<li>「2018/05/30 交際費 -6,800」を登録しました。</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<h4 class="alert-heading h5 font-weight-bold"><span class="oi oi-pin"></span> エラーが発生しました！</h4>
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul>
						<li>日付は必須入力です。</li>
						<li>カテゴリーは必須入力です。</li>
						<li>金額は必須入力です。</li>
					</ul>
				</div>
			</div>
		</div>

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
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/30</td>
							<td>日用品</td>
							<td>ティッシュペーパー、歯磨き粉など</td>
							<td class="text-right">-740</td>
						</tr>
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/30</td>
							<td>食費</td>
							<td>ランチ</td>
							<td class="text-right">-800</td>
						</tr>
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/30</td>
							<td>交際費</td>
							<td></td>
							<td class="text-right">-6,800</td>
						</tr>
						<tr class="table-info">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="copy.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>
							</th>
							<td>2018/05/31</td>
							<td>アルバイト代</td>
							<td></td>
							<td class="text-right">120,000</td>
						</tr>
						<tr class="table-light">
							<th scope="row">
								<div class="btn-group">
									<button type="button" class="btn btn-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										操作
									</button>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="detail.html"><span class="oi oi-spreadsheet"></span> 詳細</a>
										<a class="dropdown-item" href="update.html"><span class="oi oi-paperclip"></span> コピー</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item delete-btn" href="index.html"><span class="oi oi-trash"></span> 削除</a>
									</div>
								</div>

							</th>
							<td>2018/05/31</td>
							<td>交際費</td>
							<td></td>
							<td class="text-right">-6,800</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<hr>

	<footer class="footer">
		<div class="container">
			<p class="text-muted small">&copy; 2018 SIE Inc.</p>
		</div>
	</footer>

	<script src="js/jquery-3.3.1.slim.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

<script>
$(function(){
	$('.delete-btn').on('click', function(){
		return confirm('削除してよろしいですか？');
	});

	$('.category-all').on('click', function(){
		// allのチェック状態と他の選択肢のチェック状態をリンク
		$('.category').prop('checked', $(this).prop('checked'));
	});

	$('.category').on('click', function(){
		if(!$(this).prop('checked')){
			// チェックが外れたときは、allのチェックも外す
			$('.category-all').prop('checked', false);

		}else{
			// チェックが入ったときは、
			// 他の選択肢もすべてチェックだった場合に、allをチェックする
			var isChange = true;

			$('.category').each(function(){
				if(!$(this).prop('checked')){
					isChange = false;
				}
			});
			if(isChange){
				$('.category-all').prop('checked', true);
			}
		}
	});
});
</script>
</body>
</html>