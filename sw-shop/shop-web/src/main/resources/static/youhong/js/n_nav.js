// JavaScript Document
var jq = jQuery.noConflict();

jQuery(document).ready(function(){
	jq(".nav").hover(function(){						   
		jq(this).find(".leftNav").show();
	},function(){
		jq(this).find(".leftNav").hide();
	});
});	


