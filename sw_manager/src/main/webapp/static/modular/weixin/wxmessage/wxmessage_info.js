/**
 * 初始化微信公众号详情对话框
 */
var WxmessageInfoDlg = {
    wxmessageInfoData : {}
};

/**
 * 清除数据
 */
WxmessageInfoDlg.clearData = function() {
    this.wxmessageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxmessageInfoDlg.set = function(key, val) {
    this.wxmessageInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxmessageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WxmessageInfoDlg.close = function() {
    parent.layer.close(window.parent.Wxmessage.layerIndex);
}

/**
 * 收集数据
 */
WxmessageInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
WxmessageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxmessage/add", function(data){
        Feng.success("添加成功!");
        window.parent.Wxmessage.table.refresh();
        WxmessageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxmessageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WxmessageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxmessage/update", function(data){
        Feng.success("修改成功!");
        window.parent.Wxmessage.table.refresh();
        WxmessageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxmessageInfoData);
    ajax.start();
}

$(function() {

});
