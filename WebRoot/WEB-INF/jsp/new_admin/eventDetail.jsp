<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>认证信息</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editAuthentic.action" method="post"
				enctype="multipart/form-result">
				<div class="">
					<input name="contentId" value="${result.eventId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">发布人</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.user.name }></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">内容</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<textarea name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" >${result.content }</textarea>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">图片</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<c:forEach items="${result.eventimageses }" var="item">
									<img alt="备注图片" src="${item.url }" class="col-xs-2 m-b">
									<input name="image" class="form-control alert-success"
										value="${item.url }" placeholder="请输入内容链接">
								</c:forEach>

							</div> <input name="file" id="input-1" type="file" class="file">
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
