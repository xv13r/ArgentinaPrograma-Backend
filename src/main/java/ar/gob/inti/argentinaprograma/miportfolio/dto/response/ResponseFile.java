package ar.gob.inti.argentinaprograma.miportfolio.dto.response;

import java.util.UUID;

public class ResponseFile {
    private UUID id;
    private String name;
    private String type;
    private byte[] data;

    public ResponseFile(UUID id, String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public UUID getId() {
        return this.id;
    }

    public void setName(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}