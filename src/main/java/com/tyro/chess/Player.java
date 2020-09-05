package com.tyro.chess;

public class Player {
    private int no;
    private String color;
    private String type;
    private String startPosition;

    public Player(){

    }

    public Player(int no, String color, String type, String startPosition) {
        this.no = no;
        this.color = color;
        this.type = type;
        this.startPosition = startPosition;
    }

    public int getNo() {
        return no;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return (other.no == this.no && other.type.equals(this.type) && other.color.equals(this.color));
    }

    @Override
    public String toString() {
        return "Player{" +
                "no=" + no +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", startPosition='" + startPosition + '\'' +
                '}';
    }
}
