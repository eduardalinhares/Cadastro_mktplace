package br.com.senai.core.service;

import br.com.senai.core.dao.DaoHorario;
import br.com.senai.core.dao.DaoRestaurante;
import br.com.senai.core.dao.FactoryDao;
import br.com.senai.core.domain.Horario;
import br.com.senai.core.domain.Restaurante;

public class HorarioService {

	private DaoHorario dao;

	private DaoRestaurante daoRestaurante;

	public HorarioService() {
		this.dao = FactoryDao.getInstance().getDaoHorario();
		this.daoRestaurante = FactoryDao.getInstance().getDaoRestaurante();

	}

	public void salvar(Horario horario) {
		this.validar(horario);
		boolean isJaInserido = horario.getId() > 0;

		if (isJaInserido) {
			this.dao.alterar(horario);
		} else {
			dao.inserir(horario);
		}

	}

	public void removerPor(int id) {

		if (id > 0) {
			this.dao.excluirPor(id);

		} else {
			throw new IllegalArgumentException("O id do restaurante deve ser maior que 0");
		}

	}

	private void validar(Horario horario) {
		if (horario != null) {

			if (horario.getDiaSemana() != null) {

				if (horario.getRestaurante() != null && horario.getRestaurante().getId() > 0) {

					boolean IsDiaSemanaInvalido = horario.getDiaSemana() == null;
					boolean isHoraAberturaInvalida = horario.getHoraAbertura() == null;
					boolean isHorarioFechamentoInvalido = horario.getHoraFechamento() == null;

					if (IsDiaSemanaInvalido) {
						throw new IllegalArgumentException("Informe um dia da Semana.");
					}

					if (isHoraAberturaInvalida) {
						throw new IllegalArgumentException("Informe um horário de abertura.");
					}

					if (isHorarioFechamentoInvalido) {
						throw new IllegalArgumentException("Informe um horário de fechamento.");
					}

				}

			}

		} else {
			throw new NullPointerException("O restaurante não pode ser nulo");
		}
	}

}
