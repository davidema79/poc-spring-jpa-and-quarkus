package eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.repos;

import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.entities.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributorsRepository extends JpaRepository<Contributor, Long> {

    List<Contributor> findAllByComponentId(final long componentId);

}
