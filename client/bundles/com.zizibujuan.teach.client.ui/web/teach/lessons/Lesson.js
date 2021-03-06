define(["dojo/_base/declare",
        "dojo/_base/lang",
        "dojo/dom-attr",
        "dojo/dom-form",
        "dojo/dom-construct",
        "dojo/on",
        "dojo/aspect",
        "dojo/request/xhr",
        "dojo/store/JsonRest",
        "dojo/store/Observable",
        "dijit/registry",
        "dijit/_WidgetBase",
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dijit/form/Button",
        "dijit/form/ValidationTextBox",
        "dojo/i18n!teach/nls/Lesson",
        "dojo/text!teach/templates/LessonNewForm.html",
        "dgrid/Grid",
        "dgrid/extensions/Pagination",
        "teach/lessons/PPTDialog"], function(
        		declare,
        		lang,
        		domAttr,
        		domForm,
        		domConstruct,
        		on,
        		aspect,
        		xhr,
        		JsonRest,
        		Observable,
        		registry,
        		_WidgetBase,
        		_TemplatedMixin,
        		_WidgetsInTemplateMixin,
        		Button,
        		ValidationTextBox,
        		lessonMessages,
        		lessonNewFormTemplate,
        		Grid,
        		Pagination,
        		PPTDialog){
	
	return declare("teach.lessons.Lesson", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {
		
		templateString: lessonNewFormTemplate,
		
		labelName: lessonMessages.label_name,
		_setLabelNameAttr: { node: "lblName", attribute: "innerHTML" },
		
		buttonSave: lessonMessages.button_save,
		_setButtonSaveAttr: { node: "btnCreate", attribute: "innerHTML" },
		
		courseId: null,
		
		url: "",
		
		lessonStore: null,
		
		checkNameUrl: "",
		
		postCreate: function(){
			this.inherited(arguments);
			
			this.url = "/courses/" + this.courseId + "/lessons";
			this.checkNameUrl = this.url + "/check-name";
			
			this.lessonStore = new Observable(new JsonRest({
				target: this.url
			}));
			
			this.txtName.validator = lang.hitch(this, this._checkNameUnique);
			// 使用store更好些
			this.own(
				on(this.btnCreate, "click", lang.hitch(this, this._saveHandler))
			);
		},
		
		startup: function(){
			this.inherited(arguments);
			this._createGrid();
		},
		
		_saveHandler: function(){
			this.lessonStore.add(domForm.toObject(this.form))
			.then(lang.hitch(this, function(data){
				
			}), lang.hitch(this, function(error){
				
			}));
		},
		
		_createGrid: function(){
			var self = this;
			var columns = [{
				label: lessonMessages.column_name,
				field: "name",
				sortable: false
			},{
				label: lessonMessages.column_content,
				field: "id",
				sortable: false,
				renderCell: function(object, value, node, options){
					var btnPPT = new Button({
						label: lessonMessages.column_ppt
					}, node.appendChild(domConstruct.create("div", {})));
					btnPPT.on("click", function(e){
						// TODO:弹出对话框
						var dialog = new PPTDialog({
							courseId: self.courseId,
							lessonId: value
						});
						dialog.show();
					});
				}
			}];
			
			var grid = this.grid = new (declare([Grid, Pagination]))({
				store: this.lessonStore,
				columns: columns,
				rowsPerPage: 30
			}, this.gridDiv);
			
			aspect.before(grid, "removeRow", function(rowElement){
				registry.findWidgets(rowElement).forEach(function(w, index){
					widget.destroyRecursive();
				});
			});
		},
		
		_checkNameUnique: function(value, constraints){
			if(value == ""){
				domAttr.set(this.btnCreate, "disabled", true);
				return true;
			}
			
			var data = {value: value};
			
			var serverIsValid = true;
			xhr.post(this.checkNameUrl, {
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