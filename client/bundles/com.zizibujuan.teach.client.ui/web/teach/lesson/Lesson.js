define(["dojo/_base/declare",
        "dojo/_base/lang",
        "dojo/dom-form",
        "dojo/dom-construct",
        "dojo/on",
        "dojo/aspect",
        "dojo/store/JsonRest",
        "dojo/store/Observable",
        "dijit/registry",
        "dijit/_WidgetBase",
        "dijit/_TemplatedMixin",
        "dijit/_WidgetsInTemplateMixin",
        "dijit/form/Button",
        "dijit/form/ValidationTextBox",
        "dojo/text!teach/templates/LessonNewForm.html",
        "dgrid/Grid",
        "dgrid/extensions/Pagination"], function(
        		declare,
        		lang,
        		domForm,
        		domConstruct,
        		on,
        		aspect,
        		JsonRest,
        		Observable,
        		registry,
        		_WidgetBase,
        		_TemplatedMixin,
        		_WidgetsInTemplateMixin,
        		Button,
        		ValidationTextBox,
        		lessonNewFormTemplate,
        		Grid,
        		Pagination){
	
	return declare("teach.lesson.Lesson", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {
		
		templateString: lessonNewFormTemplate,
		
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
			var columns = [{
				label: "名称",
				field: "name",
				sortable: false
			},{
				label: "操作 ",
				field: "id",
				sortable: false,
				renderCell: function(object, value, node, options){
					var btnPPT = new Button({
						title: "ppt"
					}, node.appendChild(domConstruct.create("div", {})));
					btnPPT.on("click", function(e){
						// TODO:弹出对话框
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