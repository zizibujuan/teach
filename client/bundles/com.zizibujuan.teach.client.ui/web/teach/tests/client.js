define({
	loader: {
		// location of all the packages, relative to client.html
		baseUrl: "../../.."
	},

	useLoader: {
		'host-node': 'dojo/dojo',
		'host-browser': 'node_modules/dojo/dojo.js'
	},

	// Non-functional test suites
	suites: [ "teach/tests/unit/all" ]
});