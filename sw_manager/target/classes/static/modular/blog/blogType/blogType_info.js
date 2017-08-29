/**
 * 初始化博客类型详情对话框
 */
var BlogTypeInfoDlg = {
    blogTypeInfoData : {}
};

/**
 * 清除数据
 */
BlogTypeInfoDlg.clearData = function() {
    this.blogTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogTypeInfoDlg.set = function(key, val) {
    this.blogTypeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlogTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BlogTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.BlogType.layerIndex);
}

/**
 * 收集数据
 */
BlogTypeInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
BlogTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogType/add", function(data){
        Feng.success("添加成功!");
        window.parent.BlogType.table.refresh();
        BlogTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BlogTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blogType/update", function(data){
        Feng.success("修改成功!");
        window.parent.BlogType.table.refresh();
        BlogTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blogTypeInfoData);
    ajax.start();
}

$(function() {

});
