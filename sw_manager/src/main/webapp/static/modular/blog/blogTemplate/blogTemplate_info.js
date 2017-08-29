/**
 * 初始化博客模板详情对话框
 */
var BlogTemplateInfoDlg = {
    blogTemplateInfoData : {}
};

/**
 * 清除数据
 */
BlogTemplateInfoDlg.clearData = function() {
    this.blogTemplateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogTemplateInfoDlg.set = function(key, val) {
    this.blogTemplateInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogTemplateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BlogTemplateInfoDlg.close = function() {
    parent.layer.close(window.parent.BlogTemplate.layerIndex);
}

/**
 * 收集数据
 */
BlogTemplateInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
BlogTemplateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogTemplate/add", function(data){
        Feng.success("添加成功!");
        window.parent.BlogTemplate.table.refresh();
        BlogTemplateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogTemplateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BlogTemplateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogTemplate/update", function(data){
        Feng.success("修改成功!");
        window.parent.BlogTemplate.table.refresh();
        BlogTemplateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogTemplateInfoData);
    ajax.start();
}

$(function() {

});
