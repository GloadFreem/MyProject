<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>认证信息</p>
		</header>
		<section class="scrollable wrapper">
			<form
				action="submitImportCharges.action?menu=2&sortmenu=2&submenu=1&page=0&size=10"
				method="post" enctype="multipart/form-data">
				<div class="">
					<ul class="list-group gutter list-group-lg list-group-sp">

						<li class="list-group-item">
							<div class="clear">账单上传</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<img alt="${result.user.name }" src="${result.user.image }"
									class="col-xs-2 m-b"> <input name="image"
									class="form-control alert-success"
									value="${result.user.image }" placeholder="请选择excel账单文件">
							</div> <input name="file" id="input-1" type="file" class="file">
				</div>
				</li>
				<li class="list-group-item">
					<div class="clear text-danger">${tip}</div>
				</li>
				<li class="list-group-item">
					<div class="clear">上传内容</div>
				</li>
				<li class="list-group-item">
					<div class="table-responsive">
						<table class="table table-striped b-t b-light text-sm">
							<thead>
								<tr>
									<th width="20"><input type="checkbox"></th>
									<th width="90" class="th-sortable" data-toggle="class">序号
										<span class="th-sort"> <i class="fa fa-sort-down text"></i>
											<i class="fa fa-sort-up text-active"></i> <i
											class="fa fa-sort"></i>
									</span>
									</th>
									<th>业主</th>
									<th width="80">名称</th>
									<th width="10%">价格</th>
									<th>缴费状态</th>
									<th>到期时间</th>
									<th width="80">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${data != null}">
										<c:forEach items="${data}" var="item" varStatus="status">
											<tr>
												<td><input type="checkbox" name="post[]"
													value="${item.chargeId}"></td>
												<td>${item.chargeId}</td>
												<td>${item.user.name}</td>
												<td>${item.name}</td>
												<td>${item.price}元</td>
												<td><c:choose>
														<c:when test="${item.status=='1'}">
													未支付
												</c:when>
														<c:when test="${item.status=='2'}">
													已支付
												</c:when>
														<c:when test="${item.status=='3'}">
													已到账
												</c:when>
														<c:when test="${item.status=='4'}">
													已失效
												</c:when>
													</c:choose></td>
												<td>${item.endDate}</td>
												<td><a
													href="chargeDetail.action?contentId=${item.chargeId }&menu=2&sortmenu=2&submenu=1&page=0&size=10"
													class="active"><i
														class="fa fa-edit text-success text-active"></i><i
														class="fa fa-edit text-danger text"></i></a> | <a
													href="#modal"
													data-href="deleteCharge.action?contentId=${item.chargeId }&menu=2&sortmenu=2&submenu=1&page=0&size=10"
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
				</li>
				</ul>
				</div>
				<div>
					<button type="submit"
						class="btn btn-default btn-info pull-right m-t m-b m-r">完成</button>
				</div>
			</form>
		</section>
	</section>
</section>
