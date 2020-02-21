var tsapp = tsapp || {};

$(document).ready(function () {
    var buttonObj = {
        text: 'Create',
        url: '/user/create'
    };
    var listUrl = '/api/user/list';
    var columns = [
        {"data": "firstName"},
        {"data": "lastName"},
        {"data": "emailId"},
        {"data": "gender"},
        {
            "data": "id",
            "mRender": function (data, type, row) {
                var id = data;
                return "<a href='/user/edit/" + id + "'><i class='material-icons'>create</i></a>" +
                    "<a href='/user/delete/" + id + "'><i class='material-icons'>delete</i></a>";
            }
        }
    ]
    tsapp.datatable_utils.init_data_table(buttonObj, listUrl, columns);
});