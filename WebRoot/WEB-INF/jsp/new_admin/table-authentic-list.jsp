﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<section class="scrollable padder">
			<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
				<li><a href="index.action"><i class="fa fa-home"></i> 首页</a></li>
				<li><a href="index.action"><i class="fa"></i> 用户管理</a></li>
				<li><a href="index.action"><i class="fa"></i> 认证审核</a></li>
			</ul>
			<section class="panel panel-default">
				<header class="panel-heading"> 审核列表 </header>
				<div class="row text-sm wrapper">
					<div class="col-sm-4 hidden-xs">
						<select name="size"
							class="input-sm form-control input-s-sm inline">
							<c:forEach items="${sizes}" var="item">
								<option value=${item
									}
									<c:choose>
									<c:when test="${size==item}">
										 selected=selected
									</c:when>
								</c:choose>>${item }</option>
							</c:forEach>
						</select>
					</div>
	<!-- 				<div class="col-sm-4 m-b-xs">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-sm btn-default active"> <input
								type="radio" name="options" id="option1"> 今日
							</label> <label class="btn btn-sm btn-default"> <input
								type="radio" name="options" id="option2"> 本周
							</label> <label class="btn btn-sm btn-default"> <input
								type="radio" name="options" id="option2"> 本月
							</label>
						</div>
					</div> -->
					<div class="col-sm-8">
						<div class="input-group">
							<input type="text" class="input-sm form-control"
								placeholder="请输入搜索内容"> <span class="input-group-btn">
								<button class="btn btn-sm btn-default" type="button">搜索</button>
							</span>
						</div>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-striped b-t b-light text-sm">
						<thead>
							<tr>
								<th width="20"><input type="checkbox"></th>
								<th width="90" class="th-sortable" data-toggle="class">序号 <span
									class="th-sort"> <i class="fa fa-sort-down text"></i> <i
										class="fa fa-sort-up text-active"></i> <i class="fa fa-sort"></i>
								</span>
								</th>
								<th width="80">姓名</th>
								<th width="10%">身份证</th>
								<th>房屋</th>
								<th>身份类型</th>
								<th>认证状态</th>
								<th>认证时间</th>
								<th width="80">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${result != null}">
									<c:forEach items="${result}" var="item" varStatus="status">
										<tr>
											<td><input type="checkbox" name="post[]"
												value="${item.authId}"></td>
											<td>${item.authId}</td>
											<td>${item.name}</td>
											<td width="100"><a href="${item.idCard}" target="blank"><img
													src="${item.idCard}" alt="城投逸园" class="img-responsive" /></a></td>
											<td>${item.house.name}</td>
											<td>${item.identity.name}</td>
											<td>${item.authenticstatus.name}</td>
											<td>${item.authDate}</td>
											<td><a
												href="authenticDetail.action?contentId=${item.authId }&menu=1&submenu=2&page=0&size=10"
												class="active"><i
													class="fa fa-edit text-success text-active"></i><i
													class="fa fa-edit text-danger text"></i></a> | <a href="#modal"
												data-href="deleteAuthentic.action?contentId=${item.authId }&menu=1&submenu=2&page=0&size=10"
												data-toggle="modal" class="active"><i
													class="fa fa-trash-o text-success text-active"></i><i
													class="fa fa-trash-o text-danger text"></i></a>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>

						</tbody>
					</table>
				</div>
				<footer class="panel-footer">
					<div class="row">
						<div class="col-sm-4 hidden-xs">
							<select class="input-sm form-control input-s-sm inline">
								<option value="1">删除</option>
								<option value="2">导出</option>
							</select>
							<button class="btn btn-sm btn-default">应用</button>
						</div>
						<div class="col-sm-4 text-right text-center-xs">
							<ul class="pagination">

								<c:choose>
									<c:when test="${page>0}">
										<li><a name="pre" href="#"><i
												class="fa fa-chevron-left"></i></a></li>
									</c:when>
								</c:choose>


								<c:forEach items="${pages}" var="item">
									<li
										<c:choose>
										<c:when test="${page==item}">
											class="active"
										</c:when>
									</c:choose>><a
										name="page" href="#">${item+1}</a></li>
								</c:forEach>

								<c:choose>
									<c:when test="${page<count}">
										<li><a name="next" href="#"><i
												class="fa fa-chevron-right"></i></a></li>
									</c:when>
								</c:choose>


							</ul>
						</div>
					</div>
				</footer>
			</section>
		</section>
	</section>
</section>

<script type="text/javascript">
	$("a[name='page']").click(
			function() {
				var size = $("select[name='size']").val();
				var page = $(this).text() - 1;
				var menu = ${menu};
				var sortmenu = ${sortmenu};
				var submenu = ${submenu};
				var url = "authenticList.action?" + "size=" + size + "&page=" + page
						+ "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu;
				//alert(url);

				window.location.href = url;

			});
	$("a[name='pre']").click(
			function() {
				var size = $("select[name='size']").val();
				var page = ${page};
				if (page > 0) {
					page--;
				}
				var menu = ${menu};
				var sortmenu = ${sortmenu};
				var submenu = ${submenu};
				var url = "authenticList.action?" + "size=" + size + "&page=" + page
						+ "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu;
				//alert(url);

				window.location.href = url;

			});
	$("a[name='next']").click(
			function() {
				var size = $("select[name='size']").val();
				var page = ${page};
				if (page < ${count}) {
					page++;
				}
				var menu = ${menu};
				var sortmenu = ${sortmenu};
				var submenu = ${submenu};
				var url = "authenticList.action?" + "size=" + size + "&page=" + page
						+ "&menu=" + menu + "&sortmenu=" + sortmenu
						+ "&submenu=" + submenu;
				//alert(url);

				window.location.href = url;

			});
</script>

<div id="modal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">
					<i class="icon-pencil"></i> <span id="lblAddTitle"
						style="font-weight:bold">是否确认删除内容？</span>
				</h4>
			</div>
			<form class="form-horizontal form-bordered form-row-strippe"
				id="ffAdd" action="" data-toggle="validator"
				enctype="multipart/form-data">
				<div class="modal-body">删除后数据将无法恢复，请确认！</div>
				<div class="modal-footer">
					<input type="hidden" id="ID" name="ID" />
					<button type="submit" class="btn btn-default" data-dismiss="modal">取消</button>
					<a id="confirm" class="btn btn-info">确认</a>
				</div>
			</form>
		</div>
	</div>
</div>
