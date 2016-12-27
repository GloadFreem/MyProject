<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>用户详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editUser.action?menu=1&submenu=1&page=0&size=10" method="post"
				enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${result.userId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">姓名</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<textarea name="name" class="form-control alert-success"
									placeholder="请输入姓名">${result.name }</textarea>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">头像</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<img alt="${result.name }" src="${result.image }"
									class="col-xs-2 m-b"> <input name="image"
									class="form-control alert-success" value="${result.image }"
									placeholder="请输入头像链接">
							</div> <input name="file" id="input-1" type="file" class="file">
				</div>
				</li>
				<li class="list-group-item">
					<div class="clear">手机号码</div>
				</li>
				<li class="list-group-item"><input name="telephone"
					class="form-control alert-success" value="${result.telephone }"
					placeholder="请输入手机号码">
					</div></li>

				<li class="list-group-item">
					<div class="clear">个人简介</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="intro" class="form-control alert-success"
							placeholder="请输入简介">${result.intro }</textarea>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">积分</div>
				</li>
				<li class="list-group-item"><input name="score"
					class="form-control alert-success" value="${result.score }"
					placeholder="请输入积分">
					</div></li>

				<li class="list-group-item">
					<div class="clear">等级</div>
				</li>
				<li class="list-group-item"><input name="gender"
					class="form-control alert-success" value="${result.gender }"
					placeholder="请输入等级">
					</div></li>

				<li class="list-group-item">
					<div class="clear">极光推送注册识别码</div>
				</li>
				<li class="list-group-item"><input name="regId"
					class="form-control alert-success" value="${result.regId }"
					placeholder="请输入极光推送识别码">
					</div></li>


				<li class="list-group-item">
					<div class="clear">设备类型</div>
				</li>
				<li class="list-group-item"><select id="platform" name="platform"
					class="selectpicker show-menu-arrow form-control"
					data-max-options="2">
						<c:choose>
							<c:when test="${result.platform==0}">
								<option value=0 selected=selected>安卓</option>
								<option value=1>iOS</option>
							</c:when>
							<c:otherwise>
								<option value=0>安卓</option>
								<option value=1 selected=selected>iOS</option>
							</c:otherwise>
						</c:choose>

				</select></li>

				<li class="list-group-item">
					<div class="clear">认证信息</div>
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
									<c:when test="${result.authentics != null}">
										<c:forEach items="${result.authentics}" var="item"
											varStatus="status">
											<tr>
												<td><input type="checkbox" name="post[]"
													value="${item.authId}"></td>
												<td>${status.index+1}</td>
												<td>${item.name}</td>
												<td width="100"><a href="${item.idCard}" target="blank"><img
														src="${item.idCard}" alt="城投逸园" class="img-responsive" /></a></td>
												<td>${item.house.name}</td>
												<td>${item.identity.name}</td>
												<td>${item.authenticstatus.name}</td>
												<td>${item.authDate}</td>
												<td><a
													href="authenticDetail.action?contentId=${item.authId }&menu=1&submenu=1&page=0&size=10"
													class="active"><i
														class="fa fa-edit text-success text-active"></i><i
														class="fa fa-edit text-danger text"></i></a> | <a
													href="#modal"
													data-href="deleteAuthentic.action?contentId=${item.authId }&menu=1&submenu=1&page=0&size=10"
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
