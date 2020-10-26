/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Suporte T.I 2
 */
public class TiposPagamentos {
    private int cod_tipo_pagamento;
    private String descricao_tipo_pagamento;

    public TiposPagamentos() {
    }

    public TiposPagamentos(int cod_tipo_pagamento, String descricao_tipo_pagamento) {
        this.cod_tipo_pagamento = cod_tipo_pagamento;
        this.descricao_tipo_pagamento = descricao_tipo_pagamento;
    }

    public int getCod_tipo_pagamento() {
        return cod_tipo_pagamento;
    }

    public void setCod_tipo_pagamento(int cod_tipo_pagamento) {
        this.cod_tipo_pagamento = cod_tipo_pagamento;
    }

    public String getDescricao_tipo_pagamento() {
        return descricao_tipo_pagamento;
    }

    public void setDescricao_tipo_pagamento(String descricao_tipo_pagamento) {
        this.descricao_tipo_pagamento = descricao_tipo_pagamento;
    }
    @Override
    public String toString() {
        return getDescricao_tipo_pagamento(); //To change body of generated methods, choose Tools | Templates.
    }
}
