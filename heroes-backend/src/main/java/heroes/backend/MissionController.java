package heroes.backend;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

  private final MissionService missionService;

  public MissionController(MissionService missionService) {
    this.missionService = missionService;
  }

  @GetMapping
  public Iterable<Mission> listMissions() {
    return missionService.listMissions();
  }

  @GetMapping("/{id}")
  public Mission get(@PathVariable("id") Integer id) {
    return missionService.getMission(id);
  }

  @PostMapping
  public Mission create(@RequestBody Mission mission) {
    return missionService.save(mission);
  }

  @PutMapping("/{id}")
  public Mission change(@PathVariable("id") Integer id, @RequestBody Mission mission) {
    return missionService.save(mission);
  }

  @DeleteMapping("/{id}")
  public Mission delete(@PathVariable("id") Integer id) {
    return missionService.deleteMission(id);
  }
}