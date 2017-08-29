/**
 * 博客用户管理初始化
 */
var Blogger = {
    id: "BloggerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Blogger.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'username', align: 'center', valign: 'middle', sortable: true},
        {title: 'URL', field: 'imagename', align: 'center', valign: 'middle', sortable: true},
        {title: '昵称', field: 'nickname', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Blogger.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Blogger.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加博客用户
 */
Blogger.openAddBlogger = function () {
    var index = layer.open({
        type: 2,
        title: '添加博客用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blogger/blogger_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看博客用户详情
 */
Blogger.openBloggerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '博客用户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blogger/blogger_update/' + Blogger.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除博客用户
 */
Blogger.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/blogger/delete", function (data) {
            Feng.success("删除成功!");
            Blogger.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询博客用户列表
 */
Blogger.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Blogger.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Blogger.initColumn();
    var table = new BSTable(Blogger.id, "/blogger/list", defaultColunms);
    table.setPaginationType("client");
    Blogger.table = table.init();
});
