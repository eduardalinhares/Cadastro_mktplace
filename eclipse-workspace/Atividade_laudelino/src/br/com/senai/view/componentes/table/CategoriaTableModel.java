package br.com.senai.view.componentes.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.core.domain.Categoria;

public class CategoriaTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

private List<Categoria> categorias;
	
	public CategoriaTableModel(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getPor(int rowIndex) {
		return categorias.get(rowIndex);
	}
	
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Código";
		} else if (columnIndex == 1) {
			return "Nome";
		}
		throw new IllegalArgumentException("Índice inválido.");
		
	}
	
	@Override
	public int getRowCount() {
		return categorias.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Categoria categoriaDaLinha = categorias.get(rowIndex);
		if (columnIndex == 0) {
			return categoriaDaLinha.getId();
		} else if (columnIndex == 1) {
			return categoriaDaLinha.getNome();
		}
		throw new IllegalArgumentException("Índice inválido.");
	}

	public void removerPor(int rowIndex) {
		this.categorias.remove(rowIndex);
	}
	
	public boolean isVazio() {
		return categorias.isEmpty();
	}

}
