var headerUrl = 'http://'+window.location.host;

// 纪录每个月份的数据
var record_map = [];

//是否来自more的点击
var is_from_more = false;

$(function(){
    //首页3个展示栏初始化
    showPricing();

    buttonClick();

    datePickInit();

    pageChoose();

    initeCharts();


});

function showPricing(){
    $(".show_row").mouseover(function(){
        var color = $(this).find(".show_content_row").css("background-color");

        $(this).delay("slow").css("border","3px solid "+color);
        $(this).delay("slow").find(".show_num_div").css("color",color);
    });

    $(".show_row").mouseout(function(){
        var color = $(this).find(".show_content_row").css("background-color");

        $(this).css("border","3px solid #e9ecf3");
        $(this).find(".show_num_div").css("color","black");
    })
}

function pageChoose(){

    $("#max_sum").unbind("keypress").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            var value = Number($(this).html().replace(/[\D]/g, '').replace(" ",""));
            if(value == 0){
                value = '100';
            }
            $(this).html(value);
            $("#now_page_size").val("1");

            is_from_more = false;
            getMoreRecord();
            return false;
        }
    });

    $('#max_sum').keyup(function(){
        //非数字都为空格
        var value = $(this).html().replace(/[\D]/g, '').replace(" ","");
        $(this).html(value);
        return false;
    });

    $("#now_page_size").unbind("keypress").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            var value = $(this).val().replace(/[\D]/g, '').replace(" ","");
            if(value == '0' || value == ''){
                value = '1';
            }
            $(this).val(value);

            var max_page = $("#all_page_sum").text();
            if(Number(value) > max_page){
                $(this).val(max_page);
            }
            is_from_more = false;
            getMoreRecord();
            return false;
        }
    });

    $('#now_page_size').keyup(function(){
        //非数字都为空格
        var value = $(this).val().replace(/[\D]/g, '').replace(" ","");
        $(this).val(value);
        return false;
    });

    $("#all_page_sum").click(function(){
        var max_page = $("#all_page_sum").text();
        $("#now_page_size").val(max_page);
        is_from_more = false;
        getMoreRecord();
        return false;
    });

    $(".pre_li").click(function(){
        if($(this).hasClass("disabled")){
            return false;
        }
        var now_page = $("#now_page_size").val();
        var page = Number(now_page) - 1;
        $("#now_page_size").val(page);
        is_from_more = false;
        getMoreRecord();
        return false;
    });

    $(".next_li").click(function(){
        if($(this).hasClass("disabled")){
            return false;
        }
        var now_page = $("#now_page_size").val();
        var page = Number(now_page) + 1;
        $("#now_page_size").val(page);
        is_from_more = false;
        getMoreRecord();
        return false;
    });


}

function datePickInit(){
    $("#month_choose_input").datepicker({
        language: 'zh-CN',
        format: 'yyyy-mm',
        minViewMode:'months',
        autoclose:true
    });
}


function buttonClick(){


    $('#applicationBtn').click(function(){
        var appListButton = parent.document.getElementById('appListButton');
        if(appListButton != null){
            $(appListButton).trigger('click');
            return false;
        }
    });
    $('#serviceBtn').click(function(){
        var appListButton = parent.document.getElementById('serviceButton');
        if(appListButton != null){
            $(appListButton).trigger('click');
            return false;
        }
    });
    $('#hostBtn').click(function(){
        var appListButton = parent.document.getElementById('hostButton');
        if(appListButton != null){
            $(appListButton).trigger('click');
            return false;
        }
    });

    $("#more_delete_btn").click(function(){
        $('#more_insert_btn').trigger('click');
    });

    $("#more_insert_btn").click(function(){
        //var mydate = new Date();
        //var nowMonth =  mydate.getFullYear() + "-"+(mydate.getMonth()+1);
        //$("#month_choose_input").val(nowMonth);

        $("#now_page_size").val("1");
        is_from_more = true;
        getMoreRecord();


        dataChange();

    });

    $("#see_day_btn").click(function(){
       $("#day_main_div").toggleClass("hidden");
        return false;
    });
}



function dataChange(){
    $("#month_choose_input").datepicker('update').unbind("changeDate").on('changeDate', function(ev){
        is_from_more = false;
        $("#day_value").val("");
        getMoreRecord();
    });
}


function getMoreRecord(){
    var nowMonth = $("#month_choose_input").val();
    var pageIndex = $("#now_page_size").val().trim();
    var pageSize = $("#max_sum").html().trim();

    var key =  nowMonth+"-"+pageIndex+"-"+pageSize+"-";
    var day = $("#day_value").val();
    if(day == ''){
        day = undefined;
    }
    if(day != undefined){
        key += day;
    }
    var resultVO = record_map[key];
    if(resultVO == undefined) {
        var params = {'month': nowMonth,'pageIndex':pageIndex,'pageSize':pageSize};
        if(day != undefined){
            params['day']=day;
        }
        $.get(headerUrl + "/monitor/dash/getMonthChangeData", params, function (resultVO) {
                record_map[key] = resultVO;

                resultTOTbody(resultVO);
            }
        );
    }else{
        resultTOTbody(resultVO);
    }
}

//结果处理为页面记录展示
function resultTOTbody(resultVO){

    //初始化所有选项
    $("#main_status_select").val("-1");
    $("#main_category_select").val("-1");
    $("#app_search_input").val("");
    $("#host_search_input").val("");
   if(resultVO.success) {
       var resultMap = resultVO.data;
       var loadingEL = $('#more_record_section');
       Metronic.blockUI(loadingEL);

       var day = resultMap['day'];
       var daySet = resultMap['daySet'];
       var list = resultMap['list'];

       var allSum = resultMap['sum'];

       if (day != undefined) {
           $("#day_value").val(day);
       }

       var insertStatusHtml = '<span class="badge badge-info">新增</span>';
       var deleteStatusHtml = '<span class="badge badge-danger">减少</span>';
       var categoryHtml = '<span class="badge badge-success">CATEGORY</span>';
       var map ={
           list:list,
           dayFunc:function(){
               var time = this.time;
               var timeArray = time.split(" ")[0].split("-");
               var day = timeArray[2];
               return day;
           },
           statusFunc: function () {
               var doType = this.doType;
               var html = '';
               if(doType == 'insert'){
                   html = insertStatusHtml;
               }else{
                   html = deleteStatusHtml;
               }
               return html;
           },
           categoryFunc:function(){
               var category = this.category;
               var status_html = categoryHtml.replace("CATEGORY",category);
               return status_html;
           }
       };
       var html = Mustache.render($('#main_record_template').html(), map);
       //主体内容
       $("#main_record_tbody").html(html);
       //数量
       $('#recordSumNumber').html(allSum);
       //总页数
       var all_page = Math.ceil(allSum/Number($("#max_sum").html().trim()));
       $('#all_page_sum').html(all_page);

       //判断上下页是否可点击
       var now_page = Number($("#now_page_size").val().trim());
       if(now_page == 1){
           $(".pre_li").addClass("disabled");
       }else{
           $(".pre_li").removeClass("disabled");
       }
       if(now_page >= all_page){
           $(".next_li").addClass("disabled");
       }else{
           $(".next_li").removeClass("disabled");
       }

       //日期
       if(daySet != undefined) {
           var day_html = Mustache.render($('#day_template').html(), {list: daySet});
           $("#day_div").html(day_html);
           $('.board-btn').first().addClass("actived");
       }


       table_click();
       $("#wrong_section").html("");
   }else{
       var html = Mustache.render($('#alert_danger_template').html(), {'msg': "出错啦："+ $("#month_choose_input").val()+"该日期无数据"});
       $("#day_div").html("");
       $("#main_record_tbody").html("");
       $("#recordSumNumber").html("0");
       $("#all_page_sum").html("1");
       $(".pre_li").addClass("disabled");
       $(".next_li").addClass("disabled");
       $("#wrong_section").html(html);
   }

    $('#more_record_section').removeClass("hidden");

    Amm.changeiframeParentHeight();


    Metronic.unblockUI(loadingEL);

    // 滚动到指定位置
    if(is_from_more) {
        var scroll_offset = $("#more_record_section").offset();
        Amm.animate(scroll_offset);
    }
}

function table_click(){
    //$("#resert_day_btn").unbind("click").click(function () {
    //    $(".board-btn").removeClass("actived");
    //    chooseDay();
    //});
    //
    //$("#select_all_day_btn").unbind("click").click(function () {
    //    $(".board-btn").addClass("actived");
    //    chooseDay();
    //});

    $(".board-btn").unbind("click").click(function () {
        if($(this).hasClass("actived")){
            return false;
        }else{
            $(".board-btn").removeClass("actived");
            $(this).addClass("actived");
            var day = $(this).data("day") ;
            $("#day_value").val(day);
            $("#now_page_size").val("1");
            is_from_more = false;
            getMoreRecord();
        }
    });

    $("#main_status_select").unbind("change").change(function () {
        filterTable();
    });
    $("#main_category_select").unbind("change").change(function () {
        filterTable();
    });
    $("#app_search_input").unbind("keypress").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            filterTable();
        }
    });
    $("#host_search_input").unbind("keypress").bind('keypress', function (event) {
        if (event.keyCode == "13") {
            filterTable();
        }
    });



}



//根据条件过滤数据
function filterTable(){
    var main_status_select = $("#main_status_select").val();
    var search_category = $("#main_category_select").val();
    var app_search_input = $("#app_search_input").val().toUpperCase();
    var host_search_input = $("#host_search_input").val().toUpperCase();

    var sum_num = 0;
    //设置当前所有筛选的日期结果显示
    $.each($("#main_record_tbody > tr"), function (i, tr_object) {
        var tr_class= $(tr_object).data("class");
        if(tr_class ==''){
            $(tr_object).removeClass("hidden");
            sum_num += 1;
        }
    });

    if(main_status_select != "-1"){
        $.each($("#main_record_tbody > tr"), function (i, tr_object) {
            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var insert_value = $(tr_object).find(".insert").html();
            var delete_value = $(tr_object).find(".delete").html();
            if (main_status_select == "insert") {
                if (insert_value == undefined) {
                    $(tr_object).addClass("hidden");
                    sum_num -= 1;
                }
            }
            if (main_status_select == "delete") {
                if (delete_value == undefined) {
                    $(tr_object).addClass("hidden");
                }
            }
        });
    }

    if(search_category != "-1"){
        $.each($("#main_record_tbody > tr"), function (i, tr_object) {
            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var provider_value = $(tr_object).find(".providers").html();
            var consumer_value = $(tr_object).find(".consumers").html();
            if (search_category == "providers") {
                if (provider_value == undefined) {
                    $(tr_object).addClass("hidden");

                }
            }
            if (search_category == "consumers") {
                if (consumer_value == undefined) {
                    $(tr_object).addClass("hidden");

                }
            }
        });
    }


    //app搜索
    if (app_search_input != "") {
        $.each($("#main_record_tbody > tr"), function (i, tr_object) {
            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var appname = $(this).data("appname").toUpperCase();
            if (appname.indexOf(app_search_input) == -1) {
                $(tr_object).addClass("hidden");

            }
        });
    }
    //host搜索
    if (host_search_input != "") {
        $.each($("#main_record_tbody > tr"), function (i, tr_object) {
            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var host = $(this).data("host").toUpperCase();
            if (host.indexOf(host_search_input) == -1) {
                $(tr_object).addClass("hidden");
            }
        });
    }


    Amm.changeiframeParentHeight();

}





/**copy from app.js**/

function initeCharts() {

    require.config({
        paths: {
            echarts: headerUrl + '/resources/echarts/dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
            'echarts/chart/bar',
            'echarts/chart/chord',
            'echarts/chart/force'
        ],
        function (ec) {
            allAPPRelationForceChart(ec);

        }
    )

}
//所有app之间的依赖关系
function allAPPRelationForceChart(ec) {
    $.ajax({
        url: headerUrl + "/monitor/application/getAllAPPAndRelation",
        success: function (resultVO) {
            if (!resultVO.success) {
                $("#all_app_relation_force_echarts").html("加载失败~！原因：" + resultVO.msg);

            } else {

                if (ec == undefined) {
                    ec = echartsEc;
                }
                var charts_id = 'all_app_relation_force_echarts';
                var myChart = ec.init(document.getElementById(charts_id));
                myChart.showLoading({
                    text: 'Loading...',
                    effect: 'bubble',
                    textStyle: {
                        fontSize: 20
                    }
                });


                allAppResutMap = resultVO.data;

                var appMap = allAppResutMap.allApp;
                var nodesList = [];
                var linkList = [];
                $.each(appMap, function (key, value) {
                    var consumersSet = value.consumersSet;
                    var nodes_value = 1;
                    if (consumersSet != undefined) {
                        nodes_value = consumersSet.length * 1.2;
                    }
                    var nodesMap = {
                        category: 0, name: key, value: nodes_value, draggable: true
                    };
                    nodesList.push(nodesMap);

                    if (consumersSet != undefined) {
                        $.each(consumersSet, function (i, target_value) {
                            var linkMap = {
                                source: key,
                                target: target_value,
                                weight: 1,
                                name: key + "提供服务" + target_value,
                                itemStyle: {normal: {color: 'red'}}
                            };
                            linkList.push(linkMap);
                        });
                    }
                });

                var option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: ' {b}'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            restore: {show: true},
                            magicType: {show: true, type: ['force', 'chord']},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data: ['applicationName']
                    },
                    series: [
                        {
                            type: 'force',
                            name: "依赖关系",
                            ribbonType: false,
                            categories: [
                                {
                                    name: 'applicationName'
                                }
                            ],
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        textStyle: {
                                            color: '#333'
                                        }
                                    },
                                    nodeStyle: {
                                        brushType: 'both',
                                        borderColor: 'yellow',
                                        borderWidth: 1
                                    }
                                },
                                emphasis: {
                                    label: {
                                        show: false
                                    }
                                }
                            },
                            minRadius: 15,
                            maxRadius: 55,
                            gravity: 1.1,
                            scaling: 2.5,
                            draggable: true,
                            large: true,
                            roam: true,
                            linkSymbol: 'arrow',
                            steps: 10,
                            coolDown: 0.9,
                            //preventOverlap: true,
                            nodes: nodesList,
                            links: linkList
                        }
                    ]
                };
                myChart.setOption(option);
                myChart.hideLoading();
                Amm.changeiframeParentHeight();
            }
        }

    });


}
