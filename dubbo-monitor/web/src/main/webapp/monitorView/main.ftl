<#assign base=request.contextPath />

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>ants-monitor - dubbo监控中心</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${base}/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${base}/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
    <link href="${base}/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${base}/resources/assets/global/plugins/uniform/css/uniform.default.min.css" rel="stylesheet" type="text/css">
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${base}/resources/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/assets/main/css/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="${base}/resources/assets/main/css/light.css" rel="stylesheet" type="text/css"/>


    <link href="${base}/resources/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/resources/assets/main/css/amm.css" rel="stylesheet" type="text/css"/>





    <!-- BEGIN PAGE LEVEL STYLES -->
<#--<link href="${base}/resources/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/>-->
    <link href="${base}/resources/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>

    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="${base}/resources/assets/img/ants_32.ico" />

    <style type="text/css">
        @media (max-width: 992px){
            .page-content-wrapper,.page-content{
                max-width: none !important;
            }
        }
    </style>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo " style="overflow-x: hidden;">
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top" id="main_header">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner">
        <!-- BEGIN LOGO -->
        <div class="page-logo" style="">
            <span id="main-title" class="navbar-brand start" href="#" style="font-size: 50px;">
                <span style="font-size: 22px;font-weight: bold;color: #86B4DC;">Dubbo-监控中心</span>
            </span>
            <div class="menu-toggler sidebar-toggler">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN PAGE ACTIONS -->
        <!-- DOC: Remove "hide" class to enable the page header actions -->
        <div class="page-actions">
            <div class="btn-group">
                <button type="button" class="btn red-haze btn-sm " id="for_fun_btn" >
                    一键有惊喜
                </button>

            </div>
        </div>
        <!-- END PAGE ACTIONS -->
        <!-- BEGIN PAGE TOP -->
        <div class="page-top">

            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown dropdown-user dropdown-dark">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<span class="username username-hide-on-mobile" style="padding-top: 6px;">
						    最近变更服务时间：<span id="final_update_time"></span>
                        </span>
                        </a>
                    <#--<ul class="dropdown-menu dropdown-menu-default">-->
                    <#--<li>-->
                    <#--<a href="#">-->
                    <#--<i class="fa fa-refresh"></i>-->
                    <#--等待添加功能......-->
                    <#--</a>-->
                    <#--</li>-->

                    <#--</ul>-->
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->

                    <li class="dropdown dropdown-user dropdown-dark">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <img alt="" class="img-circle" style="background-color: #86B4DC;" src="${base}/resources/assets/img/ants_img.png">
                            <span class="username username-hide-on-mobile" id="user_name_show">
                            <#if name = 'null'>
                                匿名神秘人
                            <#else >
                            ${name}
                            </#if>
                            </span>
                        </a>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
            </div>
            <!-- END TOP NAVIGATION MENU -->
        </div>
        <!-- END PAGE TOP -->
    </div>
    <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container ">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar-wrapper ">
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <div class="page-sidebar navbar-collapse collapse">

            <ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <li class="start gotoIfreame active ">
                    <a href="#" data-url="/monitor/dash/index">
                        <i class="icon-home"></i>
                        <span class="title">首页</span>
                    </a>
                </li>
                <li class="gotoIfreame" id="appListButton">
                    <a href="#" data-url="/monitor/application/main">
                        <i class="icon-grid"></i>
                        <span class="title">Application-服务</span>
                    </a>
                </li>
                <li class="gotoIfreame" id="serviceButton">
                    <a href="#" data-url="/monitor/services/main">
                        <i class="icon-calendar"></i>
                        <span class="title">Service-方法</span>
                    </a>
                </li>
                <li class="gotoIfreame" id="hostButton">
                    <a href="#" data-url="/monitor/hosts/main">
                        <i class="icon-list"></i>
                        <span class="title">Hosts-主机</span>
                    </a>
                </li>

            </ul>
            <!-- END SIDEBAR MENU -->
        </div>
    </div>
    <!-- END SIDEBAR -->
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper ">
        <iframe width="100%" class="page-content" style="min-height: 196px;" frameborder="no" scrolling="auto" border="0" src="${base}/monitor/dash/index" id="mainIframe" name="mainIframe"></iframe>
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<div class="page-footer">
    <div class="page-footer-inner">
        2015 © 杭州 by 中西.
    </div>
    <div class="scroll-to-top" style="">
        <i class="icon-arrow-up"></i>
    </div>
</div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- static -->
<div id="confirm_modal" class="modal fade modal-scroll" data-backdrop="static" data-keyboard="false">
    <a href="#" class="model-close btn" style="color:#FFF;background-color:#c63927" data-dismiss="modal" aria-hidden="true">
        <i class="fa fa-times"></i>
    </a>

    <div class="modal-section">
        <div class="modal-body" style="overflow-x: hidden;">
            <p>
                别闹了，太忙了，这个功能还没做呢！！
            </p>
        </div>
        <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-default">好吧</button>
        <#--<button type="button" data-dismiss="modal" class="btn blue">Continue Task</button>-->
        </div>
    </div>

</div>

<div id="alert_model" class="modal fade modal-scroll" tabindex="-1"  aria-hidden="true">
    <a href="#" class="model-close btn" style="color:#FFF;background-color:#c63927" data-dismiss="modal" aria-hidden="true">
        <i class="fa fa-times"></i>
    </a>

    <div class="modal-section">
        <div class="modal-header">
            <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body" style="overflow-x: hidden;">
            hhh
        </div>
    </div>

</div>

<!-- BEGIN CORE PLUGINS -->
<script src="${base}/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>


<script src="${base}/resources/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/assets/main/js/metronic.js" type="text/javascript"></script>
<script src="${base}/resources/assets/main/js/layout.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core components
        Layout.init();
    });



</script>

<script src="${base}/resources/js/main.js" type="text/javascript"></script>


<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>