<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>服务详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editService.action?menu=2&sortmenu=1&submenu=1&page=0&size=10" method="post"
				enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${result.typeId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">名称</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="name" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.name }></input>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">图片</div>
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
					<div class="clear">内容</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="content" class="form-control alert-success"
							placeholder="请输入内容描述">${result.content }</textarea>
					</div>
				</li>
				<li class="list-group-item">
					<div class="clear">价格</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<input name="price" class="form-control alert-success"
							placeholder="请输入价格" value=${result.price }></input>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">支付方式</div>
				</li>
				<li class="list-group-item"><input name="url"
					class="form-control alert-success" value="${result.paytype.name }"
					placeholder="请输入支付方式">
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
