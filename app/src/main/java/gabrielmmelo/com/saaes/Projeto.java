package gabrielmmelo.com.saaes;

import android.content.Context;

import java.util.List;

public class Projeto {
    private String nome;
    private Context context;
    private ProjetoDB projetoDB;

    public Projeto(Context context, String nome){
        this.nome = nome;
        this.context = context;
        this.projetoDB = new ProjetoDB(context);
    }

    public Projeto(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

     /**
     *  Get list with all estacao
     * @return
     */
    public List<Projeto> getAll(){
        return projetoDB.getAll();
    }
}
