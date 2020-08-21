package eu.davidemartorana.cloud.gcp.quarkus.poc.jpa.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Contributor" )
@Table(name = "contributors" )
public class Contributor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "component_id", nullable = false)
  private long componentId;

}
