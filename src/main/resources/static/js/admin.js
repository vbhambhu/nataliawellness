$.fn.exists = function(callback) {
    var args = [].slice.call(arguments, 1);

    if (this.length) {
        callback.call(this, args);
    }
    return this;
};

$('.select2_tags').exists(function() {
    $('.select2_tags').select2();

    tinymce.init({
        selector: '#content'
    });
});


$('#title').exists(function() {

    $( "#title" ).blur(function() {
        var title = $(this).val();
        $( "#slug" ).val(slugify(title))
    });


    $('textarea').keyup(updateCount);
    $('textarea').keydown(updateCount);



});




$('.select2_article').exists(function() {


$(".select2_article").select2({
    ajax: {
        url: "/api/article/search",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                q: params.term, // search term
            };
        },
        processResults: function (data, params) {
            return {
                results: data,
            };
        },
        cache: true
    },
    escapeMarkup: function (markup) { return markup; },
    minimumInputLength: 1,
    templateResult: formatResult,
    templateSelection: formatResultSelection
});

function formatResult (data) {
    return data.title;
}

function formatResultSelection (data) {
    return data.title;
}


});



function slugify(text) {
    return text.toString().toLowerCase()
        .replace(/\s+/g, '-')           // Replace spaces with -
        .replace(/[^\w\-]+/g, '')       // Remove all non-word chars
        .replace(/\-\-+/g, '-')         // Replace multiple - with single -
        .replace(/^-+/, '')             // Trim - from start of text
        .replace(/-+$/, '');            // Trim - from end of text
}

function updateCount() {
    var cs =$(this).val().length;
    $('#characters').text(cs);
}