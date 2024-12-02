package com.barrildorado.lbd.dto.movimiento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimientoPorMes {
    private int mes;
    private int entrada;
    private int salida;

    public MovimientoPorMes(int mes, int entrada, int salida) {
        this.mes = mes;
        this.entrada = entrada;
        this.salida = salida;
    }

    // Getters y Setters
    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }
}