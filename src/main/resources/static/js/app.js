$('a[href="#search"]').on('click', function(event) {
    event.preventDefault();
    $('#search').addClass('open');
    $('#search > form > input[type="search"]').focus();
});

$('#search, #search button.close').on('click keyup', function(event) {
    if (event.target == this || event.target.className == 'close' || event.keyCode == 27) {
        $(this).removeClass('open');
    }
});

$('.navbar .dropdown').hover(function() {
    $(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideDown();

}, function() {
    $(this).find('.dropdown-menu').first().stop(true, true).delay(50).slideUp();

});

$('.navbar .dropdown > a').click(function(){
    location.href = this.href;
});