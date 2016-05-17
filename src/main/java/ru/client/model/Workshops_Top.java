package ru.client.model;

public class Workshops_Top {
    private int workshopsId;
    private int typesId;

    public Workshops_Top() {
    }

    public Workshops_Top(int workshopsId, int typesId) {
        this.workshopsId = workshopsId;
        this.typesId = typesId;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Workshops_Top workshops_top = new Workshops_Top(this.workshopsId, this.typesId);
        return workshops_top;
    }

    @Override
    public String toString() {
        return "WORKSHOPS_TYPES{" +
                "workshopsId=" + workshopsId +
                ", typesId=" + typesId +
                '}';
    }

    public int getWorkshopsId() {
        return workshopsId;
    }

    public void setWorkshopsId(int workshopsId) {
        this.workshopsId = workshopsId;
    }

    public int getTypesId() {
        return typesId;
    }

    public void setTypesId(int typesId) {
        this.typesId = typesId;
    }
}
