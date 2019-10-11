package pong.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

/*
   The menu for Pong

   *** Nothing to do here ***

 */
public class PongMenu extends MenuBar {

    private final Menu menuFile;
    private final Menu menuThemes;
    private final Menu menuOptions;

    public PongMenu(EventHandler<ActionEvent> menuHandler,
                    EventHandler<ActionEvent> themeHandler,
                    EventHandler<ActionEvent> optionsHandler) {

        menuFile = createMenuFile();
        menuThemes = createMenuThemes();
        menuOptions = createMenuOptions();

        // Connect event handlers to all menu items
        menuFile.getItems().forEach(item -> item.setOnAction(menuHandler));
        menuThemes.getItems().forEach(item -> item.setOnAction(themeHandler));
        menuOptions.getItems().forEach(item -> item.setOnAction(optionsHandler));

        this.getMenus().addAll(menuFile, menuThemes, menuOptions);
    }

    public void fixMenusNewGame() {
        getItemByText(menuFile, "New").setDisable(true);
        getItemByText(menuFile, "Stop").setDisable(false);
        getItemByText(menuThemes, "Cool").setDisable(true);
        getItemByText(menuThemes, "Duckie").setDisable(true);
        getItemByText(menuThemes, "Swag").setDisable(true);
    }

    public void fixMenusKillGame() {
        getItemByText(menuFile, "New").setDisable(false);
        getItemByText(menuFile, "Stop").setDisable(true);
        getItemByText(menuThemes, "Cool").setDisable(false);
        getItemByText(menuThemes, "Duckie").setDisable(false);
        getItemByText(menuThemes, "Swag").setDisable(false);
    }

    // ------------  Helpers -----------------------------

    private MenuItem getItemByText(Menu menu, String text) {
        for (MenuItem i : menu.getItems()) {
            if (i.getText().equals(text)) {
                return i;
            }
        }
        throw new IllegalArgumentException("No such menu item " + text);
    }

    private Menu createMenuFile() {
        Menu menuFile = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem stopItem = new MenuItem("Stop");
        MenuItem exitItem = new MenuItem("Exit");
        menuFile.getItems().addAll(newItem, stopItem, exitItem);
        return menuFile;
    }

    private Menu createMenuThemes() {
        Menu menuThemes = new Menu("Themes");
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioMenuItem cool = new RadioMenuItem("Cool");
        RadioMenuItem duckie = new RadioMenuItem("Duckie");
        RadioMenuItem swag = new RadioMenuItem("Swag");

        swag.setSelected(true);


        cool.setToggleGroup(toggleGroup);
        duckie.setToggleGroup(toggleGroup);
        swag.setToggleGroup(toggleGroup);

        menuThemes.getItems().addAll(swag, cool, duckie);
        return menuThemes;
    }

    private Menu createMenuOptions() {
        Menu menuThemes = new Menu("Options");
        CheckMenuItem ai = new CheckMenuItem("Computer player");
        ai.setSelected(false);
        menuThemes.getItems().addAll(ai);
        return menuThemes;
    }

}
