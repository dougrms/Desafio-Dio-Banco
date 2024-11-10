
import java.util.ArrayList;
import java.util.List;

public class Banco {

	private String nome;
	private List<Conta> contas = new ArrayList<>();
	
	public List<Conta> getContas() {
		return contas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionarConta(Conta conta){
		this.contas.add(conta);
	}

        @Override
    public String toString() {
        return nome;
	}

}

