// default table
let jquery_datatable = $("#table1").DataTable({
    responsive: true
})
let customized_datatable = $("#table2").DataTable({
    responsive: true,
    pagingType: 'simple',
    dom:
		"<'row'<'col-3'l><'col-9'f>>" +
		"<'row dt-row'<'col-sm-12'tr>>" +
		"<'row'<'col-4'i><'col-8'p>>",
    "language": {
        "info": "Page _PAGE_ of _PAGES_",
        "lengthMenu": "_MENU_ ",
        "search": "",
        "searchPlaceholder": "Search.."
    }
})

// user table
let customizedTable = $("#customized-table").DataTable({
    responsive: true,
    ordering: true,
    order: [],
    columnDefs: [
        {
            orderable: false,
            targets: -1
        },
        {
            targets: '_all',
            createdCell: function(td, cellData, rowData, row, col) {
                $(td).css({
                    'max-width': '200px',
                    'max-height': '50px',
                    'white-space': 'nowrap',
                    'overflow': 'hidden',
                    'text-overflow': 'ellipsis'
                });
            }
        }
    ]
});

const setTableColor = () => {
    document.querySelectorAll('.dataTables_paginate .pagination').forEach(dt => {
        dt.classList.add('pagination-primary')
    })
}
setTableColor()
jquery_datatable.on('draw', setTableColor)
customizedTable.on('draw', setTableColor)
