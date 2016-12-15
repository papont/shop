var form;
var ajaxUrl;
function makeEditable(url) {
    form = $('#detailsForm');
    ajaxUrl = url;

    $('#add').click(function(){
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function(){
        deleteRow($(this).attr('id'));
    });

    form.submit(function () {
        var frm = $('#detailsForm');
        $.ajax({
            type: 'POST',
            url:  ajaxUrl,
            data: frm.serialize(),
            success: function() {
                $('#editRow').modal('hide');
                updateTable();
                success('Saved');
            }
        });

        return false;
    });

    //$(document).ajaxError(function (event, jqXHR, options, jsExc) {
    //    failNoty(event, jqXHR, options, jsExc);
    //});

}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + '/' + id,
        type: 'DELETE',
        success: function() {
            updateTable();
           // success('Deleted');
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function(data){
        oTable_datatable.fnClearTable();

        $.each(data, function(key, item) {
            oTable_datatable.fnAddData(item);
        });

        oTable_datatable.fnDraw();
    });


var failedNote;

function closeNote() {
    if (filedNote) {
        filedNote.close();
        filedNote = undefined;
    }
};

function success(text) {
    closeNote();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNote();
    var errorInfo = $.parseJSON(jqXHR.responseText);

    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>" + errorInfo.cause + "<br>" + errorInfo.detail,
        type: 'error',
        layout: 'bottomRight'
    });
}



}
