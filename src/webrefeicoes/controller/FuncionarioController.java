package webrefeicoes.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import webrefeicoes.dao.FuncionarioDAO;
import webrefeicoes.dao.FuncionarioDAOInterface;
import webrefeicoes.model.Funcionario;

@ManagedBean
@SessionScoped
public class FuncionarioController {
	
	private Funcionario funcionario;
	private DataModel listaFuncionarios;
	
	public DataModel getListaFuncionarios() {
		List<Funcionario> lista = new FuncionarioDAO().list();
		listaFuncionarios = new ListDataModel(lista);
		System.out.println("estou no getLista");
		return listaFuncionarios;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void excluirFuncionario(){
		Funcionario funcionarioTemp = (Funcionario)(listaFuncionarios.getRowData());
		FuncionarioDAOInterface dao = new FuncionarioDAO();
		dao.remove(funcionarioTemp);
	}
	
	public void adicionarFuncionario(){
		FuncionarioDAOInterface dao = new FuncionarioDAO();
		dao.save(funcionario);
	}
	
	public void alterarFuncionario(){
		FuncionarioDAOInterface dao = new FuncionarioDAO();
		dao.update(funcionario);
	}
	
}
