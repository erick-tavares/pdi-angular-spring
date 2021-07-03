package br.com.pdi.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConverterBigDecimal {

    public BigDecimal converter(String valor){

        if (valor == null){
            return null;
        }
        valor = valor.replace(".", "").replace(",",".");

        return new BigDecimal(valor);
    }
}
