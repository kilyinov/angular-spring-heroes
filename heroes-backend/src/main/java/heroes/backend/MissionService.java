package heroes.backend;

import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MissionService implements InitializingBean {

  public static final List<String> MISSIONS = List.of(
    "Rescue the Princess",
    "Defeat the Dragon",
    "Retrieve the Treasure",
    "Escort the Caravan",
    "Defend the Castle"
  );

  private final MissionRepository missionRepository;

  public MissionService(MissionRepository missionRepository) {
    this.missionRepository = missionRepository;
  }

  @Override
  @Transactional
  public void afterPropertiesSet() {
    MISSIONS.stream()
      .filter(name -> missionRepository.findByName(name).isEmpty())
      .map(name -> new MissionEntity(name, "Location " + name))
      .forEach(missionRepository::save);
  }

  public Iterable<Mission> listMissions() {
    return missionRepository.findAllByOrderByName().stream().map(MissionEntity::toMission).toList();
  }

  public Mission getMission(Integer id) {
    return missionRepository.findById(id).map(MissionEntity::toMission).orElse(null);
  }

  @Transactional
  public Mission save(Mission mission) {
    if (mission.id() == null) {
      return missionRepository.save(new MissionEntity(mission.name(), mission.location())).toMission();
    } else {
      MissionEntity dbMission = missionRepository.getReferenceById(mission.id());
      dbMission.setName(mission.name());
      dbMission.setLocation(mission.location());
      return dbMission.toMission();
    }
  }

  @Transactional
  public Mission deleteMission(Integer id) {
    MissionEntity dbMission = missionRepository.findById(id).orElse(null);
    if (dbMission == null) {
      return null;
    }
    missionRepository.delete(dbMission);
    return dbMission.toMission();
  }
}