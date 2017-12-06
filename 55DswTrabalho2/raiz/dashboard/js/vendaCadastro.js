$('#btnAddProduto').click(function () {
    var cont = cont + 1;

    var div = document.createElement('div');
    var attrDivId = document.createAttribute('id');
    div.className += 'form-inline';
    div.style.marginTop = '1%';

    var labelNome = document.createElement('label');
    var attrLabelNome = document.createAttribute('for');
    labelNome.prepend('Nome');
    attrLabelNome.value = 'nome' + cont.toString();
    labelNome.setAttributeNode(attrLabelNome);

    var inputNome = document.createElement('input');
    var attrNomeHolder = document.createAttribute('placeholder');
    var attrInputNome = document.createAttribute('id');
    inputNome.className += 'form-control';
    attrInputNome.value = 'nome' + cont.toString();
    attrNomeHolder.value = 'Nome do Produto';
    inputNome.setAttributeNode(attrInputNome);
    inputNome.setAttributeNode(attrNomeHolder);
    inputNome.style.marginLeft = '.5%';

    var labelPreco = document.createElement('label');
    var attrLabelPreco = document.createAttribute('for');
    labelPreco.prepend('Preço');
    attrLabelPreco.value = 'nome' + cont.toString();
    labelPreco.setAttributeNode(attrLabelPreco);
    labelPreco.style.marginLeft = '.5%';

    var inputPreco = document.createElement('input');
    var attrInputPreco = document.createAttribute('id');
    var attrInputPrecoHolder = document.createAttribute('placeholder');
    var attrInputPrecoDisabled = document.createAttribute('disabled');
    inputPreco.className += 'form-control';
    attrInputPreco.value = 'nome' + cont.toString();
    attrInputPrecoHolder.value = 'Preço Produto';
    inputPreco.setAttributeNode(attrInputPreco);
    inputPreco.setAttributeNode(attrInputPrecoDisabled);
    inputPreco.setAttributeNode(attrInputPrecoHolder);
    inputPreco.style.marginLeft = '.5%';


    div.appendChild(labelNome);
    div.appendChild(inputNome);
    div.appendChild(labelPreco);
    div.appendChild(inputPreco);

    $('#btnSubtims').before(div);

});