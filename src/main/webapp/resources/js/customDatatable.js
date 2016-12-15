$(function(){
    var form = $('#detailsForm');
    var ajaxUrl = 'ajax/admin/users';
    var failedNote;

    $('#add').click(function(){
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function(){
        deleteRow($(this).attr('id'));
    });

    form.submit(function () {
        $.ajax({
            type: 'POST',
            url:  ajaxUrl,
            data: form.serialize(),
            success: function() {
                $('#editRow').modal('hide');
  //              updateTable();
                successNoty('Saved');
            }
        });

       // return false;
    });

    function deleteRow(id) {
        $.ajax({
            url: ajaxUrl + '/' + id,
            type: 'DELETE',
            success: function() {
                //updateTable();
                successNoty('Deleted');
            },
            //error: function(jqXHR, textStatus, errorThrown) {
            //    failNoty(event, jqXHR, options, jsExc);
            //}
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
    }


    function closeNote() {
        if (failedNote) {
            failedNote.close();
            failedNote = undefined;
        }
    }

    function successNoty(text) {
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
        //var errorInfo = $.parseJSON(jqXHR.responseText);
        debugger;
        failedNote = noty({
            //text: 'Failed: ' + jqXHR.statusText + "<br>" + errorInfo.cause + "<br>" + errorInfo.detail,
            text: 'Failed: ' + jqXHR.statusText + "<br>",
            type: 'error',
            layout: 'bottomRight'
        });
    }

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

});


