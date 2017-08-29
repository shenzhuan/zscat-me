/**
 * 博客模板管理初始化
 */
var BlogTemplate = {
    id: "BlogTemplateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BlogTemplate.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
        {title: '点击量', field: 'clickhit', align: 'center', valign: 'middle', sortable: true},
        {title: '发布时间', field: 'releasedate', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
BlogTemplate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BlogTemplate.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加博客模板
 */
BlogTemplate.openAddBlogTemplate = function () {
    var index = layer.open({
        type: 2,
        title: '添加博客模板',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blogTemplate/blogTemplate_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看博客模板详情
 */
BlogTemplate.openBlogTemplateDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '博客模板详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blogTemplate/blogTemplate_update/' + BlogTemplate.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除博客模板
 */
BlogTemplate.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/blogTemplate/delete", function (data) {
            Feng.success("删除成功!");
            BlogTemplate.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询博客模板列表
 */
BlogTemplate.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BlogTemplate.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BlogTemplate.initColumn();
    var table = new BSTable(BlogTemplate.id, "/blogTemplate/list", defaultColunms);
    table.setPaginationType("client");
    BlogTemplate.table = table.init();
});
