package vertex.pro.edu.soung_box_app.controller.request_body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class SongRequest {
    private final String title;
    private final String album;
    private final String genre;
}
