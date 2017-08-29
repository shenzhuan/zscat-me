/**
 * 初始化博客详情对话框
 */
var BlogInfoDlg = {
    blogInfoData : {}
};

/**
 * 清除数据
 */
BlogInfoDlg.clearData = function() {
    this.blogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogInfoDlg.set = function(key, val) {
    this.blogInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BlogInfoDlg.close = function() {
    parent.layer.close(window.parent.Blog.layerIndex);
}

/**
 * 收集数据
 */
BlogInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
BlogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blog/add", function(data){
        Feng.success("添加成功!");
        window.parent.Blog.table.refresh();
        BlogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BlogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blog/edit", function(data){
        Feng.success("修改成功!");
        window.parent.Blog.table.refresh();
        BlogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogInfoData);
    ajax.start();
}

$(function() {

});
