//var form;
//
//function makeEditable(ajaxUrl) {
//    form = $('#detailsForm');
//
//    $('#add').click(function () {
//        //form.find(":input").each(function () {
//        //    $(this).val("");
//        //});
//        $('#id').val(0);
//        $('#editRow').modal();
//    });
//
//    $('.delete').click(function () {
//        deleteRow($(this).attr("id"));
//    });
//
//    $('.update').click(function () {
//        updateRow($(this).attr("id"));
//    });
//
//    form.submit(function () {
//        save();
//        return false;
//    });
//
//    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
//        failNoty(event, jqXHR, options, jsExc);
//    });
//
//    //var token = $("meta[name='_csrf']").attr("content");
//    //var header = $("meta[name='_csrf_header']").attr("content");
//    //$(document).ajaxSend(function(e, xhr, options) {
//    //    xhr.setRequestHeader(header, token);
//    //});
//    //init();
//
//
//
//}
//
//
//function renderDate(date, type, row) {
//    if (type == 'display') {
//        var dateObject = new Date(date);
//        return '<span>' + dateObject.toISOString().substring(0, 10) + '</span>';
//    }
//    return date;
//}
//
//
//function renderUpdateBtn(data, type, row) {
//    if (type == 'display') {
//        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ')">Update</a>';
//    }
//    return data;
//}
//
//function renderDeleteBtn(data, type, row) {
//    if (type == 'display') {
//        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ')">Delete</a>';
//    }
//    return data;
//}
//
//function renderCheckbox(data, type, row) {
//    if (type == 'display') {
//        return '<input type="checkbox"' + (data ? ' checked ' : ' ') + 'onclick="enable(' + row.id + ',$(this))"/>';
//    }
//    return data;
//}
//
//
//
//function updateRow(id) {
//    $.get(ajaxUrl + id, function (data) {
//        $.each(data, function (key, value) {
//            form.find("input[name='" + key + "']").val(value);
//        });
//        $('#editRow').modal();
//    });
//}
//
//function deleteRow(id) {
//    $.ajax({
//        url: ajaxUrl + id,
//        type: 'DELETE',
//        success: function () {
//            updateTable();
//            successNoty('Deleted');
//        }
//    });
//}
//
//    function updateTable() {
//        $.get(ajaxUrl, function(data){
//            oTable_datatable.fnClearTable();
//
//            $.each(data, function(key, item) {
//                oTable_datatable.fnAddData(item);
//            });
//
//            oTable_datatable.fnDraw();
//        });
//    }
//
//function enable(id, chkbox) {
//    var enabled = chkbox.is(":checked");
//    chkbox.parent().parent().css("text-decoration", enabled ? "none" : "line-through");
//    $.ajax({
//        url: ajaxUrl + id + '/enable',
//        type: 'POST',
//        data: 'enabled=' + enabled,
//        success: function () {
//            successNoty(enabled ? 'Enabled' : 'Disabled');
//        }
//    });
//}
//
//function updateByData(data) {
//    oTable_datatable.fnClearTable();
//    $.each(data, function (key, item) {
//        oTable_datatable.fnAddData(item);
//    });
//    oTable_datatable.fnDraw();
//}
//
//function save() {
//    $.ajax({
//        type: "POST",
//        url: ajaxUrl,
//        data: form.serialize(),
//        success: function (data) {
//            $('#editRow').modal('hide');
//            updateTable();
//            successNoty('Saved');
//        }
//    });
//
//
//
//
//}
//
//var failedNote;
//
//function closeNote() {
//    if (failedNote) {
//        failedNote.close();
//        failedNote = undefined;
//    }
//}
//
//function successNoty(text) {
//    closeNote();
//    noty({
//        text: text,
//        type: 'success',
//        layout: 'bottomRight',
//        timeout: true
//    });
//}
//
//function failNoty(event, jqXHR, options, jsExc) {
//    closeNote();
//    failedNote = noty({
//        text: 'Failed: ' + jqXHR.statusText,
//        type: 'error',
//        layout: 'bottomRight'
//    });
//}
//
