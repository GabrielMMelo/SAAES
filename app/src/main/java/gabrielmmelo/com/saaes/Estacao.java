package gabrielmmelo.com.saaes;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
    private float altura_monometrica_placa;
    private String fabricante_motor;
    private String fabricante_bomba;
    private String cidade;
    private String local;
    private String tipo_partida;
    private String banco_capacitores;
    private String sistema_supervisionado;



    private String numero_instalacao;
    private String endereco;
    private String observacoes;

    private Context context;
    private EstacaoDB estacaoDB;

    public Estacao(Context context) {
        this.context = context;
        estacaoDB = new EstacaoDB(context);
    }

    public Estacao(float tensao_placa, float tensao_medicao, float corrente_placa, float corrente_medicao, float potencia_ativa_placa, float potencia_ativa_medicao, float potencia_reativa_placa, float potencia_reativa_medicao, float fator_potencia_placa, float fator_potencia_medicao, float rotacao_placa, float rotacao_medicao, float vazao_placa, float altura_monometrica_placa, String fabricante_motor, String fabricante_bomba, String cidade, String local, String tipo_partida, String banco_capacitores, String sistema_supervisionado,String numero_instalacao, String endereco, String observacoes) {
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
        this.altura_monometrica_placa = altura_monometrica_placa;
        this.fabricante_motor = fabricante_motor;
        this.fabricante_bomba = fabricante_bomba;
        this.cidade = cidade;
        this.local = local;
        this.tipo_partida = tipo_partida;
        this.banco_capacitores = banco_capacitores;
        this.sistema_supervisionado = sistema_supervisionado;
        this.observacoes = observacoes;
        this.numero_instalacao = numero_instalacao;
        this.endereco = endereco;
    }

    public void estacaoFromJson(String _local, String _placa, String _medicao, String _sistema) {

        try {
            JSONObject local = new JSONObject(_local);
            JSONObject placa = new JSONObject(_placa);
            JSONObject medicao = new JSONObject(_medicao);
            JSONObject sistema = new JSONObject(_sistema);


        // PLACA
            this.setTensao_placa(Float.parseFloat(placa.optString("tensao")));
            this.setCorrente_placa(Float.parseFloat(placa.optString("corrente")));
            this.setPotencia_ativa_placa(Float.parseFloat(placa.optString("potencia_ativa")));
            this.setPotencia_reativa_placa(Float.parseFloat(placa.optString("potencia_reativa")));
            this.setFator_potencia_placa(Float.parseFloat(placa.optString("fator_potencia")));
            this.setRotacao_placa(Float.parseFloat(placa.optString("rotacao")));
            this.setVazao_placa(Float.parseFloat(placa.optString("vazao")));
            this.setAltura_monometrica_placa(Float.parseFloat(placa.optString("altura_monometrica")));
            this.setFabricante_motor(placa.optString("fabricante_motor"));
            this.setFabricante_bomba(placa.optString("fabricante_bomba"));


        // MEDICAO
            this.setTensao_medicao(Float.parseFloat(medicao.optString("tensao")));
            this.setCorrente_medicao(Float.parseFloat(medicao.optString("corrente")));
            this.setPotencia_ativa_medicao(Float.parseFloat(medicao.optString("potencia_ativa")));
            this.setPotencia_reativa_medicao(Float.parseFloat(medicao.optString("potencia_reativa")));
            this.setFator_potencia_medicao(Float.parseFloat(medicao.optString("fator_potencia")));
            this.setRotacao_medicao(Float.parseFloat(medicao.optString("rotacao")));

        // LOCAL
            this.setCidade(local.optString("cidade"));
            this.setLocal(local.optString("local"));
            this.setEndereco(local.optString("endereco"));
            this.setNumero_instalacao(local.optString("numero_instalacao"));

        // SISTEMA
            this.setTipo_partida(sistema.optString("tipo_partida"));
            this.setSistema_supervisionado(sistema.optString("sistema_supervisionado"));
            this.setBanco_capacitores(sistema.optString("banco_capacitores"));
            this.setObservacoes(sistema.optString("observacoes"));

            Log.i("DEBUG", getFabricante_motor() + " Fabricante motor");
            Log.i("DEBUG", getFabricante_bomba() + " Fabricante bomba");

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     *  Get list with all estacao
     * @return
     */
    public List<Estacao> getAll(){
        return estacaoDB.getAll();
    }


    /**
     * Find estacao by id
     * @param id
     */
    public void getEstacao(int id){
        Estacao estacao;
        estacao = estacaoDB.getEstacao(id);

        Log.i("sql", estacao.getCidade() + " é a cidade.");
    }

    /**
     * Find estacao by city name
     * @param cidade
     */
    public void getEstacao(String cidade){
        Estacao estacao;
        estacao = estacaoDB.getEstacao(cidade);
        Log.i("sql", estacao.getCidade() + " é a cidade.");
    }

    /**
     * Get estacao table count
     */
    public void totalEstacao(){
        Log.i("sql",estacaoDB.getCount("estacao")+" REGISTROS NO BANCO");
    }


    /**
     * Insert an entire estacao into DB
     */
    public void prepararEscrita(){
        Log.i("ENDEREÇO", estacaoDB.save(this) + "");
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


    public void setAltura_monometrica_placa(float altura_monometrica_placa) {
        this.altura_monometrica_placa = altura_monometrica_placa;
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

    public float getAltura_monometrica_placa() {
        return altura_monometrica_placa;
    }

    public String getFabricante_motor() {
        return fabricante_motor;
    }

    public String getFabricante_bomba() {
        return fabricante_bomba;
    }

    public String getTipo_partida() {
        return tipo_partida;
    }

    public void setTipo_partida(String tipo_partida) {
        this.tipo_partida = tipo_partida;
    }

    public String getBanco_capacitores() {
        return banco_capacitores;
    }

    public void setBanco_capacitores(String banco_capacitores) {
        this.banco_capacitores = banco_capacitores;
    }

    public String getSistema_supervisionado() {
        return sistema_supervisionado;
    }

    public void setSistema_supervisionado(String sistema_supervisionado) {
        this.sistema_supervisionado = sistema_supervisionado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public String getNumero_instalacao() {
        return numero_instalacao;
    }

    public void setNumero_instalacao(String numero_instalacao) {
        this.numero_instalacao = numero_instalacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
