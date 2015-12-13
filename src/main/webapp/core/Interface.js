/**
 * Created by Michnik on 22.11.2015.
 */
var Interface = new (function () {
	var self = this;
	var sections = null;
	var admin = false;
	this.$ = null;

	this.init = function () {
		this.$ = $('.main');
		$('.login').click(self.login);
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
		var $content = Tpl.get('service-window');
		var $services = [];
		var $service = null;
		//for (var i in d) {
		for (var i = 0, j = 0; j < 5; j++) { // FOR DEBUG!
			$service = Tpl.get('service');
			$service.find('.name').html('Nazwa: ' + d[i].name);
			$service.find('.group').html('Grupa: ' + d[i].groupID);
			$service.find('.price').html('Cena: ' + d[i].price);
			$service.find('.min-price').html('Cena minimalna: ' + d[i].minPrice);
			$service.find('.max-price').html('Cena maksymalna: ' + d[i].maxPrice);
			$services.push($service);
		}
		$content.html($services);
		$('.content', '.main').html($content);
	};

	this.news = function (d) {
		console.log(d);
	};

	this.login = function () {
		var $content = Tpl.get('login-window');
		$content.find('.login').html(['Login: ', $('<input/>', {id: 'login'})]);
		$content.find('.password').html(['Hasło: ', $('<input/>', {id: 'password', type: 'password'})]);

		$content.find('.button').click(function () {
			var login = $('#login').val();
			var password = $('#password').val();
			var data = {'login': login, 'password': password};
			Communication.post('user/login', data, function (status) {
				if (status == 200) {
					setAdminMode();
				} else {
					$('.error', $content).css('display', 'block');
				}
			});
		});
		$('.content', '.main').html($content);
	};

	function setAdminMode() { //TODO: adminMode ;/
		$('.content', '.main').html('');
	}

	$(function () {
		self.init();
	})
})();