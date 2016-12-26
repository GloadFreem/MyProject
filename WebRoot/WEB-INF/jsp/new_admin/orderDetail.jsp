<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- /.aside 
-->
<section id="content">
	<section class="vbox">
		<header class="header bg-light bg-gradient b-b">
			<p>订单详情</p>
		</header>
		<section class="scrollable wrapper">
			<form action="editAuthentic.action" method="post"
				enctype="multipart/form-result">
				<div class="">
					<input name="contentId" value="${result.orderId}"
						style="display:none">
					<ul class="list-group gutter list-group-lg list-group-sp">
						<li class="list-group-item">
							<div class="clear">订单编号</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.orderCode }></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">订单类型</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.ordertype.name }></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">订单价格</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.service.servicetype.price} ></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">接单员工</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.service.member.name} ></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">预约时间</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.service.serviceDate} ></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">订单备注</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.service.content} ></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">订单状态</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.orderstatus.name} ></input>
							</div>
						</li>
						<li class="list-group-item">
							<div class="clear">订单日期</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
								<input name="desc" class="form-control alert-success"
									placeholder="请输入服务名称" value=${result.orderDate} ></input>
							</div>
						</li>

						<li class="list-group-item">
							<div class="clear">图片</div>
						</li>
						<li class="list-group-item">
							<div class="clear">
							<c:forEach var="item" items="${result.service.serviceimageses}">
								<img alt="备注图片" src="${item.url }"
									class="col-xs-2 m-b"> <input name="image"
									class="form-control alert-success" value="${item.url }"
									placeholder="请输入内容链接">
							</c:forEach>
								
							</div> <input name="file" id="input-1" type="file" class="file">
				</div>
				</li>


				<li class="list-group-item">
					<div class="clear">内容</div>
				</li>
				<li class="list-group-item">
					<div class="clear">
						<textarea name="desc" class="form-control alert-success"
							placeholder="请输入内容描述">${result.service.content }</textarea>
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
