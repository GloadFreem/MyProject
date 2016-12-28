<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>认证信息</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editCharge.action?menu=2&sortmenu=2&submenu=1&page=0&size=10" method="post"
				enctype="multipart/form-result">
				<div class="">
					<input name="contentId" value="${result.chargeId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">名称</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入名称" value=${result.name }>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">业主</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入业主" value=${result.user.name }>
							</div>
						</li>
						
								<li class="list-group-item">
					<div class="clear">头像</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<img alt="${result.user.name }" src="${result.user.image }"
							class="col-xs-2 m-b"> <input name="image"
							class="form-control alert-success" value="${result.user.image }"
							placeholder="请输入内容链接">
					</div> <input name="file" id="input-1" type="file" class="file">
					</div>
				</li>
				
				
						<li class="list-group-item">
							<div class="clear">价格</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="amount" class="form-control alert-success"
									placeholder="请输入内容描述" value=${result.price }>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">状态</div>
						</li>
						<li class="list-group-item"><input name="url"
							class="form-control alert-success"
							value="${result.status }" placeholder="请输入职位">
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
