var headerUrl = 'http://' + window.location.host;
//全局缓存
var storage = window.sessionStorage;//localStorage;

// echarts的ec对象
var echartsEc;
// 所有的app相关map，将返回的对象存储起来
var allAppResultMap = undefined;
// 测试环境url
var testUrlSet = undefined;

$(function () {
    initData();

    initClick();

    initeCharts();


});


function initData() {
    $.get(headerUrl + "/monitor/services/getAllService", function (resultVO) {
        if (resultVO.success) {
            var resutMap = resultVO.data;
            allAppResultMap = resutMap.allServicesMap;
            testUrlSet = resutMap.testUrlSet;
            // 展示风险项
            var wrongAppList = resutMap.wrongAppList;
            var wrongMethodsList = resutMap.wrongMethodsList;
            var wrongHostServiceList = resutMap.wrongHostServiceList;

            if (wrongAppList.length == 0 && wrongMethodsList.length == 0 && wrongHostServiceList.length == 0) {
                $("#warning_tab_div").addClass("hidden");
            } else {
                $("#warning_tab_div").removeClass("hidden");
            }

            if (wrongAppList.length > 0) {
                var wrongAppList_html = Mustache.render($('#string_list_template').html(), {'list': wrongAppList});
                $("#tab_more_app").html(wrongAppList_html);
            }
            if (wrongMethodsList.length > 0) {
                var wrongMethodsList_html = Mustache.render($('#string_list_template').html(), {'list': wrongMethodsList});
                $("#tab_more_method").html(wrongMethodsList_html);
            }
            if (wrongHostServiceList.length > 0) {
                var wrongHostServiceList_html = Mustache.render($('#string_list_template').html(), {'list': wrongHostServiceList});
                $("#wrong_host_content").html(wrongHostServiceList_html);
            }

            // 异常的service点击
            $(".warning_service").click(function () {
                $("#search_value").val($(this).html());
                $("#search_btn").trigger('click');
            })
        } else {
            var html = Mustache.render($('#alert_danger_template').html(), {'msg': "加载失败~！原因：" + resultVO.msg});
            $("body").prepend(html);
            $("#search_section").addClass("hidden");
        }
    });
}


function initClick() {
    // 搜索框自动去空格
    //$('#search_value').keyup(function(){
    //    var value = $(this).val().replace(" ","");
    //    $(this).val(value);
    //});
    //搜索框回车键
    $('#search_value').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            $('#search_btn').trigger('click');
        }
    });
    //点击搜索
    $("#search_btn").click(function () {
        var search_value = $("#search_value").val().trim().replace(" ", "");
        $("#main_service_section").addClass("hidden");

        serviceTable(search_value);
    });

    //点击另外一个标签
    $(".wrong_tab").click(function(){
        var content_id = $(this).find('a').attr("href");

        $('.wrong_tab').removeClass("active");
        $(this).addClass("active");
        $('.wrong_content').removeClass("active");
        $(content_id).addClass("active");

        Amm.changeiframeParentHeight();
        return false;
    });

}


function initeCharts(functionName, param) {

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
            echartsEc = ec;
            if (param != undefined) {
                functionName(param);
            } else if (functionName != undefined) {
                functionName();
            }
        }
    )


}

// =========================二级方法============

// 生成筛选的service列表 并定位到此处
function serviceTable(search_value) {
    var serviceBO_list = [];

    // 晒出符合的 ，若searchValue为空，则全部
    search_value = search_value.toUpperCase();
    $.each(allAppResultMap, function (key, value) {
        if ("" == search_value) {
            serviceBO_list.push(value);
        } else {
            var key_name = key.toUpperCase();
            if (key_name.indexOf(search_value) != -1) {
                serviceBO_list.push(value);
            }
        }
    });

    $("#appSumNumber").html(serviceBO_list.length);
    // 生成table
    var warningHtml = '<span class="badge badge-danger">WARNING</span>';
    var successHtml = '<span class="badge badge-success">SUCCESS</span>';
    var map = {
        list: serviceBO_list,
        serviceNameFunc: function () {
            var serviceName = this.serviceName;
            if ("" == search_value) {
                return serviceName
            }
            var index = serviceName.toUpperCase().indexOf(search_value);
            var next_index = index + search_value.length;
            var html = serviceName.substring(0, index);
            html += '<span class="hlight">' + serviceName.substring(index, next_index) + '</span>';
            html += serviceName.substring(next_index);
            return html
        },
        usedAppSum: function () {
            var usedApp = this.usedApp;
            if (usedApp == undefined) {
                return warningHtml.replace("WARNING", "0");
            }
            return successHtml.replace("SUCCESS", usedApp.length);
        },
        statusFunction: function () {
            var html = '';
            var ownerSet = this.ownerApp;
            var methodsSet = this.methods;
            var isHostWrong = this.isHostWrong;
            if (ownerSet != undefined && methodsSet != undefined && ownerSet.length == 1 && methodsSet.length == 1 && !isHostWrong) {
                return successHtml.replace("SUCCESS", "正常")
            }
            if(isHostWrong){
                html += warningHtml.replace("WARNING", "非法启动")
            }
            if (methodsSet == undefined) {
                html += warningHtml.replace("WARNING", "无方法")
            }
            else if (methodsSet.length > 1) {
                html += warningHtml.replace("WARNING", "多方法")
            }

            if (ownerSet == undefined) {
                html += warningHtml.replace("WARNING", "无提供者")
            }
            else if (ownerSet.length > 1) {
                html += warningHtml.replace("WARNING", "多应用提供")
            }

            return html
        },
        statusOK:function () {
            var ownerSet = this.ownerApp;
            var methodsSet = this.methods;
            if (ownerSet != undefined && methodsSet != undefined && ownerSet.length == 1 && methodsSet.length == 1) {
                return "true";
            }
            return "false";
        },
        usedAppLength:function(){
            var usedApp = this.usedApp;
            if (usedApp == undefined) {
                return "0";
            }
            return usedApp.length;
        }
    };

    var html = Mustache.render($('#search_result_template').html(), map);
    $("#main_service_tbody").html(html);
    $("#search_result_section").removeClass("hidden");

    Amm.changeiframeParentHeight();
    tableClick();
    // 滚动到指定位置
    var scroll_offset;
    if ("" == search_value) {
        var html = Mustache.render($('#alert_danger_template').html(), {'msg': "若无输入搜索词，将展示所有service！！"});
        $("#search_result_section").prepend(html);
        scroll_offset = $("#search_result_section").offset();
    } else {
        scroll_offset = $("#main_service_tbody").offset();
    }
    Amm.animate(scroll_offset);
}


// 生成table 的 后续
function tableClick() {
    $("#main_service_tbody > tr").unbind("click").click(function () {
        var value = $(this).data("value");
        var serviceBO = allAppResultMap[value];
        var methodsHost = serviceBO.methodsHost;
        var methodsSet = serviceBO.methods;
        if (methodsSet != undefined) {
            $("#serviceMethods").html("");

            var list = [];
            $.each(methodsHost, function (method, hostList) {
                var method_array = method.split(",");
                var map = {
                    method_array: method_array,
                    hostList: hostList
                };
                list.push(map);
            });
            var map = {
                'list': list,
                methodFunction: function () {
                    var method_array = this.method_array;
                    method_array.sort();
                    // 每4个为一组
                    var listArray = [];
                    var method_list = [];
                    var size = method_array.length;
                    for (var i = 0; i < size; i++) {
                        method_list.push(method_array[i]);
                        if (i % 3 == 2 && i != size) {
                            var method_map = {'list': method_list};
                            listArray.push(method_map);
                            method_list = new Array();
                        } else if (i == (size - 1)) {
                            var method_map = {};
                            method_map['list'] = method_list;
                            listArray.push(method_map);
                        }
                    }
                    var html = Mustache.render($('#method_template').html(), {'listArray':listArray});
                    return html
                },
                hostFunction:function(){
                    var html = "";
                    var hostList = this.hostList;
                    $.each(hostList,function(i,hostBO){
                        var host = hostBO.host;
                        if($.inArray(host,testUrlSet) > -1){
                            html += '<span class="badge badge-success">测试环境：'+hostBO.hostString+ "</span>";
                        }else{
                            html += hostBO.hostString + " "
                        }
                    });
                    return html;
                }
            };
            var html = Mustache.render($('#method_host_template').html(), map);
            $("#method").html(html);
        } else {
            $("#method").html("");
            $("#serviceMethods").html("无 Methods");
        }
        $('#serviceName').html(serviceBO.serviceName);
        $("#main_service_section").removeClass("hidden");
        main_service_section(serviceBO);

        Amm.changeiframeParentHeight();

        var scroll_offset = $("#main_service_section").offset();
        // 滚动到指定位置
        Amm.animate(scroll_offset);
        return false;
    });

    //筛选 异常service
    $("#select_wrong_service_btn").unbind("click").click(function (){
        $(this).toggleClass("actived");

        changeTableShow();
        return false;
    });

    //筛选 0 用户
    $("#select_no_used_btn").unbind("click").click(function (){
        $(this).toggleClass("actived");
        changeTableShow();
        return false;
    });
}

//表格内部数据的展示和隐藏
function changeTableShow(){
    var wrong_service_status = $("#select_wrong_service_btn").hasClass("actived");
    var no_used_status = $("#select_no_used_btn").hasClass("actived");

    var tr_array = $("#main_service_tbody > tr");
    tr_array.removeClass("hidden");
    $.each(tr_array,function(i,tr_object){
        if(wrong_service_status){
            // 筛选出错误的
            var wrong_td = $(tr_object).find(".status_false").html();
            if(wrong_td == undefined){
                $(this).addClass("hidden");
            }
        }
        if(no_used_status){
            // 筛选出无用户的
            var no_used= $(tr_object).find(".used_0").html();
            if(no_used == undefined){
                $(this).addClass("hidden");
            }
        }
    });
    Amm.changeiframeParentHeight();

}

// section：main_service_section 的动态内容
function main_service_section(serviceBO) {
    var methodsSet = serviceBO.methods;

    var usedApp = serviceBO.usedApp;
    if (usedApp == undefined) {
        $("#echarts_div").html("无消费的应用！");
        return false;
    }

    var html = Mustache.render($('#echarts_section_template').html(), {});
    $("#echarts_div").html(html);
    Amm.changeiframeParentHeight();


    if (echartsEc == undefined) {
        initeCharts(servicesRelationForceChart, usedApp)
    } else {
        servicesRelationForceChart(usedApp);
    }

    if (methodsSet == undefined) {
        var html = Mustache.render($('#alert_danger_template').html(), {'msg': "不存在方法，解决后方可查看数据图表"});
        $("#echarts_div").prepend(html);
        $("#tab_service_data_btn").addClass("hidden");
        return false;
    }else if (methodsSet.length > 1) {
        var html = Mustache.render($('#alert_danger_template').html(), {'msg': "存在多个不同方法，解决后方可查看数据图表"});
        $("#echarts_div").prepend(html);
        $("#tab_service_data_btn").addClass("hidden");
        return false;
    } else {
        $("#tab_service_data_btn").removeClass("hidden");
    }



    //切换 charts 的内容
    $("#tab_service_data_btn").unbind("click").click(function () {

        $('#tab_service_relation_btn').parent().removeClass("active");
        $(this).parent().addClass("active");


        $('#tab_service_relation').removeClass("active");
        $('#tab_service_data').addClass("active");

        Amm.changeiframeParentHeight();

        return false;
    });

    //点击 方法
    $(".method_class").unbind("click").click(function () {
        //判断 是否 可以看数据图
        if($("#tab_service_data_btn").hasClass("hidden")){
            return false;
        }
        //若自身为active 则不进行后续操作
        if($(this).hasClass("active")){
            return false;
        }
        // 去除其他active
        $(".method_class").removeClass("active");
        //自己+active
        $(this).addClass("active");

        //跳转到数据图，默认选中今天
        $("#tab_service_data_btn").trigger('click');
        $(".relation_bar_options").removeClass("active");
        $('#relation_bar_day').parent().addClass("active");

        //生成数据图表
        var serviceName = $("#serviceName").html().trim();
        var methodName = $(this).data("method").trim();
        var type = $('#relation_bar_day').data("value");

        $('#serviceNameForCharts').text(methodName);
        serviceArtChart(serviceName,methodName, type,is_used_method);
    });

    //  根据日期筛选
    $(".relation_bar_options").unbind("click").click(function () {


        var type = $(this).find('input').data("value");
        var serviceName = $("#serviceName").html().trim();
        var methodName = $(".method_class.active").data("method");
        if(methodName == undefined){
            Amm.alertFuc("请选择方法后再进行图表查看");
            return false;
        }
        methodName = methodName.trim();

        var loadingEL = $('#tabbable-custom');
        Metronic.blockUI(loadingEL);

        serviceArtChart(serviceName,methodName, type,is_used_method);




    });


}

function is_used_method(isUsed){
    //判断 是否有展示 是否被使用
    var is_used_span = $(".method_class.active").children('span').text();
    if(is_used_span == '' || is_used_span == '无'){
        var the_span = $(".method_class.active").children('span');
        if(isUsed == "true"){
            the_span.removeClass("badge-danger");
            the_span.addClass("badge-success");
            the_span.text('有');
        }else if(is_used_span == ''){
            the_span.addClass("badge-danger");
            the_span.removeClass("badge-success");
            the_span.text('无');
        }

    }
}

// ====echarts===
function servicesRelationForceChart(usedApp) {
    var ec = echartsEc;
    var charts_id = 'service_relation_force_echarts';
    var myChart = ec.init(document.getElementById(charts_id));
    myChart.showLoading({
        text: 'Loading...',
        effect: 'bubble',
        textStyle: {
            fontSize: 20
        }
    });

    var nodesList = [];
    nodesList.push({
        category: 0, name: "Service", value: 10, draggable: true
    });
    var linkList = [];
    $.each(usedApp, function (i, used_name) {
        var nodesMap = {
            category: 1, name: used_name, value: 10, draggable: true
        };
        nodesList.push(nodesMap);

        var linkMap = {
            source: "Service",
            target: used_name,
            weight: 1,
            name: "提供服务给" + used_name,
            itemStyle: {normal: {color: 'red'}}
        };
        linkList.push(linkMap);
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
                        name: '自身service'
                    },
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
                scaling: 1.2,
                draggable: false,
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

}


// 生成art——tps图
function serviceArtChart(serviceName,methodName, type,success_func){
    console.log('start');
    if(serviceName =='' || methodName =='' || type =='' ){
        return false;
    }
    if (type == undefined) {
        type = $("#relation_bar_day").data("value");
    }
    //准备好容器
    var tpsChart = echartsEc.init(document.getElementById('tps_bar_echarts'));
    var artChart = echartsEc.init(document.getElementById('art_bar_echarts'));
    tpsChart.showLoading({
        text: 'Loading...',
        effect: 'bubble',
        textStyle: {
            fontSize: 20
        }
    });
    artChart.showLoading({
        text: 'Loading...',
        effect: 'bubble',
        textStyle: {
            fontSize: 20
        }
    });

    var nowDate = Amm.getNowDate();
    //缓存里面找一圈
    var consumer_key = nowDate + type + serviceName+methodName + "consumerApp";
    var tps_key = nowDate + type + serviceName+methodName + "tps";
    var art_key = nowDate + type + serviceName+methodName + "art";
    var consumer = storage.getItem(consumer_key);
    var tps_option = JSON.parse(storage.getItem(tps_key));
    var art_option = JSON.parse(storage.getItem(art_key));

    var is_used_key = nowDate + type + serviceName+methodName + "is_used";
    var is_used = storage.getItem(is_used_key);
    if(tps_option == undefined){
        is_used = "false";
        //从服务器上获得数据
        // 60*60 小时
        var timeParticle = 60 * 60;
        //小数点后保留4位
        var pointNumber = 4;
        $.ajax({
            url: headerUrl + "/monitor/services/getMethodSumOneDay",
            data:{serviceName:serviceName,methodName:methodName,type:type},
            success: function (result) {
                Metronic.unblockUI($('#tabbable-custom'));
                var time_array = Amm.getHourArray();
                var resultMap = result.data;
                var appList = resultMap.appList;
                var dataMap = resultMap.dataMap;

                //涉及的app打印
                var appHtml = "";
                $.each(appList,function(i,appName){
                    appHtml += appName + " ";
                });
                consumer = appHtml;
                storage.setItem(consumer_key,appHtml);
                $("#serviceAppForCharts").html(consumer);


                var tps_array = [];
                var art_array = [];
                $.each(time_array, function (i, time) {
                    var hourMap = dataMap[time.replace('时','')];
                    if(hourMap == undefined){
                        tps_array.push(0);
                        art_array.push(0);
                    }else {
                        is_used = "true";
                        var successNum = hourMap['success'];
                        var elapsedNum = hourMap['elapsed'];
                        //方法级别的 tps和art统计
                        var tps = Number(successNum / timeParticle).toFixed(pointNumber) + "";
                        var art = Number(elapsedNum / successNum).toFixed(1) + "";
                        tps_array.push(tps);
                        art_array.push(art);
                    }
                });

                success_func(is_used);

                var tps_option = option_String('tps',time_array,tps_array);
                var art_option = option_String('art',time_array,art_array);

                storage.setItem(tps_key, JSON.stringify(tps_option));
                storage.setItem(art_key, JSON.stringify(art_option));
                storage.setItem(is_used_key, is_used);

                tpsChart.setOption(tps_option);
                artChart.setOption(art_option);

                tpsChart.hideLoading();
                artChart.hideLoading();
            }

        });


    }else{
        Metronic.unblockUI($('#tabbable-custom'));
        success_func(is_used);

        tpsChart.setOption(tps_option);
        artChart.setOption(art_option);

        tpsChart.hideLoading();
        artChart.hideLoading();

        $("#serviceAppForCharts").html(consumer);
    }



}


function option_String(titleName, time_data_array_string, consumer_data) {

    var option = {
        title: {
            text: titleName
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['consumer']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: time_data_array_string
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: 'consumer',
                type: 'line',
                data: consumer_data,
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };


    return option;
}