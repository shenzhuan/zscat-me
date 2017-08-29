/**
 * 评论管理初始化
 */
var CmsComment = {
    id: "CmsCommentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CmsComment.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
         {title: '标题', field: 'articleId', align: 'center', valign: 'middle', sortable: true},
         {title: '评论人', field: 'name', align: 'center', valign: 'middle', sortable: true},
         {title: '创建时间', field: 'createdate', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
CmsComment.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CmsComment.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加评论
 */
CmsComment.openAddCmsComment = function () {
    var index = layer.open({
        type: 2,
        title: '添加评论',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cmsComment/cmsComment_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看评论详情
 */
CmsComment.openCmsCommentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '评论详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cmsComment/cmsComment_update/' + CmsComment.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除评论
 */
CmsComment.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cmsComment/delete", function (data) {
            Feng.success("删除成功!");
            CmsComment.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询评论列表
 */
CmsComment.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CmsComment.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CmsComment.initColumn();
    var table = new BSTable(CmsComment.id, "/cmsComment/list", defaultColunms);
    table.setPaginationType("client");
    CmsComment.table = table.init();
});
