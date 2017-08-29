/**
 * 微信公众号管理初始化
 */
var Wxmessage = {
    id: "WxmessageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Wxmessage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Wxmessage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Wxmessage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加微信公众号
 */
Wxmessage.openAddWxmessage = function () {
    var index = layer.open({
        type: 2,
        title: '添加微信公众号',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wxmessage/wxmessage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看微信公众号详情
 */
Wxmessage.openWxmessageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '微信公众号详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wxmessage/wxmessage_update/' + Wxmessage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除微信公众号
 */
Wxmessage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wxmessage/delete", function (data) {
            Feng.success("删除成功!");
            Wxmessage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wxmessageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询微信公众号列表
 */
Wxmessage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Wxmessage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Wxmessage.initColumn();
    var table = new BSTable(Wxmessage.id, "/wxmessage/list", defaultColunms);
    table.setPaginationType("client");
    Wxmessage.table = table.init();
});
