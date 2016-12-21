<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- /.aside -->
<section id="content">
	<section class="vbox">
		<section class="scrollable padder">
			<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
				<li><a href="index.action"><i class="fa fa-home"></i> 首页</a></li>
				<li><a href="index.action"><i class="fa"></i> 用户管理</a></li>
				<li><a href="index.action"><i class="fa"></i> 用户列表</a></li>
			</ul>
			<section class="panel panel-default">
				<header class="panel-heading"> 用户列表 </header>
				<div class="row text-sm wrapper">
					<div class="col-sm-4 hidden-xs">
						<select class="input-sm form-control input-s-sm inline">
							<option value="0">10</option>
							<option value="1">50</option>
							<option value="2">100</option>
						</select>
					</div>
					<div class="col-sm-4 m-b-xs">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-sm btn-default active"> <input
								type="radio" name="options" id="option1"> 今日
							</label> <label class="btn btn-sm btn-default"> <input
								type="radio" name="options" id="option2"> 本周
							</label> <label class="btn btn-sm btn-default"> <input
								type="radio" name="options" id="option2"> 本月
							</label>
						</div>
					</div>
					<div class="col-sm-4">
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
								<th>头像</th>
								<th width="80">姓名</th>
								<th width="10%">手机号码</th>
								<th>内容</th>
								<th>图片</th>
								<th width="80">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${result != null}">
									<c:forEach items="${result}" var="item" varStatus="status">
										<tr>
											<td><input type="checkbox" name="post[]"
												value="${item.eventId}"></td>
											<td>${item.eventId}</td>
											<td width="100"><a href="${item.user.image}"
												target="blank"><img src="${item.user.image}" alt="城投逸园"
													class="img-responsive" /></a></td>
											<td>${item.user.name}</td>
											<td>${item.user.telephone}</td>
											<td>${item.content}</td>
											<td><c:forEach var="image" items="${item.eventimageses}">
											<a href="${image.url}" target="blank"><img
														src="${image.url}" alt="城投逸园" style="width:100px;m-l" />
													</a>
												</c:forEach></td>
											<td><a
												href="memberDetail.action?contentId=${item.eventId }"
												class="active"><i
													class="fa fa-edit text-success text-active"></i><i
													class="fa fa-edit text-danger text"></i></a> | <a href="#modal"
												data-href="deleteMember.action?contentId=${item.eventId }"
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
								<option value="0">修改</option>
								<option value="1">删除</option>
								<option value="2">导出</option>
							</select>
							<button class="btn btn-sm btn-default">应用</button>
						</div>
						<div class="col-sm-4 text-center">
							<small class="text-muted inline m-t-sm m-b-sm">显示 20-30 /
								50 条记录</small>
						</div>
						<div class="col-sm-4 text-right text-center-xs">
							<ul class="pagination pagination-lg">
								<li><a href="#"><i class="fa fa-chevron-left"></i></a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
							</ul>
						</div>
					</div>
				</footer>
			</section>
		</section>
	</section>
</section>

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
