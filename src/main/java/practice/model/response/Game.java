package practice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import practice.repository.entity.DeveloperTable;
import practice.repository.entity.PublisherTable;
import practice.repository.entity.UserTable;

@AllArgsConstructor
@Value
public class Game {

    private final Integer id;

    private final String gameName;

    private final String genre;

    private final String releasedate;

    private final DeveloperTable developerTable;

    private final UserTable userTable;

    private PublisherTable publisherTable;
}
