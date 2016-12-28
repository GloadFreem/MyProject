﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>公告信息</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editAnnounce.action?menu=7&sortmenu=1&submenu=1&page=0&size=10" method="post"
				enctype="multipart/form-result">
				<div class="">
					<input name="contentId" value="${result.announceId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">标题</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="title" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.title }></input>
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
				<div>
					<button type="submit"
						class="btn btn-default btn-info pull-right m-t m-b m-r">完成</button>
				</div>
			</form>
		</section>
	</section>
</section>
