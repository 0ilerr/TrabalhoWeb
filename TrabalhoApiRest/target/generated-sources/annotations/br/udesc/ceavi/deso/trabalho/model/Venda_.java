package br.udesc.ceavi.deso.trabalho.model;

import br.udesc.ceavi.deso.trabalho.model.Cliente;
import br.udesc.ceavi.deso.trabalho.model.Produto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-06T17:47:44")
@StaticMetamodel(Venda.class)
public class Venda_ { 

    public static volatile SingularAttribute<Venda, Cliente> cliente;
    public static volatile ListAttribute<Venda, Produto> produtos;
    public static volatile SingularAttribute<Venda, Long> id;

}