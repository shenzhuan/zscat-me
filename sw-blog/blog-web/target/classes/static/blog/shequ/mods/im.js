/** 
  社区IM 
*/

layui.define('layim', function(exports){
  var layim = layui.layim, user = layui.cache.user;
  var $ = layui.jquery;
  
  return;
  
  layim.config({
    //配置客户信息
    mine: {
      "username": user.username || "访客"
      ,"id": user.uid
      ,"status": "online"
      ,"sign": user.sign || ''
      ,"avatar": user.avatar
    }
    //开启客服模式
    ,brief: true
    ,minRight: '90px'
  });

  //打开一个客服面板
  var XiaoJiang = {
    name: 'LayIM-小酱' //名称
    ,type: 'kefu' //聊天类型
    ,avatar: 'http://res.layui.com/images/zsCat/avatar/00.jpg'
    ,id: -1 //定义唯一的id方便你处理信息
  };
  layim.chat(XiaoJiang);
  
  layim.setChatMin(); //收缩聊天面板
  
  var autoReply = function(code){
    var content = [
      '输入以下指令吧亲，我将为您服务'
      ,'1. 发送 add （自动去发表帖子），'
      ,'2. 发送 layim （获得LayIM的下载）'
      ,'3. 发送 vip （查询我vip信息）'
      ,'4. 发送 close （可将小酱客服关闭）'
      ,'5. 发送 pay （可获得授权扫码图）'
      ,'6. 发送 福利 （可随机获得一个福利）'
      ,'7. 发送 翠花 （可召唤出我的好姐妹翠花）'
      ,'8. 发送 help （展现所有指令），'
      ,'本酱还有许多隐藏功能喔，比如你可以问：“心姐是妹纸吗？”，其他的我是不会告诉你的 face[酷] 小酱还在努力get各种新技能'
    ].join('\n');
    
    code = (code||'').replace(/\s/g, '');
    
    if(/贤心|心姐/.test(code)){
      code = 'xianxin';
    } else if(/发布/.test(code)){
      code = 'fabu';
    } else if(/layui/.test(code)){
      code = 'layui';
    }
    
    switch(code){ 
      case 'add': 
        if(user.uid == -1){
          content = '亲呀，你都还没登入，是不能发表帖子的。';
        } else {
          layer.open({
            type: 2
            ,content: '/jie/add'
            ,title: '发表帖子'
            ,scrollbar: false
            ,area: ['100%', '100%']
          });
          content = '已为您打开发帖页面';
        }
      break;
      case 'layim':
        layer.open({
          type: 2
          ,content: 'http://layim.layui.com/?from=xiaojiang'
          ,title: 'LayIM - 拉近用户在Web间的距离'
          ,scrollbar: false
          ,area: ['100%', '100%']
        });
        layer.msg('一言不合就为你打开了layim主页', {icon: 6});
        content = '亲呀，layim目前并不开源，所以如果你要下载，是要经过授权的。\n你会支持一下小酱吗？face[爱你] ';
      break;
      case 'vip':
        if(user.uid == -1){
          content = '亲呀，你得先登入才能查询你的vip';
        } else if(user.sum > 0){
          if(user.vip == 0){
            content = 'Hi，可爱的VIP0，你的总捐赠额度为：￥'+ user.sum;
          } else if(user.vip > 0 ) {
            content = '您是尊贵的VIP'+user.vip+'，你的总捐赠额度为：￥'+ user.sum;
          }
        } else {
          content = '你还不是我们的VIP，好桑心。。face[悲伤] ';
        }
      break;
      case 'close':
        layer.close($('.layui-layim-chat').attr('times'));
        return layer.msg('所以你就这样把小酱关闭了？', {icon: 5});
      break;
      case 'pay':
        content = 'img[http://res.layui.com/images/pay/vip.jpg]';
      break;
      case '福利':
        content = [
          'img[http://www.sj88.com/attachments/201412/18/14/qi1m9c5t10.jpg]'
          ,'img[http://www.sj88.com/attachments/201412/18/14/rsqj7a7cl.png]'
          ,'img[http://file.ynet.com/2/1603/09/10995825.jpg]\n凤姐的一个吻'
          ,'face[心]face[心]face[心]face[心]face[心]face[心]face[心]face[心]face[心]face[心]face[心]face[心]\n小酱爱你摸摸哒~'
          ,'file(javascript:layer.msg("亲呀，别想多了。。。");)[苍老师.avi]'
        ][Math.random()*5|0]
      break;
      case '翠花':
        return layim.chat({
          name: 'LayIM-翠花'
          ,type: 'kefu'
          ,avatar: 'http://www.qqwangming.org/uploads/allimg/160519/1051443533-10.jpg'
          ,id: -2
        });
      break;
      
      case 'xianxin':
        content = '咦，你是说贤心？他可是标准的汉子。这是他博客：a(http://sentsin.com)[http://sentsin.com]，我知道的就只有这么多了，再说下去会被打。。'
      break;
      case 'fabu':
        content = '这个。。。发布我们会提前告之的。'
      break;
      case 'layui':
        content = '亲呀，layui正式版估计快要发布了。'
      break;
      
      
      
      case 'help':
        content = content;
      break;
      case '':
        content = 'Hi，'+ user.username + '我是小酱，LayIM的御用机器人，很高兴见到你face[微笑]\n' + content;
      break;
      default:
        content = '( ′◔ ‸◔`)，人类的语言真是太太太复杂了，我都听不懂你在说啥。。face[思考]\n嗯，我还要加倍学习！face[悲伤] face[悲伤] face[悲伤]\n你可以回复 help （那我可就聪明了）';
      break;
    }
    
    setTimeout(function(){
      layim.getMessage({
        username: XiaoJiang.name
        ,avatar: XiaoJiang.avatar
        ,id: -1
        ,type: "kefu"
        ,content: content
      });
    }, 300 + Math.random()*700);
  };
  
  var localzsCat = layui.data('zsCat');
  if(!localzsCat.imXiaojiang){
    autoReply();
    layui.data('zsCat', {
      key: 'imXiaojiang'
      ,value: true
    });
  }
  
  layim.on('sendMessage', function(res){
    var mine = res.mine, to = res.to;
    if(to.id === -1){
      autoReply(mine.content);
    } else if(to.id === -2){
      setTimeout(function(){
        layim.getMessage({
          username: 'LayIM-翠花'
          ,avatar: 'http://www.qqwangming.org/uploads/allimg/160519/1051443533-10.jpg'
          ,id: -2
          ,type: "kefu"
          ,content: '翠花目前的智力为0，你跟她说话等于对牛弹琴'
        });
      }, 500);
    }
  });
  
  
  exports('im', {});
});