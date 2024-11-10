public abstract class Conta implements IConta {
    
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected Banco banco;
    
    public Conta(Cliente cliente, Banco banco) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.banco = banco;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque deve ser positivo.");
            return;
        }
        if (saldo < valor) {
            System.out.println("Saldo insuficiente.");
            return;
        }
        saldo -= valor;
        System.out.println("Saque de " + valor + " realizado com sucesso.");
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito deve ser positivo.");
            return;
        }
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado com sucesso.");
    }

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (valor <= 0) {
			System.out.println("Valor de transferência deve ser positivo.");
			return;
		}
		if (saldo < valor) {
			System.out.println("Saldo insuficiente para transferência.");
			return;
		}
		this.sacar(valor);
		contaDestino.depositar(valor); 
		System.out.println("Transferência de " + valor + " realizada com sucesso para " + ((Conta)contaDestino).getCliente().getNome() + ".");
	}
	
    
    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Banco: %s", this.banco.getNome()));
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
        System.out.println("---------------------------");
    }
}
