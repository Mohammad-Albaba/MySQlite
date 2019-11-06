package com.example.mysqlite.DB;

public class Note {
    private int id;
    private String note ;
    private String timestamp;

    public static final String TabeName = "Notes";
    public static final String Col_Id = "Col_Id";
    public static final String Col_Note = "Col_Note";
    public static final String Col_Timestamp = "Col_Timestamp";

    public static final String CreateTable = " CREATE TABLE " + TabeName +
            "("+Col_Id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Col_Note+" TEXT,"+
            Col_Timestamp+" TEXT"+")";

    public Note() {
    }

    public Note(int id, String note, String timestamp) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
