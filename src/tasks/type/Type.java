package tasks.type;

public enum Type {
    WORK("рабочая"),
    PERSONAL("личная");
private final String typeOf;

    Type( String typeOf ) {
        this.typeOf = typeOf;
    }

    public String getTypeOf() {
        return typeOf;
    }

}
