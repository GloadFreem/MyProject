<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>圈子详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editAuthentic.action" method="post"
				enctype="multipart/form-result">
				<div class="">
					<input name="contentId" value="${result.contentId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">发布者</div>
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
									placeholder="请输入内容描述">${result.content }</textarea>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">图片</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<c:forEach items="${result.publiccontentimageses}" var="image">
									<img alt="${image.url }" src="${image.url }"
										class="col-xs-2 m-b">
									<input name="image" class="form-control alert-success"
										value="${image.url }" placeholder="请输入内容链接">"
								</c:forEach>
							</div> <input name="file" id="input-1" type="file" class="file">
						<li class="list-group-item">
							<div class="clear">点赞</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<c:forEach items="${result.contentprises}" var="image">
									<img alt="${image.user.name }" src="${image.user.image }"
										class="col-xs-1 m-b">
								</c:forEach>
							</div> <li class="list-group-item">
							<div class="clear">评论</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<c:forEach items="${result.contentcomments}" var="image">
									<div>${image.userByUserId.name}:${image.content}</div>
								</c:forEach>
							</div>
				
				</div>
				</li>
						



				<li class="list-group-item">
					<div class="clear">发布时间</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<input name="desc" class="form-control alert-success"
							placeholder="请输入价格" value=${result.publicDate }></input>
					</div>
				</li>

				</ul>
				</div>
<!-- 				<div>
					<button type="submit"
						class="btn btn-default btn-info pull-right m-t m-b m-r">完成</button>
				</div> -->
			</form>
		</section>
	</section>
</section>
