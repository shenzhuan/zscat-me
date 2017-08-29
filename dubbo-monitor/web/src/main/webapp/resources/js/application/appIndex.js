var headerUrl = 'http://' + window.location.host;
//全局缓存
var storage = window.sessionStorage;//localStorage;

// echarts的ec对象
var echartsEc;
// 所有的app相关map，将返回的对象存储起来
var allAppResutMap = undefined;

$(function () {
    initData();
    initeCharts();


    buttonClick();
    othersClick();
    inputFunction();
    selectFunction();

});

//初始化数据
function initData() {

    if (allAppResutMap == undefined) {
        var loadingEL = $('#app_main_body');
        Metronic.blockUI(loadingEL);
        $.ajax({
            url: headerUrl + "/monitor/application/getAllAPPAndRelation",
            //同步
            async: false,
            success: function (resultVO) {
                Metronic.unblockUI(loadingEL);
                if (resultVO.success) {
                    allAppResutMap = resultVO.data;
                } else {
                    $("#app_main_body").html("加载失败~！原因：" + resultVO.msg);
                }
            }

        });
    }
    $('#appSumNumber').html(allAppResutMap.appSum);
    $('#groupSumNumber').html(allAppResutMap.groupSum);

    // 拼接所有app数据
    var noHtml = '<span class="badge badge-danger">无</span>';
    var numberHtml = '<span class="badge badge-success">NUMBER</span>';
    var providers_category_html = '<span class="badge badge-danger providers">提供者</span>';
    var consumers_category_html = '<span class="badge badge-success consumers">消费者</span>';

    var appList = allAppResutMap.appList;
    var map = {
        list: appList,
        categoryFunc: function () {
            var categoty_html = '';
            var isProvider = this.isProvider;
            var isConsumer = this.isConsumer;
            if (isProvider) categoty_html += providers_category_html;
            if (isConsumer) categoty_html += consumers_category_html;
            return categoty_html;
        },
        serviceSumFunc: function () {
            var sum = Number(this.serviceSum);
            if (sum == 0) return noHtml;
            return numberHtml.replace('NUMBER', sum);
        },
        providerSumFunc: function () {
            var sum = Number(this.providerSum);
            if (sum == 0) return noHtml;
            return numberHtml.replace('NUMBER', sum);
        },
        consumerSumFunc: function () {
            var sum = Number(this.consumerSum);
            if (sum == 0) return noHtml;
            return numberHtml.replace('NUMBER', sum);
        }
    };
    var html = Mustache.render($('#main_app_list_template').html(), map);
    $("#main_application_tbody").html(html);
    Amm.changeiframeParentHeight();

}

function buttonClick() {
    $(".reload").click(function () {
        $('#search_app_btn').trigger('click');
        return false;
    });



}

//其他点击
function othersClick() {
    $("#main_application_tbody > tr").click(function () {
        var provider_value = $(this).find(".providers").html();
        var scroll_offset;  //得到pos这个div层的offset，包含两个值，top和left
        var appName = $(this).data("appname");

        var html = Mustache.render($('#echarts_section_template').html(), {});
        $("#echarts_section").html(html);
        $('#echarts_section').removeClass("hidden");

        finalSectionFunction();

        if (provider_value == undefined) {
            $("#services_section").addClass("hidden");

            $("#alert_section").html(Mustache.render($('#alert_danger_template').html(), {appName: appName}));
            scroll_offset = $("#alert_section").offset();  //得到pos这个div层的offset，包含两个值，top和left


            $("#services_app_span").text(appName);
            aPPRelationForceChart(appName);
            //服务数据图表隐藏
            $("#tab_app_data_btn").addClass("hidden");
            $("#tab_app_ranking_btn").addClass("hidden");

        } else {
            $("#services_section").removeClass("hidden");

            $("#alert_section").html("");
            scroll_offset = $("#services_section").offset();
            initServiceTable(appName);

            aPPRelationForceChart(appName);
            //服务数据图表出现
            $("#tab_app_data_btn").removeClass("hidden");
            $("#tab_app_ranking_btn").removeClass("hidden");
        }
        Amm.changeiframeParentHeight();

        $(parent.document).find('body').animate({
            scrollTop: scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
        }, 1200);
    });

}
function inputFunction() {

    $('#search_app_value').keyup(function () {
        filterAppTable();
        return false;
    });
    $('#search_service_value').keyup(function () {
        var key_value = $("#search_service_value").val().trim().toUpperCase();
        var all_tr = $('#all_service_div > div > div.active>div>table>tbody>tr.service');
        if (key_value == '') {
            $(all_tr).removeClass("hidden");
        } else {
            $.each(all_tr, function (i, obj) {
                var value = $(obj).data("servicename").toUpperCase();
                if (value.indexOf(key_value) == -1) {
                    $(obj).addClass("hidden");
                } else {
                    $(obj).removeClass("hidden");
                }
            });
        }
        return false;
    });
}

function  selectFunction() {

    $('#main_category_select').change(function () {
        var value = $('#main_category_select').val();
        var all_tr = $('#main_application_tbody > tr');

        if (value == '-1') {
            all_tr.removeClass("hidden");
            return false;
        }

        $.each(all_tr, function (i, obj) {
            var provider_value = $(obj).find(".providers").html();
            var consumer_value = $(obj).find(".consumers").html();
            if (value == 'all') {
                if (provider_value != undefined && consumer_value != undefined) {
                    $(obj).removeClass("hidden");
                } else {
                    $(obj).addClass("hidden");
                }
            }
            if (value == "providers") {
                if (provider_value != undefined && consumer_value == undefined) {
                    $(obj).removeClass("hidden");
                } else {
                    $(obj).addClass("hidden");
                }
            }
            if (value == "consumers") {
                if (consumer_value != undefined && provider_value == undefined) {
                    $(obj).removeClass("hidden");
                } else {
                    $(obj).addClass("hidden");
                }
            }
        });


    });
}

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
            echartsEc = ec;
            allAPPRelationForceChart(ec);

        }
    )


}

//===========================二级方法=================
// 获得该app的service列表
function initServiceTable(appName) {
    var appMap = allAppResutMap.allApp;
    var appBO = appMap[appName];
    var serviceMap = appBO.serviceMap;

    var tabs_list = [];
    var tab_first = true;

    var content_list = [];
    var tab_content_first = true;
    var status_name = {'online':'线上','test':'测试','local':'本地','wrong':'错误异常'};
    $.each(serviceMap,function(status,serviceSet){
        //标签头
        var tab_map = {'name':status_name[status],'status':status,'class':''};
        if(tab_first){
            tab_map['class'] = 'active';
            tab_first = false;
        }
        tabs_list.push(tab_map);
        //内容
        var content_map = {'name':status_name[status],'status':status,'class':''};
        if(tab_content_first){
            content_map['class'] = 'active';
            tab_content_first = false;
        }
        if(status == 'wrong'){
            content_map['wrong'] = 'wrong';
        }

        //拼接每一行的内容
        var indexs = 0;
        var map = {
            list: serviceSet,
            indexFunc: function () {
                return indexs += 1
            },
            consumersFunc: function () {
                var isConsumers = this.isConsumer;
                if (isConsumers) return '<span class="badge badge-danger"><i class="fa fa-check"></i> </span>'
            },
            wrongFunc:function(){
                var wrongReason = this.wrongReason;
                if (wrongReason != undefined && wrongReason!= ''){
                    return wrongReason;
                }
            },
            methodFunc:function(){
                var html = "";
                var methodsHost = this.methodsHost;
                $.each(methodsHost,function(method,hostSet){
                    html += method +"-----";
                    $.each(hostSet,function(i,host){
                        html += host.hostString+" ";
                    })
                })
                return html;
            }

        };
        var tbody_html = Mustache.render($('#service_table_tbody_template').html(), map);
        content_map['tbody_html'] = tbody_html;
        content_list.push(content_map);
    });

    var tab_html = Mustache.render($('#service_tab_templates').html(), {'list':tabs_list});
    var content_html = Mustache.render($('#service_content_templates').html(), {'list':content_list});

    $('#all_service_div').html(tab_html+content_html);

    $("#services_app_span").text(appName);

    $(".service").unbind("click").click(function () {
        $(this).next("tr").toggleClass("hidden");
        return false;
    });
    $(".service_tab").unbind("click").click(function () {
        var content_id = $(this).find('a').attr("href");

        $('.service_tab').removeClass("active");
        $(this).addClass("active");
        $('.service_content').removeClass("active");
        $(content_id).addClass("active");

        //重置筛选
        $("#search_service_value").val('');
        $(".service").removeClass("hidden");

        Amm.changeiframeParentHeight();
        return false;
    });
    $('.Tooltip').tooltip()
}

// 筛选主表格的app名称
function filterAppTable() {
    var key_value = $("#search_app_value").val().trim().toUpperCase();
    var all_tr = $('#main_application_tbody > tr');
    if (key_value == '') {
        $(all_tr).removeClass("hidden");
    } else {
        $.each(all_tr, function (i, obj) {
            var value = $(obj).data("appname").toUpperCase();
            if (value.indexOf(key_value) == -1) {
                $(obj).addClass("hidden");
            } else {
                $(obj).removeClass("hidden");
            }
        });
    }
    return false;
}


function finalSectionFunction(){
    // 数据图表
    $("#tab_app_data_btn").unbind("click").click(function () {

        $(this).parent().siblings().removeClass("active");
        $(this).parent().addClass("active");


        $('#tab_app_relation').removeClass("active");
        $('#tab_app_ranking').removeClass("active");
        $('#tab_app_data').addClass("active");

        if ($("#app_relation_success_bar_echarts").html().trim() == "") {
            aPPRelationBarChart();
        }

        Amm.changeiframeParentHeight();

        return false;
    });

    //排行榜
    $("#tab_app_ranking_btn").unbind("click").click(function () {

        $(this).parent().siblings().removeClass("active");
        $(this).parent().addClass("active");


        $('#tab_app_relation').removeClass("active");
        $('#tab_app_data').removeClass("active");
        $('#tab_app_ranking').addClass("active");

        rankingFunction();

        return false;
    });


    $(".relation_force_options").unbind("click").click(function () {
        var type = $(this).find('input').data("value");
        var appName = $("#services_app_span").text();
        aPPRelationForceChart(appName, type);
    });

    $(".relation_bar_options").unbind("click").click(function () {
        var type = $(this).find('input').data("value");
        aPPRelationBarChart(type);
    });

}


//排行榜
function rankingFunction(){
    var appName = $("#services_app_span").text();

    var loadingEL = $("#tab_app_ranking");
    Metronic.blockUI(loadingEL);
    $.ajax({
        type: "GET",
        url: "/monitor/application/getMethodRanking",
        data:{appName:appName},
        //6分钟
        timeout:360000,
        error:function(jqXHR, textStatus, errorThrown){
            if(textStatus=="timeout"){
                Amm.alertFuc("加载超时，请切换tab，切回来重试");
            }else{
                Amm.alertFuc(textStatus);
            }
        },
        success:function(result){
            var resultList = result.data;
            rankingFunctionHelp(resultList);

            Amm.changeiframeParentHeight();
            Metronic.unblockUI(loadingEL);
        }
    });
    //$.get("/monitor/application/getMethodRanking",{appName:appName},);

}

function rankingFunctionHelp(resultList){
    var firstIndexHtml = '<div class="label label-best label-danger"> <i class="fa fa-trophy"></i>&nbsp;INDEX</div>';
    var otherIndexHtml = '<span class="primary-link">INDEX </span>';

    var indexs = 0;
    var map = {
        list:resultList,
        indexFunc: function () {
            var html = "";
            indexs += 1;
            if(indexs < 4){
                html = firstIndexHtml.replace("INDEX",indexs);
            }else{
                html = otherIndexHtml.replace("INDEX",indexs);
            }
            return html;
        }
    };
    var rank_html = Mustache.render($('#method_rank_template').html(), map);
    $("#ranking_body").html(rank_html);

}


//所有app之间的依赖关系
function allAPPRelationForceChart(ec) {
    if (allAppResutMap != undefined) {
        var appMap = allAppResutMap.allApp;
        var nodesList = new Array;
        var linkList = new Array;
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

        if (ec == undefined) {
            ec = echartsEc;
        }
        var charts_id = 'all_app_relation_force_echarts';
        var myChart = ec.init(document.getElementById(charts_id));


        var option = {
            title: {
                text: '圈越大，与其相关的app越多'
            },
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
                    scaling: 2.0,
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
        Amm.changeiframeParentHeight();
    }


}
//app与app之间的依赖调用关系
function aPPRelationForceChart(appName, type) {

    //$("#app_relation_force_body").empty();
    //$("#app_relation_force_body").html('<div id="app_relation_force_echarts" style="height:500px"> </div>');

    //var loadingEL = $('#app_relation_force_echarts');
    //Metronic.blockUI(loadingEL);
    var charts_id = 'app_relation_force_echarts';
    var ec = echartsEc;
    var myChart = ec.init(document.getElementById(charts_id));
    myChart.showLoading({
        text : 'Loading...',
        effect : 'bubble',
        textStyle : {
            fontSize : 20
        }
    });

    if (type == undefined) {
        type = $("#relation_force_day").data("value");
    }
    //
    var nowDate = Amm.getNowDate();
    var key = nowDate + type + appName + "aPPRelationForceChart";
    var option = JSON.parse(storage.getItem(key));
    if (option == undefined) {
        $.get(headerUrl + "/monitor/application/getSuccessByConsumer", {
            type: type,
            source: appName
        }, function (resultVO) {
            if (resultVO.success) {
                var resultMap = resultVO.data;
                var providerMap = resultMap.provider;
                var consumerMap = resultMap.consumer;
                var providerConsumer = resultMap.providerConsumer;

                var nodesList = [];
                var linkList = [];
                nodesList.push({category: 0, name: appName, value: 3, draggable: true})
                $.each(providerMap, function (keyName, providerValue) {
                    var providerName = keyName + "(" + providerValue + ")";
                    var nodesMap = {
                        category: 1, name: providerName, value: providerValue, draggable: true
                    };

                    var linkMap = {
                        source: providerName,
                        target: appName,
                        weight: 1,
                        name: keyName + "提供服务" + appName + ":" + providerValue + "次"
                    };
                    nodesList.push(nodesMap);
                    linkList.push(linkMap);
                });
                $.each(consumerMap, function (keyName, consumerValue) {
                    var consumerName = keyName + "(" + consumerValue + ")";
                    var nodesMap = {
                        category: 1, name: consumerName, value: consumerValue, draggable: true
                    };

                    var linkMap = {
                        source: appName,
                        target: consumerName,
                        weight: 1,
                        name: appName + "提供服务" + keyName + ":" + consumerValue + "次"
                    };
                    nodesList.push(nodesMap);
                    linkList.push(linkMap);
                });
                $.each(providerConsumer, function (keyName, map) {
                    var providerValue = map.provider;
                    var consumerValue = map.consumer;

                    var all_Value = providerValue + consumerValue;
                    var allName = keyName + "(" + all_Value + ")";
                    var linkMap1 = {
                        source: allName,
                        target: appName,
                        weight: 1,
                        name: keyName + "提供服务" + appName + ":" + providerValue + "次",
                        itemStyle: {normal: {color: 'red'}}

                    };
                    var linkMap2 = {
                        source: appName,
                        target: allName,
                        weight: 1,
                        name: appName + "提供服务" + keyName + ":" + consumerValue + "次",
                        itemStyle: {normal: {color: 'red'}}
                    };
                    linkList.push(linkMap1);
                    linkList.push(linkMap2);

                    var nodesMap = {
                        category: 2, name: allName, value: all_Value % 10, draggable: true
                    };
                    nodesList.push(nodesMap);
                });

                option = {
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
                        data: ['其他app', '相互调用app']

                    },
                    series: [
                        {
                            type: 'force',
                            name: "依赖关系",
                            ribbonType: false,
                            categories: [
                                {
                                    name: '自身app'
                                },
                                {
                                    name: '其他app'
                                },
                                {
                                    name: '相互调用app'
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
                                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
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
                storage.setItem(key, JSON.stringify(option));
                myChart.setOption(option);

                myChart.hideLoading();
            } else {
                alert("出意外了：" + resultVO.msg);
            }
        });

    } else {

        myChart.setOption(option);
        myChart.hideLoading();
        //myChart.setOption(option);
    }


}

// app 关系柱状图
function aPPRelationBarChart(type) {
    if (type == undefined) {
        type = $("#relation_bar_day").data("value");
    }
    var ec = echartsEc;

    var appName = $("#services_app_span").text();
    var nowDate = Amm.getNowDate();

    var successChart = ec.init(document.getElementById('app_relation_success_bar_echarts'));
    var failChart = ec.init(document.getElementById('app_relation_fail_bar_echarts'));
    successChart.showLoading({
        text : 'Loading...',
        effect : 'bubble',
        textStyle : {
            fontSize : 20
        }
    });
    failChart.showLoading({
        text : 'Loading...',
        effect : 'bubble',
        textStyle : {
            fontSize : 20
        }
    });



    var success_key = nowDate + type + appName + "aPPRelationBarChart"+"_success";
    var fail_key = nowDate + type + appName + "aPPRelationBarChart"+"_fail";
    var success_option = JSON.parse(storage.getItem(success_key));
    var fail_option = JSON.parse(storage.getItem(fail_key));
    if (success_option == undefined) {
        // 按小时展示
        if(type == 'Today' || type == 'Yesterday') {
            $.get(headerUrl + "/monitor/application/getSuccessByConsumerOnHour", {
                type: type,
                source: appName
            }, function (resultVO) {
                if (resultVO.success) {
                    var resultMap = resultVO.data;
                    var time_data_array = ['00时', '01时', '02时', '03时', '04时', '05时', '06时', '07时', '08时', '09时', '10时', '11时', '12时', '13时', '14时', '15时', '16时', '17时', '18时', '19时', '20时', '21时', '22时', '23时'];
                    chartsInDayOrHour(resultMap, time_data_array, success_key, fail_key, successChart, failChart);
                }
            });
        }
        // 按天展示
        if(type == 'Seven_DAY' || type == 'Fifteen_DAT') {
            $.get(headerUrl + "/monitor/application/getSuccessByConsumerOnDay", {
                type: type,
                source: appName
            }, function (resultVO) {
                if (resultVO.success) {
                    var finalMap = resultVO.data;
                    var resultMap = finalMap['dataMap'];
                    var time_data_array =  finalMap['dateList'];
                    chartsInDayOrHour(resultMap, time_data_array, success_key, fail_key, successChart, failChart);
                }
            });
        }


    } else {

        successChart.setOption(success_option);
        failChart.setOption(fail_option);
        successChart.hideLoading();
        failChart.hideLoading();

    }


}

//成功失败的chart
function chartsInDayOrHour(resultMap,time_data_array,success_key,fail_key,successChart,failChart){
    var legend_data_array = [];
    var success_series_array = [];
    var fail_series_array = [];
    $.each(resultMap, function (consumerName, hourSumMap) {
        legend_data_array.push(consumerName);
        var success_array = [];
        var fail_array = [];
        $.each(time_data_array, function (i, time) {
            var sumMap = hourSumMap[time.replace('时','')];
            if(sumMap == undefined){
                success_array.push(0);
                fail_array.push(0);
            }else {
                var success = sumMap["success"];
                var fail = sumMap["fail"];
                success_array.push(success);
                fail_array.push(fail);
            }
        });

        var success_series = {
            name: consumerName,
            type: 'line',
            data: success_array,
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
        };
        success_series_array.push(success_series);
        var fail_series = {
            name: consumerName,
            type: 'line',
            data: fail_array,
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
        };
        fail_series_array.push(fail_series)
    });

    var success_option = {
        title : {
            text: '成功的服务次数'
        },
        tooltip: {
            trigger: 'axis'
        },

        legend: {
            data: legend_data_array,
            "y":"23"
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
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
                data: time_data_array,
                axisLabel: {formatter: '{value}'}
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    formatter: '{value}次'
                }
            }
        ],
        series: success_series_array
    };
    var fail_option = {
        title : {
            text: '失败的服务次数'
        },
        tooltip: {
            trigger: 'axis'
        },

        legend: {
            data: legend_data_array,
            "y":"23"
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
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
                data: time_data_array,
                axisLabel: {formatter: '{value}'}
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    formatter: '{value}次'
                }
            }
        ],
        series: fail_series_array
    };
    storage.setItem(success_key, JSON.stringify(success_option));
    storage.setItem(fail_key, JSON.stringify(fail_option));

    successChart.setOption(success_option);
    failChart.setOption(fail_option);

    successChart.hideLoading();
    failChart.hideLoading();
}


