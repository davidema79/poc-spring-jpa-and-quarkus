package eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.repos;

import eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentsRepository extends JpaRepository<Component, Long> {
}
