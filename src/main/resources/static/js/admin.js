$.fn.exists = function(callback) {
    var args = [].slice.call(arguments, 1);

    if (this.length) {
        callback.call(this, args);
    }
    return this;
};

if(localStorage.getItem("toggle") == "no"){
    $('#sidebar').toggleClass('active');
}

$('#sidebarCollapse').on('click', function () {

    $('#sidebar').toggleClass('active');

    if (localStorage.getItem("toggle") === null) {
        localStorage.setItem("toggle", "yes");
    } else {

        if(localStorage.getItem("toggle") == "yes"){
            localStorage.setItem("toggle", "no");
        } else{
            localStorage.setItem("toggle", "yes");
        }
    }
});



$('.datatable').exists(function() {
    $(".datatable").DataTable(
        {
            columnDefs: [
                { targets: [1], "orderable": false}
            ]
        }
    );
});


$('.editor').exists(function() {
    tinymce.init({
        selector: '.editor',
        plugins: ["image", "link","media","table","textcolor"],
        toolbar1: 'formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat',
        image_caption: true
    });
});

$('.select2_tags').exists(function() {
    $('.select2_tags').select2();


});


$('#title').exists(function() {

    $( "#title" ).blur(function() {
        var title = $(this).val();
        $( "#slug" ).val(slugify(title))
    });

    $('textarea').keyup(updateCount);
    $('textarea').keydown(updateCount);
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


$('.delete_form').submit(function(e) {
    var currentForm = this;
    e.preventDefault();
    bootbox.confirm({
        title: "Are you sure?",
        message: "This item will be deleted immediately. You can't undo this action. Are you still sure you want to delete?",
        buttons: {
            confirm: {
                label: 'Yes',
                className: 'btn-danger'
            },
            cancel: {
                label: 'No',
                className: 'btn-dark'
            }
        },
        callback: function (result) {
            if (result) {
                currentForm.submit();
            }
        }
    });
});



$('#upload_media').click(function(){
    $('#imgupload').trigger('click');
});

$('#imgupload').on('change', function(){
    $("#upload_form").submit();
});

$('#upload_image').click(function(){
    $('#img_upload').trigger('click');
});

$('#img_upload').on('change', function(){

    var file_data = $(this).prop("files")[0];
    var form_data = new FormData();
    form_data.append("file", file_data);


    $.ajax({
        type:'POST',
        url: "/api/upload",
        data:form_data,
        cache:false,
        contentType: false,
        processData: false,
        success:function(data){
            $("#image").val(data);
            $("#upload_preview").attr("src", data);
           // $("#image").src = reader.result;
        },
        error: function(data){
            console.log(data);
        }
    });


});



$('#showOnHome1').on('change', function(){
    $(".post-pos").toggleClass("d-none");
});



$('ul.sortable').exists(function() {

    $('ul.sortable').sortable({
        connectWith: 'ul.sortable',
        placeholder: 'sortable-placeholder',
        update: function(event, ui) {

            if(ui.sender){
                var pid =  $(this).parent().data("menu-id");
                if( typeof pid === 'undefined' || pid === null ){
                    $(this).find( ".pid" ).text("null")
                    $(this).find( "li.menu_item" ).data('parent_id', 0);
                } else{
                    $(this).find( "li.menu_item" ).data('parent_id', pid);
                    $(this).find( ".pid" ).text(pid)
                }
            }

            var menuData = [];
            $('.menu_item').each(function(i) {
                var mid = $(this).data('menu-id');
                var pos = i + 1;
                var pid = $(this).data("parent_id");
                var dd = {id: mid, parent_id :pid,position:pos}
                menuData.push(dd)
            });

            $.ajax({
                type: "POST",
                url: "/api/menu/update",
                datatype:'json',
                contentType:"application/json; charset=utf-8",
                data: JSON.stringify(menuData),
                success:function(data){
                    location.reload();
                }
            });


        }
    }).disableSelection();

});




$('.menu_delete').click(function(){
    var id = $(this).data('menu-id');
    $.post( "/api/menu/delete",{ menu_id: id} , function( data ) {
        location.reload();
    });
});

