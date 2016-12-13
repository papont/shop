function makeEditable() {
    $('#add').click(function(){
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function(){
        deleteRow($(this).attr('id'));
    });

    $('#rowForm').click(function(){
        save();
        return false;
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function() {
            updateTable();
            success('Deleted');
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

function save() {
    var frm = $('#detailsForm');

    $.ajax({
        url: ajaxUrl,
        type: 'POST',
        data: frm.serialize(),
        success: function(data) {
            $('#editRow').modal('hide');
            updateTable();
        }
    });
}
}
