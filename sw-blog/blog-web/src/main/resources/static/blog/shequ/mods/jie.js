/**

 @Name: 求解板块

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
    jieda: $('#jieda')
    ,content: $('#L_content')
    ,jiedaCount: $('#jiedaCount')
  };

  //提交回答
  zsCat.form['/jie/reply/'] = function(data, required){
    var tpl = '<li>\
      <div class="detail-about detail-about-reply">\
        <a class="jie-user" href="/user/">\
          <img src="{{= d.user.avatar}}" alt="{{= d.user.username}}">\
          <cite>{{d.user.username}}</cite>\
        </a>\
        <div class="detail-hits">\
          <span>刚刚</span>\
        </div>\
      </div>\
      <div class="detail-body jieda-body">\
        {{ d.content}}\
      </div>\
    </li>'
    data.content = zsCat.content(data.content);
    laytpl(tpl).render($.extend(data, {
      user: layui.cache.user
    }), function(html){
      required[0].value = '';
      dom.jieda.find('.zsCat-none').remove();
      dom.jieda.append(html);
      
      var count = dom.jiedaCount.text()|0;
      dom.jiedaCount.html(++count);
    });
  };

  //求解管理
  gather.jieAdmin = {
    //删求解
    del: function(div){
      layer.confirm('确认删除该求解么？', function(index){
        layer.close(index);
        zsCat.json('/api/jie-delete/', {
          id: div.data('id')
        }, function(res){
          if(res.status === 0){
            location.href = '/jie/';
          } else {
            layer.msg(res.msg);
          }
        });
      });
    }
    
    //设置置顶、状态
    ,set: function(div){
      var othis = $(this);
      zsCat.json('/api/jie-set/', {
        id: div.data('id')
        ,rank: othis.attr('rank')
        ,field: othis.attr('field')
      }, function(res){
        if(res.status === 0){
          location.reload();
        }
      });
    }
  };
  $('.jie-admin').on('click', function(){
    var othis = $(this), type = othis.attr('type');
    gather.jieAdmin[type].call(this, othis.parent());
  });

  //解答操作
  gather.jiedaActive = {
    zan: function(li){ //赞
      var othis = $(this), ok = othis.hasClass('zanok');
      zsCat.json('/api/jieda-zan/', {
        ok: ok
        ,id: li.data('id')
      }, function(res){
        if(res.status === 0){
          var zans = othis.find('em').html()|0;
          othis[ok ? 'removeClass' : 'addClass']('zanok');
          othis.find('em').html(ok ? (--zans) : (++zans));
        } else {
          layer.msg(res.msg);
        }
      });
    }
    ,reply: function(li){ //回复
      var val = dom.content.val();
      var aite = '@'+ li.find('.jie-user cite i').text().replace(/\s/g, '');
      dom.content.focus()
      if(val.indexOf(aite) !== -1) return;
      dom.content.val(aite +' ' + val);
    }
    ,accept: function(li){ //采纳
      var othis = $(this);
      layer.confirm('是否采纳该回答为最佳答案？', function(index){
        layer.close(index);
        zsCat.json('/api/jieda-accept/', {
          id: li.data('id')
        }, function(res){
          if(res.status === 0){
            $('.jieda-accept').remove();
            li.addClass('jieda-daan');
            li.find('.detail-about').append('<i class="iconfont icon-caina" title="最佳答案"></i>');
          } else {
            layer.msg(res.msg);
          }
        });
      });
    }
    ,edit: function(li){ //编辑
      zsCat.json('/jie/getDa/', {
        id: li.data('id')
      }, function(res){
        var data = res.rows;
        layer.prompt({
         formType: 2
         ,value: data.content
         ,maxlength: 100000
        }, function(value, index){
          zsCat.json('/jie/updateDa/', {
            id: li.data('id')
            ,content: value
          }, function(res){
            layer.close(index);
            li.find('.detail-body').html(zsCat.content(value));
          });
        });
      });
    }
    ,del: function(li){ //删除
      layer.confirm('确认删除该回答么？', function(index){
        layer.close(index);
        zsCat.json('/api/jieda-delete/', {
          id: li.data('id')
        }, function(res){
          if(res.status === 0){
            var count = dom.jiedaCount.text()|0;
            dom.jiedaCount.html(--count);
            li.remove();
            //如果删除了最佳答案
            if(li.hasClass('jieda-daan')){
              $('.jie-status').removeClass('jie-status-ok').text('求解中');
            }
          } else {
            layer.msg(res.msg);
          }
        });
      });    
    }
  };
  $('.jieda-reply span').on('click', function(){
    var othis = $(this), type = othis.attr('type');
    gather.jiedaActive[type].call(this, othis.parents('li'));
  });

  exports('jie', null);
});