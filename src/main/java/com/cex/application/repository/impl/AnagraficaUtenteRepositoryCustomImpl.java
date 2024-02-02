package com.cex.application.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cex.application.entity.AnagraficaUtente;
import com.cex.application.repository.AnagraficaUtenteRepositoryCustom;
import com.cex.application.repository.common.EntityDao;

@Repository
public class AnagraficaUtenteRepositoryCustomImpl extends EntityDao<AnagraficaUtente> implements AnagraficaUtenteRepositoryCustom
{
//	@PersistenceContext
//	EntityManager em;
	
//	public List<Anagrafica> findByCriteria(Anagrafica entity) 
//	{
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Anagrafica> cq = cb.createQuery(Anagrafica.class);
//		Root<Anagrafica> anag = cq.from(Anagrafica.class);
//		
//		ArrayList<Predicate> filtro = new ArrayList<>();
//		
//		if(entity.getNome()!=null) {
//			Predicate nome = cb.equal(anag.get("nome"), entity.getNome());
//			filtro.add(nome);
//		}
//		
//		cq.where(filtro.toArray(new Predicate[0]));
//		TypedQuery<Anagrafica> query = em.createQuery(cq);
//		
//		return query.getResultList();
//	}
	
	public List<AnagraficaUtente> findByCriteria(AnagraficaUtente entity) 
	{
		return super.findByCriteria(entity);
	}
}
