define(["dojo/_base/declare",
        "dojo/_base/lang",
        "dojo/on",
        "dojo/dom-form",
        "dojo/request/xhr",
        "dijit/_WidgetBase", 
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dojo/text!teach/templates/CourseNewForm.html",
        "dijit/form/ValidationTextBox",
        "dijit/form/SimpleTextarea"], function(
		declare,
		lang,
		on,
		domForm,
		xhr,
		_WidgetBase,
		_TemplatedMixin,
		_WidgetsInTemplateMixin,
		courseNewFormTemplate){
	
	return declare("teach.course.CourseNewForm", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {
		templateString: courseNewFormTemplate,
		
		userId: null,
		
		url: "/courses",
		
		checkNameUrl: "/courses/check-name",
		
		redirectUrl: "",
		
		postCreate: function(){
			this.inherited(arguments);
			
			
			// 唯一性校验
			this.txtCourseName.validator = lang.hitch(this, this._checkNameUnique);
			// 提交操作
			this.own(
				on(this.btnCreate, "click", lang.hitch(this,function(){
					xhr.post(this.url, {
						handleAs: "json",
						data: domForm.toJson(this.form)
					}).then(lang.hitch(this,function(data){
						// 跳转到指定的页面
						window.location.href = this.redirectUrl;
					}), lang.hitch(this, function(error){
						// 提示错误信息
					}));
				}))
			);
		},
		
		_checkNameUnique: function(value, constraints){
			xhr.post(this.checkNameUrl + "?owner=" + this.userId, {
				handleAs: "json",
				sync: true
			}).then(lang.hitch(this, function(data){
				
				return true;
			}), lang.hitch(this, function(error){
				
				return false;
			}));
		}
	});
});