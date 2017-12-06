$(document).ready(function () {
    openModalProduto1()
    console.log("ta");
});

function openModalProduto1() {

    $('#produto1modal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });

    $('#img-produto1').click(
        $('#produto1modal').show()
    )
}