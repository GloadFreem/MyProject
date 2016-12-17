<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>用户详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editUser.action" method="post" enctype="multipart/form-result">
				<div class="">
				 <input name="contentId" value="${result.userId}" style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">姓名</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
							<textarea name="desc" class="form-control alert-success"  placeholder="请输入内容描述">${result.name }</textarea></div>
						</li>
						<li class="list-group-item">
							<div class="clear">头像</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<img alt="${result.name }" src="${result.image }" class="col-xs-2 m-b">
							<input name="image" class="form-control alert-success" value="${result.image }" placeholder="请输入内容链接"></div>
								
								<input name="file" id="input-1" type="file" class="file">
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">手机号码</div>
						</li>
						<li class="list-group-item">
							<input name="url" class="form-control alert-success" value="${result.telephone }" placeholder="请输入内容链接"></div>
						</li>
						
						<li class="list-group-item">
							<div class="clear">个人简介</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
							<textarea name="desc" class="form-control alert-success"  placeholder="请输入简介">${result.intro }</textarea></div>
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
