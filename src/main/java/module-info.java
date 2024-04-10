module java1.tur0183 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires com.h2database;
    requires org.apache.logging.log4j;
    requires org.hibernate.orm.core;
    requires static jakarta.annotation;

    opens java1_2023_tur0183 to javafx.fxml;
    exports java1_2023_tur0183;
    exports java1_2023_tur0183.entities;
    opens java1_2023_tur0183.entities to javafx.fxml, org.hibernate.orm.core;
}
