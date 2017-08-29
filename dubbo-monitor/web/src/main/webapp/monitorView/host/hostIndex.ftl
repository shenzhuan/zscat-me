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
            background-color: #A8B7D4;
            color: white;
        }
    </style>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo " style="overflow: hidden;">

<#--搜索的list-->
<section id="search_result_section" class="">

    <div class="row margin-top-10">
        <div class="col-md-12">
            <div class="portlet light tasks-widget">
                <div class="portlet-title">
                    <div class="inputs" style="float: left;">
                        <div class="portlet-input input-inline input-small ">
                            <div class="input-icon right">
                                <i class="icon-magnifier"></i>
                                <input type="text" class="form-control form-control-solid" id="search_host_value"
                                       placeholder="ip search...">
                            </div>
                        </div>
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
                                        Host Number
                                    </div>
                                    <div class="number" id="hostSumNumber">
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
                                <th>
                                    注册ip
                                </th>
                                <th>映射ip</th>
                                <th>服务器</th>
                                <th>
                                    Status
                                    <select id="main_category_select">
                                        <option value="-1">不限</option>
                                        <option value="providers">提供者</option>
                                        <option value="consumers">消费者</option>
                                    </select>
                                </th>
                                <th>
                                    Providers
                                    <input type="text" id="providers_search">

                                </th>
                                <th>
                                    Consumers
                                    <input type="text" id="consumers_search">
                                </th>
                            </tr>
                            </thead>
                            <tbody id="main_host_tbody">

                            </tbody>
                        </table>
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


<#--自定义公用的js-->
<script src="${base}/resources/assets/main/js/amm.js" type="text/javascript"></script>

<#include "/monitorView/host/hostIndex_template.ftl" />

<script>
    jQuery(document).ready(function () {
        Metronic.init();
        Amm.init();
//        $('#test').tooltip()
    });


</script>

<script src="${base}/resources/js/host/host.js" type="text/javascript"></script>


<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>