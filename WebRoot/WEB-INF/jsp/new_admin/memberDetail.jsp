<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>认证信息</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editMember.action?menu=1&submenu=3&page=0&size=10"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${result.memberId}"
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
							<div class="clear">手机号码</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<textarea name="telephone" class="form-control alert-success"
									placeholder="请输入内容描述">${result.telephone }</textarea>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">职位</div>
						</li>
						<li class="list-group-item"><select id="position"
							name="position"
							class="selectpicker show-menu-arrow
							form-control"
							data-max-options="2">
								<c:forEach items="${types}" var="item">
									<option value="${item.typeId }"
										<c:choose>
							<c:when test="${result.servicetype.typeId==item.typeId }">
							selected=selected
							</c:when>
						</c:choose>>${item.name}</option>
								</c:forEach>
						</select>
				</div>
				</li>


				<li class="list-group-item">
					<div class="clear">头像</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<img alt="${result.image }" src="${result.image }"
							class="col-xs-2 m-b"> <input name="image"
							class="form-control alert-success" value="${result.image }"
							placeholder="请输入内容链接">
					</div> <input name="file" id="input-1" type="file" class="file">
					</div>
				</li>
				<li class="list-group-item">
					<div class="clear">等级</div>
				</li>
				<li class="list-group-item"><input name="gender"
					class="form-control alert-success" value="${result.gender }"
					placeholder="请输入房屋">
					</div></li>
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
