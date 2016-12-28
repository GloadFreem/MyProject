<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>认证信息</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editAuthentic.action?menu=1&submenu=2&page=0&size=10" method="post"
				enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${result.authId}"
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
							<div class="clear">身份类型</div>
						</li>
						<li class="list-group-item"><select id="identity"
							name="identity"
							class="selectpicker show-menu-arrow
							form-control"
							data-max-options="2">
								<c:choose>
									<c:when test="${result.identity.identiyTypeId==-1}">
										<option value=-1 selected=selected>无身份</option>
										<option value=1>业主</option>
										<option value=2>员工</option>
										<option value=3>管理员</option>
									</c:when>
									<c:when test="${result.identity.identiyTypeId==1}">
											<option value=-1>无身份</option>
											<option value=1 selected=selected>业主</option>
											<option value=2>员工</option>
											<option value=3>管理员</option>
										</c:when>
										<c:when test="${result.identity.identiyTypeId==2}">
											<option value=-1>无身份</option>
											<option value=1>业主</option>
											<option value=2 selected=selected>员工</option>
											<option value=3>管理员</option>
										</c:when>
										<c:otherwise>
											<option value=-1>无身份</option>
											<option value=1>业主</option>
											<option value=2>员工</option>
											<option value=3 selected=selected>管理员</option>
										</c:otherwise>
								</c:choose>
						</select></li>


						<li class="list-group-item">
							<div class="clear">身份证</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<img alt="${result.idCard }" src="${result.idCard }"
									class="col-xs-2 m-b"> <input name="image"
									class="form-control alert-success" value="${result.idCard }"
									placeholder="请输入身份证图片">
							</div> <input name="file" id="input-1" type="file" class="file">
				</div>
				</li>
				<li class="list-group-item">
					<div class="clear">房屋</div>
				</li>
				<li class="list-group-item"><input name="url"
					class="form-control alert-success" value="${result.house.name }"
					placeholder="请输入房屋">
					</div></li>


				<li class="list-group-item">
					<div class="clear">认证时间</div>
				</li>
				<li class="list-group-item"><input name="url"
					class="form-control alert-success" value="${result.authDate }"
					placeholder="请输入时间">
					</div></li>

				<li class="list-group-item">
					<div class="clear">认证状态</div>
				</li>
				<li class="list-group-item"><select id="status"
					name="status"
					class="selectpicker show-menu-arrow
							form-control"
					data-max-options="2">
						<c:choose>
							<c:when test="${result.authenticstatus.statusId==1}">
								<option value=1 selected=selected>未认证</option>
								<option value=2>认证中</option>
								<option value=3>已认证</option>
								<option value=4>认证失败</option>
							</c:when>
							<c:when test="${result.authenticstatus.statusId==2}">
								<option value=1>未认证</option>
								<option value=2 selected=selected>认证中</option>
								<option value=3>已认证</option>
								<option value=4>认证失败</option>
							</c:when>
							<c:when test="${result.authenticstatus.statusId==3}">
								<option value=1>未认证</option>
								<option value=2>认证中</option>
								<option value=3 selected=selected>已认证</option>
								<option value=4>认证失败</option>
							</c:when>
							<c:otherwise>
								<option value=1>未认证</option>
								<option value=2>认证中</option>
								<option value=3>已认证</option>
								<option value=4 selected=selected>认证失败</option>
							</c:otherwise>
						</c:choose>
				</select></li>

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
