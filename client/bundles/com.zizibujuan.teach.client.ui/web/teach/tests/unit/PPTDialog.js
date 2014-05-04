define(["intern!object",
        "intern/chai!assert",
        "teach/lessons/PPTDialog"], function(
        		registerSuite,
        		assert,
        		PPTDialog){
	
	var container, pptDialog;
	
	registerSuite({
		name: "ppt dialog",
		setup: function(){
			container = document.createElement("div");
			document.body.appendChild(container);
			pptDialog = new PPTDialog();
		},
		
		"create": function(){
			pptDialog.show();
			assert.isTrue(container.childElementCount > 0);
			assert.isNotNull(pptDialog);
			debugger;
		},
		
		teardown: function(){
			pptDialog.destroyRecursive();
			container.parentNode.removeChild(container);
		}
		
	});
	
});