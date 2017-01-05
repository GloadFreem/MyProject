<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>编辑消息</p>
		</header>
		<section class="scrollable wrapper">
			<form
				action="editMessage.action?menu=7&sortmenu=2&submenu=2&page=0&size=10"
				method="post" enctype="multipart/form-data">
				<div class="">
					<input name="contentId" value="${result.messageId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">标题</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="title" class="form-control alert-success"
									placeholder="请输入标题" value=${result.title }></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">推送对象</div>
						</li>
						<li class="list-group-item">
							<div class="input-group">
								<input name="name" id="name" type="text" class="form-control"
									placeholder="输入 关键字 进行搜索" value="${result.user.name }">
								<span class="input-group-btn">
									<button id="searchbtn" type="button"
										class="btn btn-info btn-icon">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</div>
							<div>
								<select class="selectpicker show-menu-arrow form-control"
									data-max-options="2" name='projectId' id='projectId'>
									<option value="0" selected=selected>全部</option>
								</select>
							</div>
				</div>


				</li>

				</li>



				<li class="list-group-item">
					<div class="clear">推送内容</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="content" class="form-control alert-success"
							placeholder="请输入内容">${result.content }</textarea>
					</div>
				</li>
				<li class="list-group-item">
					<div class="clear">是否已阅读</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<input name="flagStr" class="form-control alert-success"
							placeholder="是否已阅读"
							value=<c:choose>
										<c:when test="${result.readed}">
										 已阅读
									</c:when>
									<c:otherwise>未阅读</c:otherwise>
									</c:choose>></input>
					</div>
				</li>

				<li class="list-group-item">
					<div class="clear">通知时间</div>
				</li>
				<li class="list-group-item"><input name="time"
					class="form-control alert-success"
					value="${result.getPublicDate() }" placeholder="请输入支付方式">

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

<script type="text/javascript">
	$("#searchbtn").click(
			function() {
				$.ajax({
					url : "SearchUserByName.action",
					data : {
						"name" : $("input[name='name']").val(),
					},
					success : function(data) {
						selector = $("select[name='projectId']");
						selector.empty();
				

					 	data.data.forEach(function(e) {
							select = "<option value='"+e.userId+"'>" + e.name
									+ "</option>"
							selector.append(select);
						});
						selector.selectpicker('refresh');
					}
				});

			});

	$("#projectId").change(function() {
		$("input[name='name']").val($(this).find("option:selected").text());
	});
</script>
