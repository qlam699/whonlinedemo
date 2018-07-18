if (window.location.hash && window.location.hash == '#_=_') {
        if (window.history && history.pushState) {
            window.history.pushState("", document.title, window.location.pathname);
        } else {
            // Prevent scrolling by storing the page's current scroll offset
            var scroll = {
                top: document.body.scrollTop,
                left: document.body.scrollLeft
            };
            window.location.hash = '';
            // Restore the scroll offset, should be flicker free
            document.body.scrollTop = scroll.top;
            document.body.scrollLeft = scroll.left;
        }
        location.reload();
    }
/*
lay link qua href cua the btn
$(".modal").on("show.bs.modal", function(e) {
	$('body').css('overflow', 'hidden');
	var link = $(e.relatedTarget);
	$(this).find(".modal-content").load(link.attr("href"));
});
// Reset modal box
$(".modal").on("hide.bs.modal", function() {
	$('body').css('overflow', '');
	$(".modal").attr("id", "mymodal");
	$(".modal-dialog").addClass("cascading-modal"); // modal login
	$(this).find(".modal-content").html("");
});*/
$.ajaxSetup({
    beforeSend: function(xhr, settings) {
        if (settings.type == 'POST' || settings.type == 'PUT' ||
            settings.type == 'DELETE') {
            if (!(/^http:.*/.test(settings.url) || /^https:.*/
                    .test(settings.url))) {
                // Only send the token to relative URLs i.e. locally.
                xhr.setRequestHeader("X-XSRF-TOKEN", Cookies.get('XSRF-TOKEN'));
            }
        }
    }
});
$.get("/user", function(data) {
    console.log(data);
    if (data) { // 
        var name = data.principal.username == null ? (data.userAuthentication == null ? data.principal.userAuthentication.details.name : data.userAuthentication.details.name) : data.principal.username;
        if (name != null) {
            $("#profile").html(name);
        }
    }
});
var logout = function() {
    console.log("logout");
    $.post("/logout", function() {
        window.location.href = "/";
        // $("#user").html('');
        /*
         * $(".unauthenticated").show(); $(".authenticated").hide();
         */
    })
    return true;
}
// lay link qua href cua the btn
$("#mymodal").on("show.bs.modal", function(e) {
    $('body').css('overflow', 'hidden');
    var link = $(e.relatedTarget);
    $(this).find(".modal-content").load(link.attr("href"));
});
// Reset modal box
$("#mymodal").on("hide.bs.modal", function() {
    $('body').css('overflow', '');
    $("#mymodal,.modal").attr("id", "mymodal");
    $("#mymodal .modal-dialog,.modal-dialog").addClass("cascading-modal"); // modal
    // login
    $(this).find(".modal-content").html("");
});
//
$("#modalregister").on("show.bs.modal", function(e) {
    var link = $(e.relatedTarget);
    $(this).find(".modal-content").load(link.attr("href"),
        function(responseTxt, statusTxt, xhr) {
            if (statusTxt == "success") {
                $("#btnreg").click(function() {
                    // $("form #btnreg").addClass("d-none");
                    $('form').goValidate();
                    var $form = $('#formreg');
                    if ($form.valid()) { // valid is pass ajax
                        $("#btnreg").hide();
                        $('.loading').removeClass("d-none");
                        $('.loading').addClass("active");
                        $("#error").addClass("d-none");
                        $("#ok").addClass("d-none");
                        var data = $form.serialize(); // full form
                        $.ajax({
                            type: 'POST',
                            dataType: 'html', // this can be
                            // omitted - the
                            // ajax() function
                            // will work it out
                            cache: false,
                            url: $form.attr('action'),
                            data: data,
                            success: function(data, textStatus, jqXHR) {
                                $('.loading').removeClass("active");
                                $("#btnreg").show();
                                if (data == 'exist') { // error
                                    $("#error").removeClass("d-none");
                                    $("#error").text("Duplicate email!");

                                } else if (data == 'error') {
                                    $("#error").text("Error ! Please try again!");
                                } else {
                                    $("#ok").removeClass("d-none");
                                    $("#ok").text("Register Success! Please active email: " + data + " !");

                                }

                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                console.log("error!!");
                            }
                        });
                    } else { // submit so that show err
                        $form.submit();
                    }

                });
            }
            if (statusTxt == "error")
                toastr["danger"]("Error: " + xhr.status + ": " +
                    xhr.statusText);
        });
});
// Reset modal box
$("#modalregister").on("hide.bs.modal", function() {
    $(this).find(".modal-content").html("");
});
// Material Select Initialization
$(document).ready(function() {
    $('.mdb-select').material_select();

});
// message
toastr.options = {
    "closeButton": true, // true/false
    "debug": false, // true/false
    "newestOnTop": false, // true/false
    "progressBar": true, // true/false
    "positionClass": "toast-bottom-right", // toast-top-right /
    // toast-top-left /
    // toast-bottom-right /
    // toast-bottom-left
    "preventDuplicates": false, // true/false
    "onclick": null,
    "showDuration": "500", // in milliseconds
    "hideDuration": "1000", // in milliseconds
    "timeOut": "7000", // in milliseconds
    "extendedTimeOut": "1000", // in milliseconds
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

// SideNav Initialization
$(".button-collapse").sideNav();
/* Menu-toggle */
$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("active");
});
// hidden mess err form
$.validator.messages.required = '';
/* Avatar letters */
(function(w, d) {
    function LetterAvatar(name, size) {
        name = name || '';
        size = size || 60;

        var colours = [
                "#1abc9c", "#2ecc71", "#3498db", "#9b59b6", "#34495e", "#16a085", "#27ae60", "#2980b9", "#8e44ad", "#2c3e50",
                "#f1c40f", "#e67e22", "#e74c3c", "#95CBFF", "#95a5a6", "#f39c12", "#d35400", "#c0392b", "#bdc3c7", "#7f8c8d"
            ],

            nameSplit = String(name).toUpperCase().split(' '),
            initials, charIndex, colourIndex, canvas, context, dataURI;


        if (nameSplit.length == 1) {
            initials = nameSplit[0] ? nameSplit[0].charAt(0) : '?';
        } else {
            initials = nameSplit[0].charAt(0) + nameSplit[1].charAt(0);
        }

        if (w.devicePixelRatio) {
            size = (size * w.devicePixelRatio);
        }

        charIndex = (initials == '?' ? 72 : initials.charCodeAt(0)) - 64;
        colourIndex = charIndex % 20;
        canvas = d.createElement('canvas');
        canvas.width = size;
        canvas.height = size;
        context = canvas.getContext("2d");

        context.fillStyle = colours[colourIndex - 1];
        context.fillRect(0, 0, canvas.width, canvas.height);
        context.font = Math.round(canvas.width / 2) + "px Arial";
        context.textAlign = "center";
        context.fillStyle = "#FFF";
        context.fillText(initials, size / 2, size / 1.5);

        dataURI = canvas.toDataURL();
        canvas = null;

        return dataURI;
    }

    LetterAvatar.transform = function() {

        Array.prototype.forEach.call(d.querySelectorAll('img[avatar]'), function(img, name) {
            name = img.getAttribute('avatar');
            img.src = LetterAvatar(name, img.getAttribute('width'));
            img.removeAttribute('avatar');
            img.setAttribute('alt', name);
        });
    };


    // AMD support
    if (typeof define === 'function' && define.amd) {

        define(function() {
            return LetterAvatar;
        });

        // CommonJS and Node.js module support.
    } else if (typeof exports !== 'undefined') {

        // Support Node.js specific `module.exports` (which can be a function)
        if (typeof module != 'undefined' && module.exports) {
            exports = module.exports = LetterAvatar;
        }

        // But always support CommonJS module 1.1.1 spec (`exports` cannot be a
        // function)
        exports.LetterAvatar = LetterAvatar;

    } else {

        window.LetterAvatar = LetterAvatar;

        d.addEventListener('DOMContentLoaded', function(event) {
            LetterAvatar.transform();
        });
    }

})(window, document);