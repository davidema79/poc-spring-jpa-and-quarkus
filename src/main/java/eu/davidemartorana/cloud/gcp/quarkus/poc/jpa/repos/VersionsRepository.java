package eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.repos;

import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionsRepository extends JpaRepository<Version, Long> {

    List<Version> findAllByComponentId(final long componentId);

}
