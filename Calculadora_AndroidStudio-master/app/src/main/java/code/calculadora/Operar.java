package code.calculadora;

public class Operar {
    String valor = "";
    int lado_parentesis = 0;
    double valorDef = 0;
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getLado_parentesis() {
        return lado_parentesis;
    }

    public void setLado_parentesis(int lado_parentesis) {
        this.lado_parentesis = lado_parentesis;
    }

    public double getValorDef() {
        return valorDef;
    }

    public void setValorDef(double valorDef) {
        this.valorDef = valorDef;
    }
}
