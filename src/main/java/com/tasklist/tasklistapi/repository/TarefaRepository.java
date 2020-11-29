package com.tasklist.tasklistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasklist.tasklistapi.model.Tarefa;

/** 
 * REPOSITÓRIO DA ENTIDADE TAREFA 
 *
 * @author Wenner
 */

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa , Long>{

}
