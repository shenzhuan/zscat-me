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
    <link href="${base}/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link href="${base}/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet"
          type="text/css">
    <link href="${base}/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css">
    <link href="${base}/resources/assets/global/plugins/uniform/css/uniform.default.min.css" rel="stylesheet"
          type="text/css">
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${base}/resources/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet"
          type="text/css"/>
    <link href="${base}/resources/assets/main/css/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="${base}/resources/assets/main/css/light.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link href="${base}/resources/assets/main/css/amm.css" rel="stylesheet" type="text/css"/>


    <link href="${base}/resources/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet"
          type="text/css"/>


    <style type="text/css">
        tr, th {
            text-align: center;
        }

        tbody > tr {
            cursor: pointer;
        }

        .services_title {
            color: #7FB0DA !important;
            font-family: "Ruthie", cursive;
            font-size: 71px !important;
            margin: 0;
            font-weight: 400;
            width: 100%;
        }

        .services_icon {
            color: #7FB0DA !important;
            font-size: 71px !important;
        }

        .fa-warning{
            color: red;
        }
         /*高亮*/
        .hlight{
            background: yellow;
        }

        /*按钮带边框*/
        .board-btn{
            border-radius: 25px !important;
            border-color: #A8B7D4;
            border-width: 2px;
            color: #93a2a9;
            font-family: "Open Sans", sans-serif;
            background: white;
        }
        .board-btn:hover,.board-btn.actived{
            font-family: "Open Sans", sans-serif;
            background-color: #A8B7D4;
            color: white;
        }

        /*方法*/

        .spanRow>.span{
            border: 1px solid #F5F5F5;
            border-radius: 5px !important;
            margin-top: 10px;
            margin-left: 30px;
            margin-bottom: 10px;
            cursor:pointer;
            color: green;
            padding: 4px 7px;
        }

        /*闪光效果*/
        .spanRow>.span.active,
        .spanRow>.span:hover{
            background-color: rgb(217, 83, 79);
            color: white;
            transition: border linear .2s,box-shadow linear .5s;
            -moz-transition: border linear .2s,-moz-box-shadow linear .5s;
            -webkit-transition: border linear .2s,-webkit-box-shadow linear .5s;
            outline: none;
            border-color: rgb(216, 5, 5);
            box-shadow: 0 0 8px rgb(252, 40, 40);
            -moz-box-shadow: 0 0 8px rgb(252, 40, 40);
            -webkit-box-shadow: 0 0 8px rgb(252, 40, 40);
        }
        /*标签*/
        .spanRow>.span > span{
            float: right;
        }
        .spanRow>.span.active > span,
        .spanRow>.span:hover > span{
            background-color: white;
            color: red;
        }

    </style>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo " style="overflow: hidden;">

<#--搜索框-->
<section id="search_section" class="">
    <div class="portlet light tasks-widget">
        <div class="portlet-body" id="app_main_body">
            <div class="row number-stats margin-bottom-30">
                <div class="col-lg-12 col-md-12 col-xs-12">
                    <div class="stat-center">
                        <div class="stat-number">
                            <div class="number services_icon">
                                <i class="icon-magnifier" style="font-size: 41px !important;"></i>
                            </div>
                            <div class="title services_title">
                                Service Search
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row margin-bottom-40">
                <div class="col-lg-2 col-md-1"></div>
                <div class="col-lg-8 col-md-10 col-xs-12">
                    <div class="input-group">
                        <input type="text" class="form-control" value="" placeholder="service名称" id="search_value">
                        <span class="input-group-btn">
                            <button class="btn bg-blue-madison" type="button" id="search_btn">
                                <i class="icon-magnifier"></i>
                                搜索
                            </button>
                        </span>

                    </div>
                </div>
            </div>

            <div class="row hidden" id="warning_tab_div">
                <div class="col-lg-1 col-md-1"></div>
                <div class="col-lg-10 col-md-10 col-xs-12">

                    <div class="panel-body">
                        <div class="tabbable-custom ">
                            <div class="panel panel-default">

                                <ul class="nav nav-tabs ">

                                    <li class="active wrong_tab">
                                        <a href="#tab_more_method" data-toggle="tab" >
                                            <i class="fa fa-warning"></i>
                                            <span>多方法</span>
                                        </a>
                                    </li>
                                    <li class="wrong_tab">
                                        <a href="#tab_more_app" data-toggle="tab">
                                            <i class="fa fa-warning" ></i>
                                            <span>多应用</span>
                                        </a>
                                    </li>
                                    <li class="wrong_tab">
                                        <a href="#wrong_host_content" data-toggle="tab" >
                                            <i class="fa fa-warning" ></i>
                                            <span>非法启动</span>
                                        </a>
                                    </li>
                                </ul>



                                <div class="tab-content" style="padding-left: 10px;">
                                    <div class="tab-pane active wrong_content" id="tab_more_method">
                                        无~~
                                    </div>
                                    <div class="tab-pane wrong_content" id="tab_more_app">
                                        无~~
                                    </div>
                                    <div class="tab-pane wrong_content" id="wrong_host_content">
                                        无~~
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-2 col-md-1"></div>
            </div>
        </div>
    </div>
</section>



<#--搜索的list-->
<section id="search_result_section" class="hidden">

    <div class="row margin-top-10">
        <div class="col-md-12">
            <div class="portlet light tasks-widget">
                <div class="portlet-title">
                    <span>筛选按钮:</span>
                    <div class="btn-group btn-group-devided">
                        <button class=" btn btn-sm board-btn " id="select_wrong_service_btn">
                            异常接口</button>
                        <button class="btn btn-sm board-btn " id="select_no_used_btn">
                            无消费者</button>
                    </div>
                    <div class="tools">
                        <a href="" class="collapse" data-original-title="" title="">
                        </a>
                        <a href="" class="reload" data-original-title="" title="">
                        </a>
                    </div>
                </div>
                <div class="portlet-body" id="app_main_body">
                    <div class="row number-stats margin-bottom-30">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="stat-center">
                                <div class="stat-number">
                                    <div class="title">
                                        Service Number
                                    </div>
                                    <div class="number" id="appSumNumber">
                                        0
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="table-scrollable table-scrollable-borderless">
                        <table class="table table-hover table-light">
                            <thead>
                            <tr class="uppercase">
                                <th>Service
                                </th>
                                <th>Status</th>
                                <th>
                                    所属应用
                                </th>
                                <th>负责团队</th>
                                <th>负责人</th>
                                <th>Consumers</th>
                                <th>最后消费时间</th>
                            </tr>
                            </thead>
                            <tbody id="main_service_tbody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="main_service_section" class="hidden">
    <div class="row margin-top-10">
        <div class="col-md-12">
            <div class="portlet light tasks-widget">
                <div class="portlet-body" >

                    <div class="row number-stats margin-bottom-30">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="stat-center">
                                <div class="stat-number">
                                    <div class="number " id="serviceName">
                                        <i class="fa fa-warning" ></i>
                                        xxx.xxx.xxx.xxx.xxx.xxxService:1.0.0.daily
                                    </div>
                                    <div class="title" id="serviceMethods">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="method">

                    </div>

                    <div id="echarts_div">

                    </div>


                </div>
            </div>
        </div>
    </div>
</section>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${base}/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>

<#--模板-->
<script src="/resources/assets/main/js/mustache.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/assets/main/js/metronic.js" type="text/javascript"></script>

<#--echarts-->
<script src="${base}/resources/echarts/dist/echarts.js" type="text/javascript"></script>

<#--自定义公用的js-->
<script src="${base}/resources/assets/main/js/amm.js" type="text/javascript"></script>

<#include "/monitorView/services/servicesIndex_template.ftl" />

<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Amm.init();
//        $('#test').tooltip()
    });


</script>

<script src="${base}/resources/js/services/services.js" type="text/javascript"></script>


<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>