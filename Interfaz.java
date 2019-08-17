package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Interfaz extends Application {
    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lector CSV");
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        final FileChooser search = new FileChooser();
        Button btnCSV = new Button("Cargar Archivo");
        TilePane root = new TilePane();
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                File file = search.showOpenDialog(primaryStage);
                if (file != null) {
                    populateTable(table, file);

                }
            }
        };
        btnCSV.setOnAction(event);
        root.getChildren().

                add(btnCSV);
        root.getChildren();

        Scene sc = new Scene(root, 1280, 720);
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    private void populateTable(
            final TableView<ObservableList<StringProperty>> table,
            final File file) {
        table.getItems().clear();
        table.getColumns().clear();
        table.setPlaceholder(new Label("Loading..."));
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                BufferedReader br;
                // Data:
                try {
                    br = new BufferedReader(new FileReader(file));
                    if (br.readLine() != null) {
                        String Line;
                        while ((Line = br.readLine()) != null) {
                            final String[] dataValues = Line.split(" ;");
                            System.out.println(br.readLine());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    // Add additional columns if necessary:
                                    for (int columnIndex = table.getColumns().size(); columnIndex < dataValues.length; columnIndex++) {
                                        table.getColumns().add(createColumn(columnIndex, ""));
                                    }
                                    // Add data to table:
                                    ObservableList<StringProperty> data = FXCollections
                                            .observableArrayList();
                                    for (String value : dataValues) {
                                        data.add(new SimpleStringProperty(value));
                                    }
                                    table.getItems().add(data);
                                }
                            });
                        }
                    }
                    ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            private TableColumn<ObservableList<StringProperty>, String> createColumn(
                    final int columnIndex, String columnTitle) {
                TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
                String title;
                if (columnTitle == null || columnTitle.trim().length() == 0) {
                    title = "Column " + (columnIndex + 1);
                } else {
                    title = columnTitle;
                }
                column.setText(title);
                column
                        .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(
                                    TableColumn.CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
                                ObservableList<StringProperty> values = cellDataFeatures.getValue();
                                if (columnIndex >= values.size()) {
                                    return new SimpleStringProperty("");
                                } else {
                                    return cellDataFeatures.getValue().get(columnIndex);
                                }
                            }
                        });
                return column;
            }

            public void main(String args[]) {
                // launch the application
                launch(args);

            }

            private void openFile(File file) {
                try {
                    desktop.open(file);

                } catch (IOException ex) {
                    Logger.getLogger(
                            Interfaz.class.getName()).log(
                            Level.SEVERE, null, ex
                    );
                }
            }
        };
    }
}