/**
 * Created by Michnik on 22.11.2015.
 */
var Communication = new (function () {
	var self = this;
	var prefix = '/rest/';
	var admin = '';//'admin/';

	this.post = function (task, data, callback) {
		$.post(prefix + admin + task, data, function (data) {
			callback(data);
		});
	};
	this.put = function (task, data, callback) {
		$.put(prefix + admin + task, data, function (data) {
			callback(data);
		});
	};
	this.delete = function (task, data, callback) {
		$.delete(prefix + admin + task, data, function (data) {
			callback(data);
		});
	};
	this.get = function (task, data, callback) {
		$.get(prefix + admin + task, data, function (data) {
			callback(data);
		}, 'jsonp');
	};

	jQuery.each(['put', 'delete', 'get', 'post'], function (i, method) {
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