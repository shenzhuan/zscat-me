/**
 * 站点管理初始化
 */
var CmsSite = {
    id: "CmsSiteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CmsSite.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
         {title: '标题', field: 'name', align: 'center', valign: 'middle', sortable: true},
         {title: 'Logo', field: 'logo', align: 'center', valign: 'middle', sortable: true},

         {title: '版权', field: 'copyright', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
CmsSite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CmsSite.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加站点
 */
CmsSite.openAddCmsSite = function () {
    var index = layer.open({
        type: 2,
        title: '添加站点',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cmsSite/cmsSite_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看站点详情
 */
CmsSite.openCmsSiteDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '站点详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cmsSite/cmsSite_update/' + CmsSite.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除站点
 */
CmsSite.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cmsSite/delete", function (data) {
            Feng.success("删除成功!");
            CmsSite.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询站点列表
 */
CmsSite.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CmsSite.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CmsSite.initColumn();
    var table = new BSTable(CmsSite.id, "/cmsSite/list", defaultColunms);
    table.setPaginationType("client");
    CmsSite.table = table.init();
});
