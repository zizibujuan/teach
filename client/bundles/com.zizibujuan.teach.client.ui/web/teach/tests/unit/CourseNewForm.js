define(["intern!object",
        "intern/chai!assert",
        "dojo/dom-attr",
        "teach/course/CourseNewForm"], function(
        		registerSuite,
        		assert,
        		domAttr,
        		CourseNewForm){
	
	var container, courseNewForm;

	registerSuite({
		name: "Course New Form",
		setup: function(){
			container = document.createElement("div");
			document.body.appendChild(container);
			
			courseNewForm = new CourseNewForm({
				_checkNameUnique: function(){
					return true;
				}
			}/*如果直接将container放在这里，会将container从body中移除，why?*/);
			courseNewForm.placeAt(container);
			courseNewForm.startup();
		},
		
		"create": function(){
			
			assert.isTrue(container.childElementCount > 0);
			assert.isTrue(domAttr.get(courseNewForm.btnCreate, "disabled"));
		},
		
		teardown: function(){
			courseNewForm.destroyRecursive();
			container.parentNode.removeChild(container);
		}
	});
	
});