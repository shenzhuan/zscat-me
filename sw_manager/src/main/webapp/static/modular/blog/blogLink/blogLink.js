/**
 * 博客友链管理初始化
 */
var BlogLink = {
    id: "BlogLinkTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BlogLink.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'linkname', align: 'center', valign: 'middle', sortable: true},
        {title: 'URL', field: 'linkurl', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'orderno', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
BlogLink.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BlogLink.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加博客友链
 */
BlogLink.openAddBlogLink = function () {
    var index = layer.open({
        type: 2,
        title: '添加博客友链',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blogLink/blogLink_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看博客友链详情
 */
BlogLink.openBlogLinkDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '博客友链详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blogLink/blogLink_edit/' + BlogLink.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除博客友链
 */
BlogLink.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/blogLink/delete", function (data) {
            Feng.success("删除成功!");
            BlogLink.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询博客友链列表
 */
BlogLink.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BlogLink.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BlogLink.initColumn();
    var table = new BSTable(BlogLink.id, "/blogLink/list", defaultColunms);
    table.setPaginationType("client");
    BlogLink.table = table.init();
});
