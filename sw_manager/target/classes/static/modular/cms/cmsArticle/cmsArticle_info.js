/**
 * 初始化内容详情对话框
 */
var CmsArticleInfoDlg = {
    cmsArticleInfoData : {}
};

/**
 * 清除数据
 */
CmsArticleInfoDlg.clearData = function() {
    this.cmsArticleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsArticleInfoDlg.set = function(key, val) {
    this.cmsArticleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsArticleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CmsArticleInfoDlg.close = function() {
    parent.layer.close(window.parent.CmsArticle.layerIndex);
}

/**
 * 收集数据
 */
CmsArticleInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
CmsArticleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsArticle/add", function(data){
        Feng.success("添加成功!");
        window.parent.CmsArticle.table.refresh();
        CmsArticleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsArticleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CmsArticleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsArticle/update", function(data){
        Feng.success("修改成功!");
        window.parent.CmsArticle.table.refresh();
        CmsArticleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsArticleInfoData);
    ajax.start();
}

$(function() {

});
