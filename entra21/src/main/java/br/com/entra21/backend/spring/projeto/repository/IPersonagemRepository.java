package br.com.entra21.backend.spring.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.entra21.backend.spring.projeto.model.Personagem;
import br.com.entra21.backend.spring.projeto.model.Usuario;

@Repository
@EnableJpaRepositories
public interface IPersonagemRepository extends JpaRepository<Personagem, Integer> {

	
	
	
	@Query("From Personagem Where habilidade = :habilidadeParam")
	Personagem habilidade(@Param("habilidadeParam") String habilidade);

	@Query("From Personagem Where nome_heroi = :nome_heroiParam")
	Personagem nome_heroi(@Param("nome_heroiParam") String nome_heroi);

	@Query("FROM Personagem Where idade >= :idadeParam ")
	List<Personagem> maiorIdade(@Param("idadeParam") Integer idade);

	@Query("From Personagem Where nome_real = :nome_realParam")
	Personagem nome_real(@Param("nome_realParam") String nome_heroi);
	
	@Query("FROM Personagem  Where nome_heroi = :nome_heroiParam and acessorio = :acessorioParam" )
	Personagem ficha(@Param("nome_heroiParam") String nome_heroi,@Param("acessorioParam") Boolean acessorio);
	
	@Query("FROM Personagem  Where nome_heroi = :nome_heroiParam and acessorio = :acessorioParam and habilidade = :habilidadeParam" )
	Personagem ficha_comp(@Param("nome_heroiParam") String nome_heroi,@Param("acessorioParam") Boolean acessorio,@Param("habilidadeParam") String habilidade);
}
