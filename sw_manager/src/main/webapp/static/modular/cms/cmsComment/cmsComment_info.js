/**
 * 初始化评论详情对话框
 */
var CmsCommentInfoDlg = {
    cmsCommentInfoData : {}
};

/**
 * 清除数据
 */
CmsCommentInfoDlg.clearData = function() {
    this.cmsCommentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsCommentInfoDlg.set = function(key, val) {
    this.cmsCommentInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CmsCommentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CmsCommentInfoDlg.close = function() {
    parent.layer.close(window.parent.CmsComment.layerIndex);
}

/**
 * 收集数据
 */
CmsCommentInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
CmsCommentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsComment/add", function(data){
        Feng.success("添加成功!");
        window.parent.CmsComment.table.refresh();
        CmsCommentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsCommentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CmsCommentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cmsComment/update", function(data){
        Feng.success("修改成功!");
        window.parent.CmsComment.table.refresh();
        CmsCommentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cmsCommentInfoData);
    ajax.start();
}

$(function() {

});
