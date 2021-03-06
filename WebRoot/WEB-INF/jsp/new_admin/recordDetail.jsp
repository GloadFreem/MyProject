﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>考勤记录</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editAuthentic.action" method="post"
				enctype="multipart/form-result">
				<div class="">
					<input name="contentId" value="${result.recordId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">员工</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.user.name }></input>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">图片</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<img alt="${result.user.image }" src="${result.user.image }"
									class="col-xs-2 m-b"> <input name="image"
									class="form-control alert-success" value="${result.user.image }"
									placeholder="请输入内容链接">
							</div> <input name="file" id="input-1" type="file" class="file">
				</div>
				</li>

				<li class="list-group-item">
					<div class="clear">楼室</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<input name="desc" class="form-control alert-success"
							placeholder="请输入价格" value=${result.attendance.building.name }></input>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">内容</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="desc" class="form-control alert-success"
							placeholder="请输入内容描述">${result.attendance.content }</textarea>
					</div>
				</li>
				<li class="list-group-item">
					<div class="clear">要求时间</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<input name="desc" class="form-control alert-success"
							placeholder="请输入价格" value=${result.attendance.requireDate }></input>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">打卡时间</div>
				</li>
				<li class="list-group-item"><input name="url"
					class="form-control alert-success" value="${result.attendDate }"
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
