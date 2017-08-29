var headerUrl = 'http://' + window.location.host;

$(function () {
    initData();
    initFunction();
});


function initData() {
    $.get(headerUrl + "/monitor/hosts/getAllHostPage", function (resultVO) {
        if (resultVO.success) {
            var resutMap = resultVO.data;
            var sum = resutMap['sum'];
            var hostMap = resutMap['hostMap'];
            var hostList = resutMap['hostList'];

            var provider_size = 0;
            var consumer_size = 0;

            var all_html = "";
            var providers_category_html = '<span class="badge badge-danger providers">提供者</span>';
            var consumers_category_html = '<span class="badge badge-success consumers">消费者</span>';
            //var successHtml = '<span class="badge badge-danger CATEGORY_CLAEE">SUCCESS</span>';
            $.each(hostList, function (i, host) {
                var hostBO = hostMap[host];
                var providersSet = hostBO['providers'];
                var consumersSet = hostBO['consumers'];
                var hostName = hostBO['hostName'];
                var anotherIp = hostBO['anotherIp'];


                var statusHtml = '';
                var providersHtml = '';
                var consumersHtml = '';

                if (providersSet != undefined && providersSet.length != 0) {
                    provider_size += providersSet.length;
                    statusHtml += providers_category_html;
                    providersHtml = Mustache.render($('#main_host_td_template').html(), {list: providersSet});
                }
                if (consumersSet != undefined && consumersSet.length != 0) {
                    consumer_size += consumersSet.length;
                    statusHtml += consumers_category_html;
                    consumersHtml = Mustache.render($('#main_host_td_template').html(), {list: consumersSet});
                }

                var map = {
                    hostname: hostBO['host'],
                    providersHtml: providersHtml,
                    consumersHtml: consumersHtml,
                    hostName: hostName,
                    anotherIp: anotherIp,
                    statusHtml: statusHtml
                };
                var tr_html = Mustache.render($('#main_host_tr_template').html(), map);

                all_html += tr_html;
            });

            $("#hostSumNumber").html(sum);
            $("#main_host_tbody").html(all_html);
            $("#search_result_section").removeClass("hidden");
            Amm.changeiframeParentHeight();

            //console.log("==============providers:"+provider_size+" consumers:"+consumer_size)
        } else {
            var html = Mustache.render($('#alert_danger_template').html(), {'msg': "加载失败~！原因：" + resultVO.msg});
            $("body").prepend(html);
            $("#search_result_section").addClass("hidden");
        }
    });
}

function initFunction() {
    $('#search_host_value').keyup(function () {
        filterTable();
        return false;
    });
    $('#providers_search').keyup(function () {
        filterTable();
        return false;
    });
    $('#consumers_search').keyup(function () {
        filterTable();
        return false;
    });
    $('#main_category_select').change(function () {
        filterTable();
        return false;
    });
}

function filterTable() {
    var search_ip = $('#search_host_value').val().trim().toUpperCase();
    var search_provider = $('#providers_search').val().trim().toUpperCase();
    var search_consumer = $('#consumers_search').val().trim().toUpperCase();
    var search_category = $('#main_category_select').val();

    $("#main_host_tbody > tr").removeClass("hidden");


    if (search_category != '-1') {
        $.each($("#main_host_tbody > tr"), function (i, tr_object) {
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

    //ip搜索
    if (search_ip != "") {
        $.each($("#main_host_tbody > tr"), function (i, tr_object) {
            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var iphost = $(this).data("appip").toUpperCase();
            if (iphost.indexOf(search_ip) == -1) {
                $(tr_object).addClass("hidden");
            }
        });
    }

    //provider搜索
    if (search_provider != '') {
        $.each($("#main_host_tbody > tr"), function (i, tr_object) {
            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var providerHtml = $(tr_object).find(".providerHtml").find("div").html();
            if (providerHtml != undefined) {
                providerHtml = providerHtml.toUpperCase();
                if (providerHtml.indexOf(search_provider) == -1) {
                    $(tr_object).addClass("hidden");
                }
            } else {
                $(tr_object).addClass("hidden");
            }
        });
    }

    //consumer搜索
    if (search_consumer != '') {
        $.each($("#main_host_tbody > tr"), function (i, tr_object) {

            if ($(tr_object).hasClass("hidden")) {
                return true;
            }
            var consumerHtml = $(tr_object).find(".consumersHtml").find("div").html();
            if (consumerHtml != undefined) {
                consumerHtml = consumerHtml.toUpperCase();
                if (consumerHtml.indexOf(search_consumer) == -1) {
                    $(tr_object).addClass("hidden");
                }
            } else {
                $(tr_object).addClass("hidden");
            }
        });

    }


}


