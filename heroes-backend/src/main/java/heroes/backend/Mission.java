package heroes.backend;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record Mission(Integer id, String name, String location) {}