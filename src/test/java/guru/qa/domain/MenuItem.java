package guru.qa.domain;

public enum MenuItem {
    AUTO("На автомобиле"),
    PT("На общественном транспорте"),
    FOOT("Пешком"),
    BICYCLE("На велосипеде"),
    PLANE("На самолете");

    public final String rusName;

    MenuItem(String rusName) {
        this.rusName = rusName;
    }
}
