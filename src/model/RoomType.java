package model;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    private final String typeId;

    RoomType(String typeId) {
        this.typeId = typeId;
    }

    public static RoomType valueOfLabel(String typeId) {
        for (RoomType roomType : values()) {
            if (roomType.typeId.equals(typeId)) {
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}
