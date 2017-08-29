/**
 * 类别管理初始化
 */
var CmsCategory = {
    id: "CmsCategoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CmsCategory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
         {title: '标题', field: 'name', align: 'center', valign: 'middle', sortable: true},
         {title: '模块', field: 'module', align: 'center', valign: 'middle', sortable: true},
         {title: '排序', field: 'orderno', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
CmsCategory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CmsCategory.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加类别
 */
CmsCategory.openAddCmsCategory = function () {
    var index = layer.open({
        type: 2,
        title: '添加类别',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cmsCategory/cmsCategory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看类别详情
 */
CmsCategory.openCmsCategoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '类别详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cmsCategory/cmsCategory_update/' + CmsCategory.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除类别
 */
CmsCategory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cmsCategory/delete", function (data) {
            Feng.success("删除成功!");
            CmsCategory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询类别列表
 */
CmsCategory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CmsCategory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CmsCategory.initColumn();
    var table = new BSTable(CmsCategory.id, "/cmsCategory/list", defaultColunms);
    table.setPaginationType("client");
    CmsCategory.table = table.init();
});
