<#--app 主列表模板-->
<script type="text/template" id="main_host_tr_template">
        <tr class="" data-appip="{{hostname}}-{{anotherIp}}" >
            <td>
                <span class="primary-link">{{hostname}}</span>
            </td>

            <td style="">
                {{anotherIp}}
            </td>
            <td style="">
                {{hostName}}
            </td>

            <td>
                {{{statusHtml}}}
            </td>
            <td style="text-align: left" class="providerHtml">
                {{{providersHtml}}}
            </td>
            <td style="text-align: left" class="consumersHtml">
                {{{consumersHtml}}}
            </td>
        </tr>
</script>


<script type="text/template" id="main_host_td_template">
    <div class="note note-info">

        {{#list}}
            {{.}}
            <br>
        {{/list}}
    </div>

</script>

<#--警告框-->
<script type="text/template" id="alert_danger_template">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
        <strong>警告!</strong> {{msg}}
    </div>
</script>