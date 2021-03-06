﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="vbox">
	<header class="bg-dark dk header navbar navbar-fixed-top-xs">
		<div class="navbar-header aside-md">
			<a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen"
				data-target="#nav"> <i class="fa fa-bars"></i>
			</a> <a href="#" class="navbar-brand" data-toggle="fullscreen"><img
				src="images/logo.png" class="m-r-sm">城投逸园</a> <a
				class="btn btn-link visible-xs" data-toggle="dropdown"
				data-target=".nav-user"> <i class="fa fa-cog"></i>
			</a>
		</div>
		<ul class="nav navbar-nav navbar-right hidden-xs nav-user">
			<li class="hidden-xs"><a href="#" class="dropdown-toggle dk"
				data-toggle="dropdown"> <i class="fa fa-bell"></i> <span
					class="badge badge-sm up bg-danger m-l-n-sm count">2</span>
			</a>
				<section class="dropdown-menu aside-xl">
					<section class="panel bg-white">
						<header class="panel-heading b-light bg-light">
							<strong>您有 <span class="count">2</span> 条通知
							</strong>
						</header>
						<div class="list-group list-group-alt animated fadeInRight">
							<a href="#" class="media list-group-item"> <span
								class="pull-left thumb-sm"> <img src="images/avatar.jpg"
									alt="John said" class="img-circle">
							</span> <span class="media-body block m-b-none"> 上报事件 <br> <small class="text-muted">10 分钟前</small>
							</span>
							</a> <a href="#" class="media list-group-item"> <span
								class="media-body block m-b-none"> 1条订单更新<br> <small
									class="text-muted">1 小时前</small>
							</span>
							</a>
						</div>
						<footer class="panel-footer text-sm">
							<a href="#" class="pull-right"><i class="fa fa-cog"></i></a> <a
								href="#notes" data-toggle="class:show animated fadeInRight">查看全部消息</a>
						</footer>
					</section>
				</section></li>
			<li class="dropdown hidden-xs"><a href="#"
				class="dropdown-toggle dker" data-toggle="dropdown"><i
					class="fa fa-fw fa-search"></i></a>
				<section class="dropdown-menu aside-xl animated fadeInUp">
					<section class="panel bg-white">
						<form role="search">
							<div class="form-group wrapper m-b-none">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="输入 关键字 进行搜索"> <span
										class="input-group-btn">
										<button type="submit" class="btn btn-info btn-icon">
											<i class="fa fa-search"></i>
										</button>
									</span>
								</div>
							</div>
						</form>
					</section>
				</section></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <span class="thumb-sm avatar pull-left">
						<img src="images/avatar.jpg">
				</span>城投逸园 <b class="caret"></b>
			</a>
				<ul class="dropdown-menu animated fadeInRight">
					<span class="arrow top"></span>
					<li><a href="#">设置</a></li>
					<li><a href="profile.html">简介</a></li>
					<li><a href="#"> <span class="badge bg-danger pull-right">3</span>
							消息
					</a></li>
					<li><a href="docs.html">帮助</a></li>
					<li class="divider"></li>
					<li><a href="modal.lockme.html" data-toggle="ajaxModal">注销</a>
					</li>
				</ul></li>
		</ul>
	</header>
	<section>
		<section class="hbox stretch">
			<!-- .aside -->
			<aside class="bg-dark lter aside-md hidden-print" id="nav">
				<section class="vbox">
					<section class="w-f scrollable">
						<div class="slim-scroll" data-height="auto"
							data-disable-fade-out="true" data-distance="0" data-size="5px"
							data-color="#333333">
							<!-- nav -->
							<nav class="nav-primary hidden-xs">
								<ul class="nav">
									<c:choose>
										<c:when test="${menu==1}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>


									<a href="#layout"> <i class="fa fa-columns icon"> <b
											class="bg-warning"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>用户管理</span>
									</a>
									<ul class="nav lt">

										<c:choose>
											<c:when test="${submenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="userList.action?&menu=1&submenu=1&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>用户列表</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>

										<a
											href="authenticList.action?&menu=1&submenu=2&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>认证审核</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="memberList.action?&menu=1&submenu=3&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>员工管理</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==4}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="userRankList.action?&menu=1&submenu=4&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>排行榜</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==5}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="userList.action?&menu=1&submenu=5&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>黑名单</span>
										</a>
										</li>
									</ul>
									</li>

									<c:choose>
										<c:when test="${menu==2}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#uikit"> <i class="fa fa-flask icon"> <b
											class="bg-success"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>服务管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>

										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>服务管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1 && sortmenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>

											<a
												href="serviceList.action?&menu=2&sortmenu=1&submenu=1&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>服务列表</span>
											</a>
											</li>

											<c:choose>
												<c:when test="${submenu==2&& sortmenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="serviceDetail.action?&menu=2&sortmenu=1&submenu=2&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>添加服务</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>

										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>费用缴纳</span>
										</a>
										<ul class="nav bg">

											<c:choose>
												<c:when test="${submenu==1&& sortmenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="propertyChargesList.action?&menu=2&sortmenu=2&submenu=1&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>物业费缴纳订单</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2&& sortmenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="chargeDetail.action?&menu=2&sortmenu=2&submenu=2&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>录入物业费</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2&& sortmenu==3}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="importChargeOrder.action?&menu=2&sortmenu=2&submenu=3&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>一键导入物业费账单</span>
											</a>
											</li>
										</ul>
										</li>
										<!--<li > <a href="#form" > <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> <span>Form</span> </a>-->
										<!--<ul class="nav bg">-->
										<!--<li > <a href="form-elements.html" > <i class="fa fa-angle-right"></i> <span>Form elements</span> </a> </li>-->
										<!--<li > <a href="form-validation.html" > <i class="fa fa-angle-right"></i> <span>Form validation</span> </a> </li>-->
										<!--<li > <a href="form-wizard.html" > <i class="fa fa-angle-right"></i> <span>Form wizard</span> </a> </li>-->
										<!--</ul>-->
										<!--</li>-->
									</ul>
									</li>

									<c:choose>
										<c:when test="${menu==3}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>

									<a href="#uikit"> <i class="fa fa-flask icon"> <b
											class="bg-success"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>订单管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"><b class="badge bg-info pull-right">369</b> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>预约订单</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="orderList.action?&menu=3&sortmenu=1&submenu=1&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>订单列表</span>
											</a>
						<%-- 					</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="orderDetail.action?&menu=3&sortmenu=1&submenu=2&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>添加订单</span>
											</a>
											</li> --%>
											<c:choose>
												<c:when test="${submenu==3}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="memberOrderList.action?&menu=3&sortmenu=1&submenu=3&page=0&size=10">
												<b class="badge bg-info pull-right">369</b> <i
												class="fa fa-angle-right"></i> <span>员工订单</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>订单统计</span>
										</a>
										<ul class="nav bg">

											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="orderList.action?&menu=3&sortmenu=2&submenu=1&page=0&size=10">
												<i class="fa fa-angle-right"></i> <span>当日订单</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a
												href="orderList.action?&menu=3&sortmenu=2&submenu=2&page=0&size=10">
												<b class="badge bg-info pull-right">369</b> <i
												class="fa fa-angle-right"></i> <span>历史订单</span>
											</a>
											</li>
										</ul>
										</li>
										<!--<li > <a href="#form" > <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> <span>Form</span> </a>-->
										<!--<ul class="nav bg">-->
										<!--<li > <a href="form-elements.html" > <i class="fa fa-angle-right"></i> <span>Form elements</span> </a> </li>-->
										<!--<li > <a href="form-validation.html" > <i class="fa fa-angle-right"></i> <span>Form validation</span> </a> </li>-->
										<!--<li > <a href="form-wizard.html" > <i class="fa fa-angle-right"></i> <span>Form wizard</span> </a> </li>-->
										<!--</ul>-->
										<!--</li>-->
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==4}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-file-text icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>事件管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${submenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>

										<a
											href="eventList.action?&menu=4&sortmenu=0&submenu=1&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>事件列表</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>

										<a
											href="eventList.action?&menu=4&sortmenu=0&submenu=2&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>上报事件</span>
										</a>
										</li>

										<c:choose>
											<c:when test="${submenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a
											href="eventList.action?&menu=4&sortmenu=0&submenu=3&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>业主投诉</span>
										</a>
										</li>
									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==5}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>圈子管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${submenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a
											href="feelingList.action?&menu=5&sortmenu=0&submenu=1&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>圈子列表</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a
											href="feelingDetail.action?&menu=5&sortmenu=0&submenu=2&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>发布圈子</span>
										</a>
										</li>
										<c:choose>
											<c:when test="${submenu==3}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a
											href="feelingList.action?&menu=5&sortmenu=0&submenu=3&page=0&size=10">
											<i class="fa fa-angle-right"></i> <span>黑名单</span>
										</a>
										</li>
									</ul>
									</li>

									<c:choose>
										<c:when test="${menu==6}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>考勤任务</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>任务管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="taskList.action?&menu=6&sortmenu=1&submenu=1&page=0&size=10"> <i class="fa fa-angle-right"></i>
												<span>任务列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="taskDetail.action?&menu=6&sortmenu=1&submenu=2&page=0&size=10"> <i class="fa fa-angle-right"></i>
												<span>任务分配</span>
											</a>
											</li>
										</ul>
										</li>

										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>考勤管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="employerTask.action?&menu=6&sortmenu=2&submenu=1&page=0&size=10"> <i
												class="fa fa-angle-right"></i> <span>员工考勤</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="workResult.action?&menu=6&sortmenu=2&submenu=2&page=0&size=10"> <i class="fa fa-angle-right"></i>
												<span>绩效管理</span>
											</a>
											</li>
										</ul>
										</li>

									</ul>
									</li>
									<c:choose>
										<c:when test="${menu==7}">
											<li class="active">
										</c:when>
										<c:otherwise>
											<li>
										</c:otherwise>
									</c:choose>
									<a href="#pages"> <i class="fa fa-envelope-o icon"> <b
											class="bg-primary"></b>
									</i> <span class="pull-right"> <i
											class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i>
									</span> <span>消息管理</span>
									</a>
									<ul class="nav lt">
										<c:choose>
											<c:when test="${sortmenu==1}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>公告管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="announceList.action?menu=7&sortmenu=1&submenu=1&page=0&size=10"> <i
												class="fa fa-angle-right"></i> <span>公告列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="announceDetail.action?&menu=7&sortmenu=1&submenu=2&page=0&size=10"> <i
												class="fa fa-angle-right"></i> <span>发布公告</span>
											</a>
											</li>
										</ul>
										</li>
										<c:choose>
											<c:when test="${sortmenu==2}">
												<li class="active">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise>
										</c:choose>
										<a href="#table"> <i class="fa fa-angle-down text"></i> <i
											class="fa fa-angle-up text-active"></i> <span>消息管理</span>
										</a>
										<ul class="nav bg">
											<c:choose>
												<c:when test="${submenu==1}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="messageList.action?&menu=7&sortmenu=2&submenu=1&page=0&size=10"> <i
												class="fa fa-angle-right"></i> <span>消息列表</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==2}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="messageDetail.action?&menu=7&sortmenu=2&submenu=2&page=0&size=10"> <i
												class="fa fa-angle-right"></i> <span>添加消息</span>
											</a>
											</li>
											<c:choose>
												<c:when test="${submenu==3}">
													<li class="active">
												</c:when>
												<c:otherwise>
													<li>
												</c:otherwise>
											</c:choose>
											<a href="PushMessage.action?&menu=7&sortmenu=2&submenu=3&page=0&size=10"> <i
												class="fa fa-angle-right"></i> <span>推送消息</span>
											</a>
											</li>
										</ul>
										</li>
									</ul>
									</li>
								</ul>
							</nav>
							<!-- / nav -->
						</div>
					</section>
				</section>
			</aside>
			<!-- /.aside -->
			<jsp:include page="${content}.jsp"></jsp:include>
		</section>
	</section>
</section>
