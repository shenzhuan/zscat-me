/**
 * 内容管理初始化
 */
var CmsArticle = {
    id: "CmsArticleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CmsArticle.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
         {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
         {title: '类别', field: 'categoryname', align: 'center', valign: 'middle', sortable: true},
         {title: '创建时间', field: 'createdate', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
CmsArticle.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CmsArticle.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加内容
 */
CmsArticle.openAddCmsArticle = function () {
    var index = layer.open({
        type: 2,
        title: '添加内容',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cmsArticle/cmsArticle_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看内容详情
 */
CmsArticle.openCmsArticleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '内容详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cmsArticle/cmsArticle_update/' + CmsArticle.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除内容
 */
CmsArticle.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cmsArticle/delete", function (data) {
            Feng.success("删除成功!");
            CmsArticle.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询内容列表
 */
CmsArticle.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CmsArticle.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CmsArticle.initColumn();
    var table = new BSTable(CmsArticle.id, "/cmsArticle/list", defaultColunms);
    table.setPaginationType("client");
    CmsArticle.table = table.init();
});
