define(["dojo/_base/declare",
        "dojo/_base/lang",
        "dojo/on",
        "dojo/json",
        "dojo/dom-form",
        "dojo/dom-attr",
        "dojo/request/xhr",
        "dijit/_WidgetBase", 
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dojo/i18n!teach/nls/CourseNewForm",
        "dojo/text!teach/templates/CourseNewForm.html",
        "dijit/form/ValidationTextBox",
        "dijit/form/SimpleTextarea"], function(
		declare,
		lang,
		on,
		JSON,
		domForm,
		domAttr,
		xhr,
		_WidgetBase,
		_TemplatedMixin,
		_WidgetsInTemplateMixin,
		courseNewFormMessages,
		courseNewFormTemplate){
	
	return declare("teach.courses.CourseNewForm", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {
		templateString: courseNewFormTemplate,
		
		labelName: courseNewFormMessages.label_name,
		_setLabelNameAttr: { node: "lblName", attribute: "innerHTML" },
		
		labelDesc: courseNewFormMessages.label_desc,
		_setLabelDescAttr: { node: "lblDesc", attribute: "innerHTML" },
		
		buttonSave: courseNewFormMessages.button_save,
		_setButtonSaveAttr: { node: "btnCreate", attribute: "innerHTML" },
		
		userId: null,
		
		url: "/courses",
		
		checkNameUrl: "/courses/check-name",
		
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
						var courseId = data.id;
						window.location.href = this.url + "/" + courseId + "/lessons/new";
					}), lang.hitch(this, function(error){
						// 提示错误信息
						console.error(error);
					}));
				}))
			);
		},
		
		_checkNameUnique: function(value, constraints){
			if(value == ""){
				domAttr.set(this.btnCreate, "disabled", true);
				return true;
			}
			
			var data = {value: value};
			
			var serverIsValid = true;
			xhr.post(this.checkNameUrl + "?owner=" + this.userId, {
				handleAs: "json",
				data: JSON.stringify(data),
				sync: true
			}).then(lang.hitch(this, function(data){
				domAttr.set(this.btnCreate, "disabled", false);
				serverIsValid = true;
			}), lang.hitch(this, function(error){
				domAttr.set(this.btnCreate, "disabled", true);
				serverIsValid = false;
			}));
			return serverIsValid;
		}
	});
});