<#--警告框-->
<script type="text/template" id="alert_danger_template">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
        <strong>警告!</strong> {{msg}}
    </div>
</script>


<#--警告的tab框-->
<script type="text/template" id="string_list_template">
    {{#list}}
    <a href="#" class="warning_service">{{.}}</a>
    <br>
    {{/list}}
</script>

<#--方法的展示-->
<script type="text/template" id="method_host_template">
    {{#list}}
        <div class="portlet light ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <span class="caption-subject theme-font-color bold ">{{{hostFunction}}}</span>
                </div>
            </div>
            <div class="portlet-body">
                {{{methodFunction}}}
            </div>
        </div>
    {{/list}}
</script>
<#--方法的展示-->
<script type="text/template" id="method_template">
    {{#listArray}}
        <div class="row spanRow">
            {{#list}}
            <span class="col-lg-3 col-md-3 col-xs-3 span method_class" data-method="{{.}}">
                {{.}}
                <span class="badge badge-danger"></span>
            </span>
            {{/list}}
        </div>
    {{/listArray}}
</script>



<#--service 搜索主结果-->
<script type="text/template" id="search_result_template">
    {{#list}}

    <tr class="" data-value="{{serviceName}}">
        <td style="text-align: left">
            <span class="primary-link">{{{serviceNameFunc}}}</span>
        </td>
        <td class="status_{{{statusOK}}}">
            {{{statusFunction}}}
        </td>
        <td>
            {{#ownerApp}}
            {{.}}<br>
            {{/ownerApp}}
        </td>
        <td>
            {{organization}}
        </td>
        <td>
            {{owner}}
        </td>
        <td class="used_{{{usedAppLength}}}">
            {{{usedAppSum}}}
        </td>
        <td>{{finalConsumerTime}}</td>

    </tr>
    {{/list}}
</script>





<#--方法的展示-->
<script type="text/template" id="echarts_section_template">


    <div class="tabbable-custom" id="tabbable-custom">
        <ul class="nav nav-tabs ">
            <li class="active"><a href="#tab_service_relation" data-toggle="tab" id="tab_service_relation_btn">对应关系</a>
            </li>
            <li class=""><a href="#tab_service_data" data-toggle="tab" id="tab_service_data_btn">数据图表</a></li>
        </ul>


        <div class="tab-content">
            <div class="tab-pane active" id="tab_service_relation">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="portlet-title">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font-color hide"></i>
                                    <span class="caption-subject theme-font-color bold uppercase">依赖关系 统计</span>
                                </div>
                            </div>
                            <div class="portlet-body" id="service_relation_force_body">

                                <div id="service_relation_force_echarts" style="height:500px"></div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="tab_service_data">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="portlet-title">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font-color hide"></i>
                                    <span class="caption-subject theme-font-color bold ">
                                        <span id="serviceNameForCharts"></span>
                                        :
                                        <span id="serviceAppForCharts"></span>
                                    </span>
                                    <br>
                                    <span class="caption-helper">TPS:每秒查询率（次/秒）</span>
                                    <span class="caption-helper">ART:每次平均耗时（毫秒/次）</span>
                                </div>

                                <div class="actions">
                                    <div class="btn-group btn-group-devided" data-toggle="buttons">
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options active">
                                            <input type="radio" class="toggle" id="relation_bar_day" data-value="Today">今天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options">
                                            <input type="radio" class="toggle" data-value="Yesterday">昨天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options ">
                                            <input type="radio" class="toggle" data-value="Seven_DAY">前7天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options">
                                            <input type="radio" class="toggle" data-value="Fifteen_DAT">前15天</label>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" id="bar_body">

                            <#--数据图表展示-->
                                <div class="row" data-value="{{.}}">
                                    <div class="col-lg-6 col-md-12 col-sm-12">
                                        <div id="tps_bar_echarts" style="height:400px">

                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 col-sm-12">
                                        <div id="art_bar_echarts" style="height:400px">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>

            </div>
        </div>
    </div>


</script>


