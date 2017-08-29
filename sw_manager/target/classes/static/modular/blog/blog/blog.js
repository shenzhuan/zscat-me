/**
 * 博客管理初始化
 */
var Blog = {
    id: "BlogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Blog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', align: 'center', valign: 'middle', sortable: true},
        {title: '类别', field: 'typename', align: 'center', valign: 'middle', sortable: true},
        {title: '点击量', field: 'clickhit', align: 'center', valign: 'middle', sortable: true},
        {title: '发布时间', field: 'releasedate', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Blog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Blog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加博客
 */
Blog.openAddBlog = function () {
    var index = layer.open({
        type: 2,
        title: '添加博客',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blog/blog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看博客详情
 */
Blog.openBlogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '博客详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blog/blog_edit/' + Blog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除博客
 */
Blog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/blog/delete", function (data) {
            Feng.success("删除成功!");
            Blog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询博客列表
 */
Blog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Blog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Blog.initColumn();
    var table = new BSTable(Blog.id, "/blog/list", defaultColunms);
    table.setPaginationType("client");
    Blog.table = table.init();
});
