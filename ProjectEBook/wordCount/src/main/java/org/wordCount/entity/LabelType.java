package org.wordCount.entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
@Node({"Genres", "Themes"})
public class LabelType {
    @Id @GeneratedValue private Long identity;
    @Property("name")
    private String label;
    private LabelType() {
        // Empty constructor required as of Neo4j API 2.0.5
    };
    @Relationship(type = "Related")
    public Set<LabelType> related;
    @Relationship(type = "IS_SUBTYPE_TO")
    public Set<LabelType> subType;
    @Relationship(type = "IS_FATHER_TO")
    public Set<LabelType> fatherType;
    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}
