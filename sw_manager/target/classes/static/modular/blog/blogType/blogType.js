/**
 * 博客类型管理初始化
 */
var BlogType = {
    id: "BlogTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BlogType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'typename', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'orderno', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true}

    ];
};

/**
 * 检查是否选中
 */
BlogType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BlogType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加博客类型
 */
BlogType.openAddBlogType = function () {
    var index = layer.open({
        type: 2,
        title: '添加博客类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blogType/blogType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看博客类型详情
 */
BlogType.openBlogTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '博客类型详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blogType/blogType_update/' + BlogType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除博客类型
 */
BlogType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/blogType/delete", function (data) {
            Feng.success("删除成功!");
            BlogType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询博客类型列表
 */
BlogType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BlogType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BlogType.initColumn();
    var table = new BSTable(BlogType.id, "/blogType/list", defaultColunms);
    table.setPaginationType("client");
    BlogType.table = table.init();
});
