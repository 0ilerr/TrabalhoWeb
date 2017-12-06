$(document).ready(function () {
    modelLogin();
    modelCadastro();
    adicionaClickTemplate();
    btnCadastrar();
    btnLogar();
    cadastrarEmpresa();
});


function modelLogin() {
    $("#loginBtn").click(function () {
        $("#modal_login").modal();
    })
}


function modelCadastro() {
    $("#cadastroBtn").click(function () {
        $("#modal_cadastro").modal();
    })
}

function adicionaClickTemplate() {
    var radioImagem = $(":radio");
    for (var i = 0; i <= radioImagem.length - 1; i++) {
        var oRadio = radioImagem[i];
        oRadio.addEventListener('click', btnCadastrar);
    }
}

function btnCadastrar() {
    var radioImagem = $(":radio");
    var bIsChecked = false;
    var btnCadastro = $("#cadastroBtn")[0];
    for (var i = 0; i <= radioImagem.length - 1; i++) {
        var oRadio = radioImagem[i];
        if (oRadio.checked != false) {
            bIsChecked = true;
        }
    }
    if (bIsChecked == true) {
        btnCadastro.className = "";
        modelCadastro();
    } else {
        btnCadastro.className = "disabled-item";
    }

}

function btnLogar() {
    $('#btnModalLogin').click(function () {
        if (($('#usuarioSenha').val() == 'admin') && ($('#usuarioSenha').val() == 'admin')) {
            window.open('indexDashboard.html');
        } else {
            swal('Erro de Autenticação ', "Usuário ou senha incorretos", 'error');
        }
    })
}


function cadastrarEmpresa() {

    var id = 0;
    $('#btn_enviar_modal_cadastro').click(function () {
        debugger;
        var nome = $('#cadastro_nome_empresa').val();
        var senha = $('#cadastro_senha_empresa').val();
        var email = $('#cadastro_email_empresa').val();
        var endereco = $('#cadastro_endereco_empresa').val();
        var slogan = $('#cadastro_senha_empresa').val();
        var midiaSocial = $('#cadastro_midiasocial_empresa').val();
        var contato = $('#cadastro_telefone_empresa').val();
        var template = $("input[name|='templateRadio']").val();
        id = id + 1;

        var empresa;
        empresa = {
            "id"          : id,
            "senha"       : senha,
            "nome"        : nome,
            "email"       : email,
            "endereco"    : endereco,
            "slogan"      : slogan,
            "midiaSocial" : midiaSocial,
            "contato"     : contato,
            "template"    : template
        };
    })
}