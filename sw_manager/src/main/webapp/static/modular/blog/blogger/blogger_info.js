/**
 * 初始化博客用户详情对话框
 */
var BloggerInfoDlg = {
    bloggerInfoData : {}
};

/**
 * 清除数据
 */
BloggerInfoDlg.clearData = function() {
    this.bloggerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BloggerInfoDlg.set = function(key, val) {
    this.bloggerInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BloggerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BloggerInfoDlg.close = function() {
    parent.layer.close(window.parent.Blogger.layerIndex);
}

/**
 * 收集数据
 */
BloggerInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
BloggerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogger/add", function(data){
        Feng.success("添加成功!");
        window.parent.Blogger.table.refresh();
        BloggerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bloggerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BloggerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogger/update", function(data){
        Feng.success("修改成功!");
        window.parent.Blogger.table.refresh();
        BloggerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bloggerInfoData);
    ajax.start();
}

$(function() {

});
