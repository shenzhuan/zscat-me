/**
 * 友链管理初始化
 */
var CmsLink = {
    id: "CmsLinkTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CmsLink.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
         {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
         {title: 'URL', field: 'href', align: 'center', valign: 'middle', sortable: true},
         {title: '颜色', field: 'color', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
CmsLink.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CmsLink.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加友链
 */
CmsLink.openAddCmsLink = function () {
    var index = layer.open({
        type: 2,
        title: '添加友链',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cmsLink/cmsLink_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看友链详情
 */
CmsLink.openCmsLinkDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '友链详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cmsLink/cmsLink_update/' + CmsLink.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除友链
 */
CmsLink.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cmsLink/delete", function (data) {
            Feng.success("删除成功!");
            CmsLink.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询友链列表
 */
CmsLink.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CmsLink.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CmsLink.initColumn();
    var table = new BSTable(CmsLink.id, "/cmsLink/list", defaultColunms);
    table.setPaginationType("client");
    CmsLink.table = table.init();
});
