/**

 @Name: 用户模块

 */
 
layui.define(['laypage', 'zsCat'], function(exports){

  var $ = layui.jquery;
  var layer = layui.layer;
  var util = layui.util;
  var laytpl = layui.laytpl;
  var form = layui.form;
  var laypage = layui.laypage;
  var zsCat = layui.zsCat;

  var gather = {}, dom = {
    mine: $('#LAY-mine')
    ,mineview: $('.mine-view')
    ,minemsg: $('#LAY_minemsg')
    ,infobtn: $('#LAY_btninfo')
  };

  //我的相关数据
  gather.minelog = {};
  gather.mine = function(index, type){
    if(!type) return;
    var tpl = [
      //求解
      '{{# for(var i = 0; i < d.rows.length; i++){ }}\
      <li>\
        {{# if(d.rows[i].status == 1){ }}\
        <span class="zsCat-jing">精</span>\
        {{# } }}\
        {{# if(d.rows[i].accept >= 0){ }}\
        <span class="jie-status jie-status-ok">已解决</span>\
        {{# } }}\
        <a class="jie-title" href="/jie/{{d.rows[i].id}}.html" target="_blank">{{= d.rows[i].title}}</a>\
        <i>{{new Date(d.rows[i].time).toLocaleString()}}</i>\
        {{# if(d.rows[i].accept == -1){ }}\
        <a class="mine-edit" href="/jie/edit/{{d.rows[i].id}}">编辑</a>\
        {{# } }}\
        <em>{{d.rows[i].hits}}阅/{{d.rows[i].comment}}答</em>\
      </li>\
      {{# } }}'
    ];
    function view(res){
      var html = laytpl(tpl[index]).render(res);
      dom.mine.find('a').eq(index).find('cite').html(res.count);
      dom.mineview.eq(index).html(res.rows.length === 0 ? '<div class="zsCat-msg">没有相关数据</div>' : html);
    }
    function page(now){
      var curr = now || 1;
      if(gather.minelog[type + 'page-' + curr]){
        view(gather.minelog[type + 'page-' + curr]);
      } else {
        zsCat.json('/api/'+ type +'/', {
          page: curr
        }, function(res){
          view(res);
          gather.minelog[type + 'page-' + curr] = res;
          now || laypage({
            cont: 'LAY-page'
            ,pages: res.pages
            ,skin: 'zsCat'
            ,curr: curr
            ,jump: function(e, first){
              if(!first){
                page(e.curr);
              }
            }
          });
        });
      }
    }
    if(!gather.minelog[type]){
      page();
    }
  };

  //显示当前tab
  gather.tabshow = function(index, hash){
    var a = dom.mine.find('a');
    if(hash){
      a.each(function(i, item){
        if($(this).attr('hash') === hash){
          index = i;
          return false;
        }
      });
    }
    a.eq(index).addClass('tab-this').siblings().removeClass('tab-this');
    dom.mineview.hide();
    dom.mineview.eq(index).show();
    gather.mine(index, a.eq(index).attr('type'));
  };

  dom.mine.find('a').on('click', function(){
    var othis = $(this), index = othis.index();
    var type = othis.attr('type'), hash = othis.attr('hash');
    if(othis.attr('href') !== 'javascript:;'){
      return;
    }
    
    gather.tabshow(index);
    gather.minelog[type] = true;
    if(hash){
      location.hash = hash;
    }
  });
  dom.mine[0] && gather.tabshow(0, location.hash.replace(/^#/, ''));

  //根据ip获取城市
  if($('#LAY_city').val() === ''){
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(){
      $('#LAY_city').val(remote_ip_info.city);
    });
  }

  //上传图片
  if($('.upload-img')[0]){
    layui.use('upload', function(upload){
      var avatarAdd = $('.avatar-add');
      layui.upload({
        elem: '.upload-img input'
        ,method: 'post'
        ,url: '/user/upload/'
        ,before: function(){
          avatarAdd.find('.loading').show();
        }
        ,success: function(res){
          if(res.status == 0){
            $.post('/user/set/', {
              avatar: res.url
            }, function(res){
              location.reload();
            });
          } else {
            layer.msg(res.msg, {icon: 5});
          }
          avatarAdd.find('.loading').hide();
        }
        ,error: function(){
          avatarAdd.find('.loading').hide();
        }
      });
    });
  }

  //提交成功后刷新
  zsCat.form['set-mine'] = function(data, required){
    layer.msg('修改成功', {
      icon: 1
      ,time: 1000
      ,shade: 0.1
    }, function(){
      location.reload();
    });
  }

  //帐号绑定
  $('.acc-unbind').on('click', function(){
    var othis = $(this), type = othis.attr('type');
    layer.confirm('整的要解绑'+ ({
      qq_id: 'QQ'
      ,weibo_id: '微博'
    })[type] + '吗？', {icon: 5}, function(){
      zsCat.json('/api/unbind', {
        type: type
      }, function(res){
        if(res.status === 0){
          layer.alert('已成功解绑。', {
            icon: 1
            ,end: function(){
              location.reload();
            }
          });
        } else {
          layer.msg(res.msg);
        }
      });
    });
  });


  //我的消息
  gather.minemsg = function(){
    var delAll = $('#LAY_delallmsg')
    ,tpl = '{{# var len = d.rows.length;\
    if(len === 0){ }}\
      <div class="zsCat-none">您暂时没有最新消息</div>\
    {{# } else { }}\
      <ul class="mine-msg">\
      {{# for(var i = 0; i < len; i++){ }}\
        <li data-id="{{d.rows[i].id}}"><a href="{{d.rows[i].href}}" target="_blank">{{ d.rows[i].content}}</a><p><span>{{d.rows[i].time}}</span><a href="javascript:;" class="layui-btn layui-btn-small zsCat-delete">删除</a></p></li>\
      {{# } }}\
      </ul>\
    {{# } }}'
    ,delEnd = function(clear){
      if(clear || dom.minemsg.find('.mine-msg li').length === 0){
        dom.minemsg.html('<div class="zsCat-none">您暂时没有最新消息</div>');
      }
    }
    
    
    zsCat.json('/api/msg/', {}, function(res){
      var html = laytpl(tpl).render(res);
      dom.minemsg.html(html);
      if(res.rows.length > 0){
        delAll.removeClass('layui-hide');
      }
    });
    
    //阅读后删除
    dom.minemsg.on('click', '.mine-msg li .zsCat-delete', function(){
      var othis = $(this).parents('li'), id = othis.data('id');
      zsCat.json('/api/msg-del', {id: id}, function(res){
        if(res.status === 0){
          othis.remove();
          delEnd();
        }
      });
    });

    //删除全部
    $('#LAY_delallmsg').on('click', function(){
      var othis = $(this);
      layer.confirm('确定清空吗？', function(index){
        zsCat.json('/api/msg-del', {
          type: 'all'
        }, function(res){
          if(res.status === 0){
            layer.close(index);
            othis.addClass('layui-hide');
            delEnd(true);
          }
        });
      });
    });

  };

  dom.minemsg[0] && gather.minemsg();

  exports('user', null);
  
});