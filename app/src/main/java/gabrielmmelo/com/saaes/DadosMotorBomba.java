package gabrielmmelo.com.saaes;

public class DadosMotorBomba {
    private float tensao;
    private float corrente;
    private float potencia_ativa;
    private float potencia_reativa;
    private float fator_potencia;
    private float rotacao;
    private String fabricante_motor;
    private float vazao;
    private float altura_monometrica;
    private String fabricante_bomba;

    /**
     * Constructor that initialize all attributes to 0
     */
    public DadosMotorBomba() {
        tensao = 0;
        corrente = 0;
        potencia_ativa = 0;
        potencia_reativa = 0;
        fator_potencia = 0;
    }

    public void setVazao(float vazao) {
        this.vazao = vazao;
    }

    public void setAltura_monometrica(float altura_monometrica) {
        this.altura_monometrica = altura_monometrica;
    }

    public void setFabricante_bomba(String fabricante_bomba) {
        this.fabricante_bomba = fabricante_bomba;
    }

    public float getVazao() {
        return vazao;
    }

    public float getAltura_monometrica() {
        return altura_monometrica;
    }

    public String getFabricante_bomba() {
        return fabricante_bomba;
    }

    public void setFabricante_motor(String fabricante_motor) {
        this.fabricante_motor = fabricante_motor;
    }

    public String getFabricante_motor() {
        return fabricante_motor;
    }

    public void setTensao(float tensao) {
        this.tensao = tensao;
    }

    public void setCorrente(float corrente) {
        this.corrente = corrente;
    }

    public void setPotencia_ativa(float potencia_ativa) {
        this.potencia_ativa = potencia_ativa;
    }

    public void setPotencia_reativa(float potencia_reativa) {
        this.potencia_reativa = potencia_reativa;
    }

    public void setFator_potencia(float fator_potencia) {
        this.fator_potencia = fator_potencia;
    }

    public void setRotacao(float rotacao) {
        this.rotacao = rotacao;
    }

    public float getTensao() {
        return tensao;
    }

    public float getCorrente() {
        return corrente;
    }

    public float getPotencia_ativa() {
        return potencia_ativa;
    }

    public float getPotencia_reativa() {
        return potencia_reativa;
    }

    public float getFator_potencia() {
        return fator_potencia;
    }

    public float getRotacao() {
        return rotacao;
    }
}
