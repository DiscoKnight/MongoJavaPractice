package practice.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GameCriteria {

    private final String dev;
    private final String genre;
    private final String publisher;
}
