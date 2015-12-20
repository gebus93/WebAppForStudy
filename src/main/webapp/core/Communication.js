/**
 * Created by Michnik on 22.11.2015.
 */
var Communication = new (function () {
	var self = this;
	var prefix = 'http://localhost:8080/rest/';

	this.post = function (task, data, callback) {
		$.post(prefix + task, data, function (data, status, xhr) {
			callback(xhr.status);
		});
	};
	this.put = function (task, data, callback) {
		$.put(prefix + task, data, function (data) {
			callback(data);
		});
	};
	this.delete = function (task, data, callback) {
		$.delete(prefix + task, data, function (data) {
			callback(data);
		});
	};
	this.get = function (task, data, callback) {
		$.get(prefix + task, data, function (data) {
			callback(data);
		}, 'json');
	};

	jQuery.each(['put', 'delete'], function (i, method) {
		jQuery[method] = function (url, data, callback, type) {
			if (jQuery.isFunction(data)) {
				type = type || callback;
				callback = data;
				data = undefined;
			}

			return jQuery.ajax({
				url: url,
				headers: {
					'application': '1c226459-a721-4337-a7e6-f2864e0186b9'
				},
				type: method,
				dataType: type,
				data: data,
				success: callback
			});
		};
	});

})();