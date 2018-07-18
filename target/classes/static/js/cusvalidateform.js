$.fn.goValidate = function() {
	var $form = this, $inputs = $form.find('input:text, input:password'), $selects = $form
			.find('select'), $textAreas = $form.find('textarea'), $birthday = $form
			.find('date');

	var validators = {
		name : {
			regex : /^([\u0000-\uFFFF]{2,}[^$|\s*])$/
		},
		username : {
			regex : /^([\u0000-\uFFFF]{6,}[^$|\s*])$/
		},
		town : {
			regex : /^([\u0000-\uFFFF]{2,}[^$|\s*])$/
		},
		postcode : {
			regex : /^.{3,}$/
		},
		password : {
			// regex: /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/
			regex : /^[a-zA-Z0-9]{7,30}$/
		},
		email : {
			regex : /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/
		},
		phone : { // sdt va cmnd
			regex : /^[0-9]\d{9,12}$/
		},
		cmnd : { // sdt va cmnd
			regex : /^[0-9]\d{5,11}$/
		},
		number : { // so diem
			regex : /^[0-9]\d{0,1}$/
		},
		money:{
			regex: /^(?!\.?$)\d{0,8}(\.\d{0,2})?$/
		},
		year : { // so diem
			regex : /^[0-9]\d{3}(\-[0-9]\d{3})?$/
		},
		masv : {
			regex : /^[0-9]\d{7}$/
		},
		country : {
			regex : /^(?=\s*\S).*$/
		},
		sldk : {
			regex : /^([0-9][0-9])$/,
		}
	};
	var validate = function(klass, value) {
		var isValid = true, error = '';

		if (!value && /required/.test(klass)) {
			error = 'This field is required';
			isValid = false;
		} else {
			klass = klass.split(/\s/);
			$.each(klass, function(i, k) {
				if (validators[k]) {
					if (value && !validators[k].regex.test(value)) {
						isValid = false;
						error = validators[k].error;
					}
				}
			});
		}
		return {
			isValid : isValid,
			error : error
		}
	};
	var showError = function($e) {
		var klass = $e.attr('class'), value = $e.val(), test = validate(klass,
				value);

		$e.removeClass('invalid');
		$('#form-error').addClass('hide');

		if (!test.isValid) {
			$e.addClass('invalid');

			if (typeof $e.data("shown") == "undefined"
					|| $e.data("shown") == false) {
				$e.popover('show');
			}

		} else {
			$e.popover('hide');
		}
	};

	$inputs.keyup(function() {
		showError($(this));
	});
	$selects.change(function() {
		showError($(this));
	});
	$textAreas.keyup(function() {
		showError($(this));
	});
	$birthday.change(function() {
		showError($(this));
	})

	$inputs.on('shown.bs.popover', function() {
		$(this).data("shown", true);
	});

	$inputs.on('hidden.bs.popover', function() {
		$(this).data("shown", false);
	});

	$form.submit(function(e) {

		$inputs.each(function() { /* test each input */
			if ($(this).is('.required') || $(this).hasClass('invalid')) {
				showError($(this));
			}
		});
		$selects.each(function() { /* test each input */
			if ($(this).is('.required') || $(this).hasClass('invalid')) {
				showError($(this));
			}
		});
		$textAreas.each(function() { /* test each input */
			if ($(this).is('.required') || $(this).hasClass('invalid')) {
				showError($(this));
			}
		});
		if ($form.find('input.invalid').length) { /* form is not valid */
			e.preventDefault();
			$('#form-error').toggleClass('hide');
		}
	});
	return this;
};
$('form').goValidate();