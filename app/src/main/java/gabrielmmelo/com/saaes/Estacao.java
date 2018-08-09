package gabrielmmelo.com.saaes;

import org.json.JSONException;
import org.json.JSONObject;

public class Estacao{

    private float tensao_placa;
    private float tensao_medicao;
    private float corrente_placa;
    private float corrente_medicao;
    private float potencia_ativa_placa;
    private float potencia_ativa_medicao;
    private float potencia_reativa_placa;
    private float potencia_reativa_medicao;
    private float fator_potencia_placa;
    private float fator_potencia_medicao;
    private float rotacao_placa;
    private float rotacao_medicao;
    private float vazao_placa;
    private float vazao_medicao;
    private float altura_monometrica_placa;
    private float altura_monometrica_medicao;
    private String fabricante_motor;
    private String fabricante_bomba;
    private String cidade;
    private String local;
    private String tipo_partida;
    private String banco_capacitores;
    private String sistema_supervisionado;
    private String observacoes;

    public Estacao() {
        // empty
    }

    public Estacao(float tensao_placa, float tensao_medicao, float corrente_placa, float corrente_medicao, float potencia_ativa_placa, float potencia_ativa_medicao, float potencia_reativa_placa, float potencia_reativa_medicao, float fator_potencia_placa, float fator_potencia_medicao, float rotacao_placa, float rotacao_medicao, float vazao_placa, float vazao_medicao, float altura_monometrica_placa, float altura_monometrica_medicao, String fabricante_motor, String fabricante_bomba, String cidade, String local) {
        this.tensao_placa = tensao_placa;
        this.tensao_medicao = tensao_medicao;
        this.corrente_placa = corrente_placa;
        this.corrente_medicao = corrente_medicao;
        this.potencia_ativa_placa = potencia_ativa_placa;
        this.potencia_ativa_medicao = potencia_ativa_medicao;
        this.potencia_reativa_placa = potencia_reativa_placa;
        this.potencia_reativa_medicao = potencia_reativa_medicao;
        this.fator_potencia_placa = fator_potencia_placa;
        this.fator_potencia_medicao = fator_potencia_medicao;
        this.rotacao_placa = rotacao_placa;
        this.rotacao_medicao = rotacao_medicao;
        this.vazao_placa = vazao_placa;
        this.vazao_medicao = vazao_medicao;
        this.altura_monometrica_placa = altura_monometrica_placa;
        this.altura_monometrica_medicao = altura_monometrica_medicao;
        this.fabricante_motor = fabricante_motor;
        this.fabricante_bomba = fabricante_bomba;
        this.cidade = cidade;
        this.local = local;
    }

    public void estacaoFromJson(String _local, String _placa, String _medicao, String _sistema) {

        try {
            JSONObject local = new JSONObject(_local);
            JSONObject placa = new JSONObject(_placa);
            JSONObject medicao = new JSONObject(_medicao);
            JSONObject sistema = new JSONObject(_sistema);


        // PLACA
            this.tensao_placa = Float.parseFloat(placa.optString("tensao"));
            this.corrente_placa = Float.parseFloat(placa.optString("corrente"));
            this.potencia_ativa_placa = Float.parseFloat(placa.optString("potencia_ativa"));
            this.potencia_reativa_placa = Float.parseFloat(placa.optString("potencia_reativa"));
            this.fator_potencia_placa = Float.parseFloat(placa.optString("fator_potencia"));
            this.rotacao_placa = Float.parseFloat(placa.optString("rotacao"));
            this.vazao_placa = Float.parseFloat(placa.optString("vazao"));
            this.altura_monometrica_placa = Float.parseFloat(placa.optString("altura_monometrica"));
            this.fabricante_motor = placa.optString("fabricante_motor");
            this.fabricante_bomba = placa.optString("fabricante_bomba");

        // MEDICAO
            this.tensao_placa = Float.parseFloat(medicao.optString("tensao"));
            this.corrente_placa = Float.parseFloat(medicao.optString("corrente"));
            this.potencia_ativa_placa = Float.parseFloat(medicao.optString("potencia_ativa"));
            this.potencia_reativa_placa = Float.parseFloat(medicao.optString("potencia_reativa"));
            this.fator_potencia_placa = Float.parseFloat(medicao.optString("fator_potencia"));
            this.rotacao_placa = Float.parseFloat(medicao.optString("rotacao"));
            this.vazao_placa = Float.parseFloat(medicao.optString("vazao"));
            this.altura_monometrica_placa = Float.parseFloat(medicao.optString("altura_monometrica"));
            this.fabricante_motor = medicao.optString("fabricante_motor");
            this.fabricante_bomba = medicao.optString("fabricante_bomba");

        // LOCAL
            this.cidade = local.optString("cidade");
            this.local = local.optString("local");

        // SISTEMA
            this.tipo_partida = local.optString("tipo_partida");
            this.sistema_supervisionado = local.optString("sistema_supervisionado");
            this.banco_capacitores = local.optString("banco_capacitores");
            this.observacoes = local.optString("observacoes");

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setTensao_placa(float tensao_placa) {
        this.tensao_placa = tensao_placa;
    }

    public void setTensao_medicao(float tensao_medicao) {
        this.tensao_medicao = tensao_medicao;
    }

    public void setCorrente_placa(float corrente_placa) {
        this.corrente_placa = corrente_placa;
    }

    public void setCorrente_medicao(float corrente_medicao) {
        this.corrente_medicao = corrente_medicao;
    }

    public void setPotencia_ativa_placa(float potencia_ativa_placa) {
        this.potencia_ativa_placa = potencia_ativa_placa;
    }

    public void setPotencia_ativa_medicao(float potencia_ativa_medicao) {
        this.potencia_ativa_medicao = potencia_ativa_medicao;
    }

    public void setPotencia_reativa_placa(float potencia_reativa_placa) {
        this.potencia_reativa_placa = potencia_reativa_placa;
    }

    public void setPotencia_reativa_medicao(float potencia_reativa_medicao) {
        this.potencia_reativa_medicao = potencia_reativa_medicao;
    }

    public void setFator_potencia_placa(float fator_potencia_placa) {
        this.fator_potencia_placa = fator_potencia_placa;
    }

    public void setFator_potencia_medicao(float fator_potencia_medicao) {
        this.fator_potencia_medicao = fator_potencia_medicao;
    }

    public void setRotacao_placa(float rotacao_placa) {
        this.rotacao_placa = rotacao_placa;
    }

    public void setRotacao_medicao(float rotacao_medicao) {
        this.rotacao_medicao = rotacao_medicao;
    }

    public void setVazao_placa(float vazao_placa) {
        this.vazao_placa = vazao_placa;
    }

    public void setVazao_medicao(float vazao_medicao) {
        this.vazao_medicao = vazao_medicao;
    }

    public void setAltura_monometrica_placa(float altura_monometrica_placa) {
        this.altura_monometrica_placa = altura_monometrica_placa;
    }

    public void setAltura_monometrica_medicao(float altura_monometrica_medicao) {
        this.altura_monometrica_medicao = altura_monometrica_medicao;
    }

    public void setFabricante_motor(String fabricante_motor) {
        this.fabricante_motor = fabricante_motor;
    }

    public void setFabricante_bomba(String fabricante_bomba) {
        this.fabricante_bomba = fabricante_bomba;
    }

    public float getTensao_placa() {
        return tensao_placa;
    }

    public float getTensao_medicao() {
        return tensao_medicao;
    }

    public float getCorrente_placa() {
        return corrente_placa;
    }

    public float getCorrente_medicao() {
        return corrente_medicao;
    }

    public float getPotencia_ativa_placa() {
        return potencia_ativa_placa;
    }

    public float getPotencia_ativa_medicao() {
        return potencia_ativa_medicao;
    }

    public float getPotencia_reativa_placa() {
        return potencia_reativa_placa;
    }

    public float getPotencia_reativa_medicao() {
        return potencia_reativa_medicao;
    }

    public float getFator_potencia_placa() {
        return fator_potencia_placa;
    }

    public float getFator_potencia_medicao() {
        return fator_potencia_medicao;
    }

    public float getRotacao_placa() {
        return rotacao_placa;
    }

    public float getRotacao_medicao() {
        return rotacao_medicao;
    }

    public float getVazao_placa() {
        return vazao_placa;
    }

    public float getVazao_medicao() {
        return vazao_medicao;
    }

    public float getAltura_monometrica_placa() {
        return altura_monometrica_placa;
    }

    public float getAltura_monometrica_medicao() {
        return altura_monometrica_medicao;
    }

    public String getFabricante_motor() {
        return fabricante_motor;
    }

    public String getFabricante_bomba() {
        return fabricante_bomba;
    }
}
