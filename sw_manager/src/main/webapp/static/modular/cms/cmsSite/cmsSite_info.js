/**
 * 初始化站点详情对话框
 */
var CmsSiteInfoDlg = {
    cmsSiteInfoData : {}
};

/**
 * 清除数据
 */
CmsSiteInfoDlg.clearData = function() {
    this.cmsSiteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsSiteInfoDlg.set = function(key, val) {
    this.cmsSiteInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsSiteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CmsSiteInfoDlg.close = function() {
    parent.layer.close(window.parent.CmsSite.layerIndex);
}

/**
 * 收集数据
 */
CmsSiteInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
CmsSiteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsSite/add", function(data){
        Feng.success("添加成功!");
        window.parent.CmsSite.table.refresh();
        CmsSiteInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsSiteInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CmsSiteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsSite/update", function(data){
        Feng.success("修改成功!");
        window.parent.CmsSite.table.refresh();
        CmsSiteInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsSiteInfoData);
    ajax.start();
}

$(function() {

});
