<#--app 主列表模板-->
<script type="text/template" id="main_app_list_template">
    {{#list}}
    <tr class="" data-appname="{{applicationName}}">
        <td>
            <span class="primary-link">{{applicationName}}</span>
        </td>
        <td>
            {{{categoryFunc}}}
        </td>
        <td style="text-align: left">
            {{#hostList}}
            {{hostString}}<br>
            {{/hostList}}
        </td>
        <td>
            {{organization}}
        </td>
        <td>
            {{owner}}
        </td>
        <td>
            {{{serviceSumFunc}}}
        </td>
        <td>
            {{{providerSumFunc}}}
        </td>
        <td>
            {{{consumerSumFunc}}}
        </td>

    </tr>
    {{/list}}
</script>

<#--警告框-->
<script type="text/template" id="alert_danger_template">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
        <strong>警告!</strong> {{appName}} 不存在提供的service，仅作为消费者！
    </div>
</script>

<#--排行榜-->
<script type="text/template" id="method_rank_template">
    <table class="table table-hover table-light">
        <thead>
        <th>排名</th>
        <th>method</th>
        <th>调用次数</th>
        <th>service</th>
        </thead>
        <tbody>
        {{#list}}
        <tr>
            <td>
                {{{indexFunc}}}
            </td>
            <td>{{methodName}}</td>
            <td>
                        <span class="primary-link">
                            {{usedNum}}
                        </span>
            </td>
            <td>{{serviceName}}</td>
        </tr>
        {{/list}}
        </tbody>
    </table>
</script>

<script type="text/template" id="service_table_tbody_template">
    {{#list}}
    <tr class="service" data-servicename="{{serviceName}}">
        <td>{{{indexFunc}}}</td>
        {{#wrongFunc}}
        <td>{{.}}</td>
        {{/wrongFunc}}
        <td style="text-align: left">
            {{serviceName}}
        </td>
        <td>{{owner}}</td>
        <td class="consumers">
            {{{consumersFunc}}}
        </td>
        <td>{{finalConsumerTime}}</td>
    </tr>
    <tr class="hidden method" >
        <td colspan="5" style="text-align: left">
            {{{methodFunc}}}
        </td>
    </tr>
    {{/list}}
</script>


<script type="text/template" id="service_tab_templates">
    <ul class="nav nav-tabs ">
        {{#list}}
        <li class="{{class}} service_tab"><a href="#service_{{status}}" data-toggle="tab" >{{name}}</a></li>
        {{/list}}
    </ul>
</script>
<script type="text/template" id="service_content_templates">
    <div class="tab-content">
        {{#list}}
        <div class="tab-pane service_content {{class}}" id="service_{{status}}">

            <div class="table-scrollable">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        {{#wrong}}
                        <th>错误原因</th>
                        {{/wrong}}
                        <th>service</th>
                        <th>负责人</th>
                        <th>有无被消费</th>
                        <th>最后消费时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    {{{tbody_html}}}
                    </tbody>
                </table>
            </div>

        </div>
        {{/list}}
    </div>
</script>

<script type="text/template" id="echarts_section_template">

    <div class="tabbable-custom ">
        <ul class="nav nav-tabs ">
            <li class="active"><a href="#tab_app_relation" data-toggle="tab" id="tab_app_relation_btn">服务对应关系</a></li>
            <li class=""><a href="#tab_app_data" data-toggle="tab" id="tab_app_data_btn">服务数据图表</a></li>
            <li class=""><a href="#tab_app_ranking" data-toggle="tab" id="tab_app_ranking_btn">服务排行榜单</a></li>
        </ul>


        <div class="tab-content">
            <div class="tab-pane active" id="tab_app_relation">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="portlet-title">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font-color hide"></i>
                                    <span class="caption-subject theme-font-color bold uppercase">依赖关系 统计</span>
                                </div>
                                <div class="actions">
                                    <div class="btn-group btn-group-devided" data-toggle="buttons">
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_force_options active">
                                            <input type="radio"  class="toggle" id="relation_force_day" data-value="Today">今天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_force_options ">
                                            <input type="radio"  class="toggle"  data-value="Yesterday">昨天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_force_options ">
                                            <input type="radio"  class="toggle"  data-value="Seven_DAY">前7天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_force_options">
                                            <input type="radio" class="toggle" data-value="Fifteen_DAT">前15天</label>
                                    <#--<label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_force_options">-->
                                    <#--<input type="radio" class="toggle"  data-value="Month">本月</label>-->
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" id="app_relation_force_body">

                                <div id="app_relation_force_echarts" style="height:500px"> </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="tab_app_data">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="portlet-title">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font-color hide"></i>
                                    <span class="caption-subject theme-font-color bold uppercase">提供的服务 统计</span>
                                </div>
                                <div class="actions">
                                    <div class="btn-group btn-group-devided" data-toggle="buttons">
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options active">
                                            <input type="radio"  class="toggle" id="relation_bar_day" data-value="Today">今天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options">
                                            <input type="radio"  class="toggle" data-value="Yesterday">昨天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options ">
                                            <input type="radio"  class="toggle" data-value="Seven_DAY">前7天</label>
                                        <label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options">
                                            <input type="radio" class="toggle" data-value="Fifteen_DAT">前15天</label>
                                    <#--<label class="btn btn-transparent grey-salsa btn-circle btn-sm relation_bar_options">-->
                                    <#--<input type="radio" class="toggle"  data-value="Month">本月</label>-->
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="row">
                                    <div class="col-lg-6 col-md-12 col-sm-12">
                                        <div id="app_relation_success_bar_echarts" style="height:400px">

                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 col-sm-12">
                                        <div id="app_relation_fail_bar_echarts" style="height:400px">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>
                </div>

            </div>
            <div class="tab-pane" id="tab_app_ranking">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet light ">
                            <div class="portlet-title">
                                <div class="caption caption-md">
                                    <i class="icon-bar-chart theme-font-color hide"></i>
                                    <span class="caption-subject theme-font-color bold uppercase">半月方法榜</span>
                                    <span class="caption-helper">前50名</span>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div id="ranking_body" class="ranking_body">

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