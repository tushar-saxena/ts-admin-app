var tsapp = tsapp || {};

tsapp.datatable_utils = (function () {
    var test = function () {
        console.log("test");
    };

    var init_data_table = (function (buttonObj, listUrl, columns) {
        $('#data-table').DataTable({
            "processing": true,
            "serverSide": true,
            searching: false,
            dom: 'Bfrtip',
            buttons: [
                {
                    text: buttonObj.text,
                    action: function (e, dt, node, config) {
                        window.location.href = buttonObj.url;
                    },
                    className: 'btn btn-primary float-right'
                }
            ],
            "ajax": {
                "url": listUrl,
                "type": "POST"
            },
            "columns": columns
        });
    });

    return {
        test: test,
        init_data_table: init_data_table
    }
})();