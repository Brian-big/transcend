package brianbig.transcend.api.dto;

import brianbig.transcend.entities.Stream;

public record StreamDto(String id, String form, String name) {

    public static StreamDto from(Stream stream) {
        return new StreamDto(stream.getId(), stream.getName(), stream.getName());
    }
}
