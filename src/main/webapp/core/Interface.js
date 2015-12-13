/**
 * Created by Michnik on 22.11.2015.
 */
var Interface = new (function () {
	var self = this;
	var sections = null;
	this.$ = null;

	this.init = function () {
		this.$ = $('.main');
		this.updateSectionsTab();
		this.addNav();
	};

	this.updateSectionsTab = function () {
		sections = [ // TODO: after login add new items
			{text: 'Aktualności', task: 'news/list?perPage={perPage}&page={pageNumber}', 'callback': self.news},
			{text: 'Oferta', task: 'service', 'callback': self.service},
			{text: 'Kontakt', task: 'contact', 'callback': self.contact}
		];
	};

	this.addNav = function () {
		var $nav = this.$.find('nav > ul');
		var $sections = [];
		var $li = null;
		var $a = null;
		for (var i = 0; i < sections.length; i++) {
			$a = $('<a/>', {html: sections[i].text});
			$li = $('<li/>', {html: $a});
			$sections.push($li);

			$li.click((function (task, callback) {
				return function () {
					Communication.get(task, {}, function (data) {
						callback(data);
					})
				};
			})(sections[i].task, sections[i].callback));
		}
		$nav.html($sections);
	};

	this.contact = function (d) {
		console.log(d);
	};

	this.service = function (d) {
		console.log(d);
	};

	this.news = function (d) {
		console.log(d);
	};

	$(function () {
		self.init();
	})
})();