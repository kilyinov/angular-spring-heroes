package heroes.backend;

import jakarta.persistence.*;

@Entity
@Table(name = "MISSIONS")
public class MissionEntity {

  @Id
  @GeneratedValue(generator = "missionsSequence")
  @SequenceGenerator(name = "missionsSequence", sequenceName = "MISSIONS_SEQ")
  private Integer id;

  @Column(length = 100)
  private String name;

  @Column(length = 100)
  private String location;

  protected MissionEntity() {}

  public MissionEntity(String name, String location) {
    this.name = name;
    this.location = location;
  }

  public Integer getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return location;
  }

  public Mission toMission() {
    return new Mission(getId(), getName(), getLocation());
  }
}