/**
 * Created by Michnik on 22.11.2015.
 */
var Communication = new (function () {
	var self = this;
	var prefix = '/rest/';
	var admin = 'admin/';

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
		});
	};

	jQuery.each(["put", "delete"], function (i, method) {
		jQuery[method] = function (url, data, callback, type) {
			if (jQuery.isFunction(data)) {
				type = type || callback;
				callback = data;
				data = undefined;
			}

			return jQuery.ajax({
				url: url,
				type: method,
				dataType: type,
				data: data,
				success: callback
			});
		};
	});

})();