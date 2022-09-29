package br.com.entra21.backend.spring.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.entra21.backend.spring.projeto.model.Usuario;

@Repository
@EnableJpaRepositories
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("FROM Usuario Where idade >= :idadeParam ")

	List<Usuario> maiorIdade(@Param("idadeParam") Integer idade);

	@Query("FROM Usuario  Where email = :emailParam and senha = :senhaParam" )
	Usuario login(@Param("emailParam") String email,@Param("senhaParam") String senha);
}
