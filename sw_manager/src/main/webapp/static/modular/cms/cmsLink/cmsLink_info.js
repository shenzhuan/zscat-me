/**
 * 初始化友链详情对话框
 */
var CmsLinkInfoDlg = {
    cmsLinkInfoData : {}
};

/**
 * 清除数据
 */
CmsLinkInfoDlg.clearData = function() {
    this.cmsLinkInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsLinkInfoDlg.set = function(key, val) {
    this.cmsLinkInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsLinkInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CmsLinkInfoDlg.close = function() {
    parent.layer.close(window.parent.CmsLink.layerIndex);
}

/**
 * 收集数据
 */
CmsLinkInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
CmsLinkInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsLink/add", function(data){
        Feng.success("添加成功!");
        window.parent.CmsLink.table.refresh();
        CmsLinkInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsLinkInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CmsLinkInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsLink/update", function(data){
        Feng.success("修改成功!");
        window.parent.CmsLink.table.refresh();
        CmsLinkInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsLinkInfoData);
    ajax.start();
}

$(function() {

});
