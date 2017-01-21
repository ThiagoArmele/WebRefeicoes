package webrefeicoes.dao;

import java.util.List;

import webrefeicoes.model.Funcionario;

public interface FuncionarioDAOInterface {
	
	public void save(Funcionario funcionario);
	public Funcionario  getFuncionario(long id);
	public List<Funcionario> list();
	public void remove(Funcionario funcionario);
	public void update(Funcionario funcionario);
	
	
}
	