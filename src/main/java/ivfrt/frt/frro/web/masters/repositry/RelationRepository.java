package ivfrt.frt.frro.web.masters.repositry;

import java.util.List;

import ivfrt.frt.frro.web.masters.model.Relation;

public interface RelationRepository {

	List<Relation> findAll();
	List<Relation> findRelationByCode(String relation_code);
}
