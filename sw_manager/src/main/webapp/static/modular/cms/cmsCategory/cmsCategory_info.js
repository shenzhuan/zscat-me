/**
 * 初始化类别详情对话框
 */
var CmsCategoryInfoDlg = {
    cmsCategoryInfoData : {}
};

/**
 * 清除数据
 */
CmsCategoryInfoDlg.clearData = function() {
    this.cmsCategoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsCategoryInfoDlg.set = function(key, val) {
    this.cmsCategoryInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsCategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CmsCategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.CmsCategory.layerIndex);
}

/**
 * 收集数据
 */
CmsCategoryInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
CmsCategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsCategory/add", function(data){
        Feng.success("添加成功!");
        window.parent.CmsCategory.table.refresh();
        CmsCategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsCategoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CmsCategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsCategory/update", function(data){
        Feng.success("修改成功!");
        window.parent.CmsCategory.table.refresh();
        CmsCategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsCategoryInfoData);
    ajax.start();
}

$(function() {

});
