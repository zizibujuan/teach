define(["intern!object",
        "intern/chai!assert",
        "teach/lesson/Lesson"], function(
        		registerSuite,
        		assert,
        		Lesson){
	
	var container, lesson;
	
	registerSuite({
		name: "Lesson",
		setup: function(){
			container = document.createElement("div");
			document.body.appendChild(container);
			lesson = new Lesson({
				_checkNameUnique: function(){
					return true;
				},
				_createGrid: function(){
					
				}
			});
			lesson.placeAt(container);
			lesson.startup();
		},
		
		"create": function(){
			assert.isTrue(container.childElementCount > 0);
			assert.isNotNull(lesson.grid);
			debugger;
		},
		
		teardown: function(){
			lesson.destroyRecursive();
			container.parentNode.removeChild(container);
		}
		
	});
	
});