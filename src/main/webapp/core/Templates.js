/**
 * Created by Michnik on 22.11.2015.
 */
var Tpl = new (function () {
	this.get = function (name) {
		var tpl = $('[data-template="' + name + '"]').clone();
		tpl.addClass(tpl.attr('data-template')).removeAttr('data-template');
		if (!tpl.length)
			throw 'No template found [' + name + ']';
		return tpl;
	}
})();