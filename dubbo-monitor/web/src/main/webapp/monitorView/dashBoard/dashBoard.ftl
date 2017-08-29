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

<#--时间选择-->
    <link href="${base}/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet"
          type="text/css"/>
    <!-- END THEME STYLES -->
    <link href="${base}/resources/assets/main/css/amm.css" rel="stylesheet" type="text/css"/>

<#--价格标签css-用于首页信息展示-->
    <#--<link id="style_color" href="${base}/resources/assets/global/css/pricing-table.css" rel="stylesheet"-->
          <#--type="text/css"/>-->

    <style type="text/css">
        .pricing:hover {
            cursor: pointer;
        }

        /*日期按钮带边框*/
        .board-btn {
            border-radius: 25px !important;
            margin-top: 10px;
            margin-bottom: 10px;
            border-color: #A8B7D4;
            border-width: 2px;
            color: #93a2a9;
            font-family: "Open Sans", sans-serif;
            background: white;
        }

        .board-btn:hover, .board-btn.actived {
            font-family: "Open Sans", sans-serif;
            background-color: #A8B7D4;
            color: white;
        }

        .special{
            font-family: "Ruthie", cursive;
        }

        .fail_app{
            color: #F98273;
            margin-left: 20px;
        }

        .success_app {
            color: #71AEE4;
            margin-left: 20px;
        }

        .badge{
            margin-left: 10px;
        }

        .btn-more{
            border: 1px solid #e9ecf3;
            background-color: white;
            color: #333333;
            height: 30px;
            width: 93px;
            padding: 0;
        }
        .btn-more:hover,.btn-more:active{
            background-color: #71AEE4;
            color: white;
        }
        /*===========首页3个显示栏css=================*/
        .show_row{
            background-color: white;
            border: 3px solid #e9ecf3;
            margin-bottom: 20px;
        }
        .show_row:hover {
            cursor: pointer;
        }
        /*内容*/
        .show_content_row{
            color: white;padding: 35px 0;
        }

        .show_help{
            display: block;
            margin-top: 5px;
            font-size: 10px;
        }

        .show_icon{
            padding-top: 15px;padding-right: 0;text-align: center;
        }
        .show_name {
            text-align: center;font-size: 20px;
        }
        @media (min-width: 992px){
            .show_icon{
                padding-top: 15px;padding-right: 0;text-align: right;
            }
            .show_name {
                text-align: left;font-size: 20px;
            }
        }
        .show_icon i{
            font-size: 40px;
        }


        /*文字*/
        .show_num_div {
            padding-top: 26px;
            text-align: center;
            color: #333333;
        }

        .show_main_num {
            font-size: 50px;
        }
        .small_span {
            /*font-size: 18px;*/
        }
    </style>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo " style="overflow: hidden;">
<#--标题-->
<div class="page-head">
    <div class="page-title" style="color: #333333">
        <h1>首页
            <small>本系统数据均采集同一zk的dubbo项目</small>
        </h1>
    </div>
</div>

<#--当前的服务，消费，提供和方法数-->
<div class="row margin-top-10" style="    padding-right: 20px;">
    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <div class="row show_row" id="applicationBtn">
            <div class="col-md-8">
                <div class="row show_content_row" style="background-color: #F98273; " >
                    <div class="col-md-4 show_icon" >
                        <i class="icon-grid" ></i>
                    </div>
                    <div class="col-md-8 show_name" >
                        Application
                        <span class="show_help">dubbo服务的主服务</span>
                    </div>
                </div>
            </div>

            <div class="col-md-4 show_num_div" >
                <span class="show_main_num">${appSum}</span>
                <span class="small_span">个</span>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <div class="row show_row" id="serviceBtn">
            <div class="col-md-8">
                <div class="row show_content_row" style="background-color: #71AEE4; " >
                    <div class="col-md-4 show_icon" >
                        <i class="icon-calendar" ></i>
                    </div>
                    <div class="col-md-8 show_name" >
                        Service
                        <span class="show_help">提供出的Service类</span>
                    </div>
                </div>
            </div>

            <div class="col-md-4 show_num_div" >
                <span class="show_main_num">${serviceSum}</span>
                <span class="small_span">个</span>
            </div>
        </div>
        <#--<div class="pricing hover-effect " id="serviceBtn">-->
            <#--<div class="pricing-head">-->
                <#--<h3>-->
                    <#--<i class="icon-calendar"></i>-->
                    <#--Service<span>所有提供出的Service类</span>-->
                <#--</h3>-->
                <#--<h4><i></i>${serviceSum}<i>个</i>-->
                <#--</h4>-->
            <#--</div>-->
        <#--</div>-->
    </div>
    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
        <div class="row show_row" id="hostBtn">
            <div class="col-md-8">
                <div class="row show_content_row" style="background-color: #26D9AB; " >
                    <div class="col-md-4 show_icon" >
                        <i class="icon-list" ></i>
                    </div>
                    <div class="col-md-8 show_name" >
                        Host
                        <span class="show_help">所有运行的主机</span>
                    </div>
                </div>
            </div>

            <div class="col-md-4 show_num_div" >
                <span class="show_main_num">${hostSum}</span>
                <span class="small_span">个</span>
            </div>
        </div>

    </div>

</div>

<#--所有app的依赖关系-->
<section>
    <div class="row">
        <div class="col-md-12">
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption">
                        <span class="caption-subject  bold uppercase">所有服务间的依赖关系</span>
                        <span class="caption-helper">圈越大，与其相关的app越多</span>

                    </div>
                    <div class="tools">

                        <a href="" class="collapse" data-original-title="" title="收起/展开">

                        </a>

                    </div>
                </div>
                <div class="portlet-body">

                    <div id="all_app_relation_force_echarts" style="height:600px">

                    </div>
                </div>
            </div>

        </div>
    </div>
</section>

<#--新增和减少的记录-->
<section id="recent_record_section">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-12">
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption caption-md">

                        <span class="caption-subject  bold uppercase" >
                            <i class="fa fa-volume-up" style="color: #F98273;"></i>断开的服务
                        </span>
                        <span class="caption-helper">仅展示前10条记录</span>
                    </div>
                    <button type="button" class="btn btn-more " style="float: right;" id="more_delete_btn">
                        <i class="icon-eye" ></i>
                        查看更多
                    </button>
                </div>
                <div class="portlet-body">
                    <div class="general-item-list">
                    <#list recentDeleteList as appBO>
                        <div class="item">

                            <div class="item-head">
                                <div class="item-details">
                                ${appBO.time}
                                    <span class="item-name fail_app">${appBO.appName}(${appBO.hostString})
                                </div>

                                <span class="item-status">

                                    <#if appBO.category = 'providers'>
                                        <span class="" >Providers</span>
                                    <#else >
                                        <span class="" >Consumers</span>
                                    </#if>
                                    <span class="badge badge-empty badge-danger"></span>
                                </span>
                            </div>

                        </div>
                    </#list>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-6 col-md-6 col-sm-12">
            <div class="portlet light">
                <div class="portlet-title">
                    <div class="caption caption-md">
                        <span class="caption-subject bold uppercase" >
                            <i class="fa fa-volume-up" style="color: #65A0D0;"></i>新增的服务
                        </span>
                        <span class="caption-helper">仅展示前10条记录</span>
                    </div>
                    <button type="button" class="btn btn-more " style="float: right;" id="more_insert_btn">
                        <i class="icon-eye" ></i>
                        查看更多
                    </button>
                </div>
                <div class="portlet-body">

                    <div class="general-item-list">
                    <#list recentInsertList as appBO>
                        <div class="item">
                            <div class="item-head">
                                <div class="item-details">
                                ${appBO.time}
                                    <span class="item-name success_app">${appBO.appName}(${appBO.hostString})
                                </div>

                                <span class="item-status">

                                     <#if appBO.category = 'providers'>
                                         <span class="" >Providers</span>
                                     <#else >
                                         <span class="" >Consumers</span>
                                     </#if>
                                    <span class="badge badge-empty badge-info"></span>
                                </span>
                            </div>

                        </div>
                    </#list>


                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<section id="wrong_section">

</section>
<#--更多的记录-->
<input type="hidden" id="day_value">
<section id="more_record_section" class="hidden">
    <div class="row margin-top-10">
        <div class="col-md-12">
            <div class="portlet light tasks-widget">
                <div class="portlet-title" id="record_title">
                    <div class="row">
                        <div class="inputs" style="float: none;">
                            <span style="font-size: 13px;color: #333333"> 选择月份：</span>
                            <div class="portlet-input input-inline input-small ">
                                <div class="input-icon right">
                                    <i class="fa fa-clock-o"></i>
                                    <input type="text" class="form-control form-control-solid" id="month_choose_input"
                                           value="${nowMonth}">
                                </div>
                            </div>
                            <a href="#" class="btn btn-icon-only btn-circle red-haze" id="see_day_btn">
                                <i class="fa fa-calendar"></i>
                            </a>
                        </div>
                    </div>

                    <div class="" id="day_main_div">
                        <div class="row" style="margin-top: 10px;margin-bottom: 5px;">
                            <div class="btn-group btn-group-devided" id="day_div">

                            </div>
                        </div>

                        <#--<div class="row" style="margin-bottom: 5px;text-align: center">-->
                            <#--<button type="button" class="btn btn-circle blue-madison" id="resert_day_btn">清空筛选</button>-->
                            <#--<button type="button" class="btn btn-circle blue-madison" id="select_all_day_btn">全部选择</button>-->
                        <#--</div>-->


                    </div>

                </div>
                <div class="portlet-body" id="app_main_body">
                    <ul class="pager">
                        <li class="previous disabled pre_li" >
                            <a href="#">
                                ← 上一页 </a>
                        </li>
                        <li class="center">
                            当前页数
                            <input type="text" id="now_page_size" value="1" class="form-control input-inline"  style="width: 5%;">/
                            总页数：
                            <span id="all_page_sum">12</span>
                        </li>
                        <li class="next disabled next_li" >
                            <a href="#" >
                                下一页 → </a>
                        </li>
                    </ul>

                    <div class="row number-stats margin-bottom-30">
                        <div class="col-md-6 col-sm-6 col-xs-6">
                            <div class="stat-left">
                                <div class="stat-number">
                                    <div class="title">
                                        总数
                                    </div>
                                    <div class="number" id="recordSumNumber">
                                        0
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6 col-sm-6 col-xs-6">
                            <div class="stat-right">
                                <div class="stat-number">
                                    <div class="title">
                                        每页
                                    </div>
                                    <div class="number" contenteditable id="max_sum">
                                        100
                                        <#--<input type="text" id="max_sum" value="100"  class="form-control input-inline" style="width: 40%;">-->
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="table-scrollable table-scrollable-borderless" >
                        <table class="table table-hover table-light">
                            <thead>
                            <tr class="uppercase">

                                <th>状态
                                    <select id="main_status_select">
                                        <option value="-1">不限</option>
                                        <option value="insert">新增</option>
                                        <option value="delete">减少</option>
                                    </select>
                                </th>
                                <th>角色
                                    <select id="main_category_select">
                                        <option value="-1">不限</option>
                                        <option value="providers">仅提供者</option>
                                        <option value="consumers">仅消费者</option>
                                    </select>
                                </th>
                                <th>时间</th>
                                <th>负责团队</th>
                                <th>服务名
                                    <input type="text" id="app_search_input">
                                </th>
                                <th>host
                                    <input type="text" id="host_search_input">
                                </th>
                            </tr>
                            </thead>
                            <tbody id="main_record_tbody">

                            </tbody>
                        </table>



                    </div>


                    <ul class="pager">
                        <li class="previous disabled pre_li" id="">
                            <a href="#">
                                ← 上一页 </a>
                        </li>
                        <#--<li class="center">-->
                            <#--当前页数-->
                            <#--<input type="text"  value="1" class="now_page_size form-control input-inline"  style="width: 5%;">/-->
                            <#--总页数：-->
                            <#--<span class="all_page_sum">12</span>-->
                        <#--</li>-->
                        <li class="next disabled next_li" >
                            <a href="#" >
                                下一页 → </a>
                        </li>
                    </ul>
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

<!-- END PAGE LEVEL SCRIPTS -->
<script src="${base}/resources/assets/main/js/metronic.js" type="text/javascript"></script>

<#--时间选择-->
<script src="${base}/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"
        type="text/javascript"></script>
<script src="${base}/resources/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"
        type="text/javascript"></script>
<#--模板-->
<script src="/resources/assets/main/js/mustache.js" type="text/javascript"></script>
<#--自定义公用的js-->
<script src="${base}/resources/assets/main/js/amm.js" type="text/javascript"></script>

<#--echarts-->
<script src="${base}/resources/echarts/dist/echarts.js" type="text/javascript"></script>

<#include "/monitorView/dashBoard/dashBoard_template.ftl" />
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core components
        Amm.init();
    });


</script>

<script src="${base}/resources/js/dashBoard.js" type="text/javascript"></script>


<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>