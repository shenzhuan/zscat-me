/**
 * 初始化博客友链详情对话框
 */
var BlogLinkInfoDlg = {
    blogLinkInfoData : {}
};

/**
 * 清除数据
 */
BlogLinkInfoDlg.clearData = function() {
    this.blogLinkInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogLinkInfoDlg.set = function(key, val) {
    this.blogLinkInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogLinkInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BlogLinkInfoDlg.close = function() {
    parent.layer.close(window.parent.BlogLink.layerIndex);
}

/**
 * 收集数据
 */
BlogLinkInfoDlg.collectData = function() {
    this.set('id').set('linkname').set('orderno').set('linkurl');
}

/**
 * 提交添加
 */
BlogLinkInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogLink/add", function(data){
        Feng.success("添加成功!");
        window.parent.BlogLink.table.refresh();
        BlogLinkInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogLinkInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BlogLinkInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogLink/edit", function(data){
        Feng.success("修改成功!");
        window.parent.BlogLink.table.refresh();
        BlogLinkInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogLinkInfoData);
    ajax.start();
}

$(function() {

});
