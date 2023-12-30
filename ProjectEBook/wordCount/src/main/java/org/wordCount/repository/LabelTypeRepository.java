package org.wordCount.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.wordCount.entity.LabelType;

public interface LabelTypeRepository extends Neo4jRepository<LabelType, Long> {

    @Query("MATCH (t:Genres) WHERE t.name=$labelName\n" +
            "MATCH (t)-[:Related|IS_FATHER_OF]->(neighbor) RETURN neighbor")
    List<LabelType> findNeibour(@Param("labelName") String label);
}
