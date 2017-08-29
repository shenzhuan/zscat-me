<#--app 主列表模板-->
<script type="text/template" id="main_record_template">
    {{#list}}
        <tr class="" data-day="{{dayFunc}}"  data-class="" data-appname="{{appName}}" data-host="{{hostString}}">
            <td class="{{category}}">
                {{{statusFunc}}}
            </td>

            <td class="{{doType}}">
                {{{categoryFunc}}}
            </td>
            <td >
                <span class="primary-link">{{time}}</span>
            </td>

            <td>
                <span class="primary-link">{{organization}}</span>
            </td>

            <td style="text-align: left" class="appName" >
                {{appName}}
            </td>
            <td style="text-align: left" class="host">
                {{hostString}}
            </td>
        </tr>
    {{/list}}

</script>



<#--警告框-->
<script type="text/template" id="alert_danger_template">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
        <strong>警告!</strong> {{msg}}
    </div>
</script>


<#--日期-->
<script type="text/template" id="day_template">
    {{#list}}
        <button class="btn btn-sm board-btn" data-day="{{.}}">
            {{.}}
        </button>
    {{/list}}
</script>