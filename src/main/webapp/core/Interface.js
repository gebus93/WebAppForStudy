/**
 * Created by Michnik on 22.11.2015.
 */
var Interface = new (function () {
	var self = this;
	var sections = [ // TODO: after login add new items
		{text: 'Aktualności', task: 'news/list?perPage={perPage}&page={pageNumber}'},
		{text: 'Oferta', task: 'service'},
		{text: 'Kontakt', task: 'contact'}
	];
	this.$ = null;

	this.init = function () {
		this.$ = $('.main');
		this.addNav();
	};

	this.addNav = function () {
		var $nav = this.$.find('nav > ul');
		var $li = null;
		var $a = null;
		for (var i = 0; i < sections.length; i++) {
			$a = $('<a/>', {html: sections[i].text});
			$li = $('<li/>', {html: $a});
			$nav.append($li);

			$li.click((function (task) {
				return function () {
					Communication.get(task, {}, function () {
						console.log('test');
					})
				};
			})(sections[i].task));
		}
	};

	$(function () {
		self.init();
	})
})();