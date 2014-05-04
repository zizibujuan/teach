define(["dojo/_base/declare",
        "dojo/_base/lang",
        "dojo/json",
        "dojo/dom-construct",
        "dojo/request/xhr",
        "dijit/form/TextBox",
        "dijit/form/Button",
        "dijit/Dialog",
        "dojo/i18n!teach/nls/PPTDialog"], function(
		declare,
		lang,
		JSON,
		domConstruct,
		xhr,
		TextBox,
		Button,
		Dialog,
		pptDialogMessages){
	
	return declare("teach.lessons.PPTDialog", [Dialog], {
		
		courseId: null,
		
		lessonId: null,
		
		title: pptDialogMessages.title,
		
		postCreate: function(){
			this.inherited(arguments);
			
			var contentArea = domConstruct.create("div", {
				"class": "dijitDialogPaneContentArea"
			}, this.containerNode);
			var actionBar = domConstruct.create("div", {
				"class": "dijitDialogPaneActionBar"
			}, this.containerNode);
			
			var lblPPT = domConstruct.create("div", {
				innerHTML: pptDialogMessages.label_ppt
			}, contentArea);
			var pptContainer = domConstruct.create("div", {
				style: "position: relative; width: 600px; height: 300px"
			}, contentArea);
			var editor = this.txtPPT = ace.edit(pptContainer);
			editor.setTheme("ace/theme/eclipse");
			// TODO: 动态设置编程语言
			editor.getSession().setMode("ace/mode/java");
			
			var lblCommit = domConstruct.create("div", {
				innerHTML: pptDialogMessages.label_commit
			}, contentArea);
			var commitContainer = domConstruct.create("div", {}, contentArea);
			var txtCommit = this.txtCommit = new TextBox({
				style: "width: 100%"
			}, commitContainer);
			
			
			// 操作按钮
			var btnOk = new Button({
				label: pptDialogMessages.button_save
			});
			var btnCancel = new Button({
				label: pptDialogMessages.button_cancel
			});
			btnOk.on("click", lang.hitch(this, this._onSaveContent));
			btnCancel.on("click", lang.hitch(this, function(e){
				this.hide();
			}));
			
			btnOk.placeAt(actionBar);
			btnCancel.placeAt(actionBar);
			
			// 加载ppt内容
			xhr.get(this.getUrl(), {
				handleAs: "json"
			}).then(lang.hitch(this, function(data){
				editor.setValue(data.content, 1);// 光标移到最后
			}), lang.hitch(this, function(error){
				
			}));
		},
		
		getUrl: function(){
			return "/courses/" + this.courseId + "/lessons/" + this.lessonId + "/ppt";
		},
		
		
		_onSaveContent: function(){
			// 校验ppt内容和提交说明都不能为空
			var formData = {
				content: this.txtPPT.getValue(),
				commitMessage: this.txtCommit.get("value")
			};
			if(!this._validateData(formData)){
				this._showErrors();
				return;
			}
			
			xhr.put(this.getUrl(), {
				data: JSON.stringify(formData)
			}).then(lang.hitch(this, function(data){
				this.hide();
			}), lang.hitch(this, function(error){
				console.error(error);
			}));
		},
		
		_validateData: function(formData){
			// summary:
			//		校验数据的有效性
			
			return true;
		},
		
		_showErrors: function(){
			// summary:
			//		显示错误信息，错误信息缓存在_errors变量中
		}
	
	});
	
});