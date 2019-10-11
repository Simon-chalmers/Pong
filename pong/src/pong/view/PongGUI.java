package pong.view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pong.event.IEventHandler;
import pong.model.*;
import pong.event.ModelEvent;
import pong.event.EventBus;
import pong.view.theme.Cool;
import pong.view.theme.Duckie;
import pong.view.theme.Swag;

import java.util.Random;

import static java.lang.System.out;
import static pong.model.Paddle.PADDLE_SPEED;
import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;

/*
 * The GUI for the Pong game (except the menu).
 * No application logic here just GUI and event handling
 *
 * Run this to run the game
 *
 * See: https://en.wikipedia.org/wiki/Pong
 *
 */
public class PongGUI extends Application implements IEventHandler {

    private Pong pong;                  // The OO model (the data and logic for the game)
    private boolean running = false;    // Is game running?

    private static Random ran = new Random();

    // ------- Keyboard handling ----------------------------------

    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
                pong.setSpeedRightPaddle(-1);
                break;
            case DOWN:
                pong.setSpeedRightPaddle(1);
                break;
            case Q:
                pong.setSpeedLeftPaddle(-1);
                break;
            case A:
                pong.setSpeedLeftPaddle(1);
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
            case DOWN:
                pong.setSpeedRightPaddle(0);
                break;
            case A:
            case Q:
                pong.setSpeedLeftPaddle(0);
                break;
            default: // Nothing
        }
    }

    // ---- Menu handling (except themes) -----------------

    private void handleMenu(ActionEvent e) {
        String s = ((MenuItem) e.getSource()).getText();
        switch (s) {
            case "New":
                newGame();
                break;
            case "Stop":
                killGame();
                break;
            case "Exit":
                System.exit(0);
            default:
                throw new IllegalArgumentException("No such menu choice " + s);
        }
    }

    // ---------- Menu actions ---------------------

    private void newGame() {
        // GUI handling
        menu.fixMenusNewGame();
        renderBackground();

        // Build the model
        Paddle rightPaddle = null;
        Paddle leftPaddle = null;

        Ceiling ceiling = null;
        Floor floor = null;

        // TODO Create objects and connect to a full object model
        rightPaddle = new Paddle(GAME_WIDTH - 50, GAME_HEIGHT / 2 - Paddle.PADDLE_HEIGHT / 2);
        leftPaddle = new Paddle(50, GAME_HEIGHT / 2 - Paddle.PADDLE_HEIGHT / 2);

        ceiling = new Ceiling(0);
        floor = new Floor(GAME_HEIGHT);

        Ball ball = new Ball(GAME_WIDTH/2, GAME_HEIGHT/2, ran.nextDouble() * 10 - 5, ran.nextDouble() * 10 - 5, 1);

        pong = new Pong(ball, leftPaddle, rightPaddle, ceiling, floor);


        // Map objects to sprites
        assets.bind(rightPaddle, assets.rightPaddle);
        assets.bind(leftPaddle, assets.leftPaddle);

        // Start game
        timer.start();
        running = true;
    }

    private void killGame() {
        timer.stop();
        menu.fixMenusKillGame();
        renderSplash();
        running = false;
    }

    // -------- Event handling (events sent from model to GUI) ------------


    @Override
    public void onModelEvent(ModelEvent evt) {
        if (evt.type == ModelEvent.Type.NEW_BALL) {
            // TODO Optional
        } else if (evt.type == ModelEvent.Type.BALL_HIT_PADDLE) {
            assets.ballHitPaddle.play();
        } else if (evt.type == ModelEvent.Type.BALL_HIT_WALL_CEILING) {
           // TODO Optional
        }
    }

    // ------- Optional ------------
    private void handleOptions(ActionEvent e){
        CheckMenuItem i = (CheckMenuItem) e.getSource();
        if( i.isSelected()){
            // TODO Optional if using AI
            out.println("AI on");
        }else {
            out.println("AI off");
        }
    }

    // ################## Nothing to do below ############################

    // ---------- Theme handling ------------------------------

    private Assets assets;

    private void handleTheme(ActionEvent e) {
        String s = ((MenuItem) e.getSource()).getText();
        Assets lastTheme = assets;
        try {
            switch (s) {
                case "Cool":
                    assets = new Cool();
                    break;
                case "Duckie":
                    assets = new Duckie();
                    break;
                case "Swag":
                    assets = new Swag();
                    break;
                default:
                    throw new IllegalArgumentException("No such assets " + s);
            }
        } catch (NullPointerException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Theme");
            alert.setHeaderText("Data for assets " + s + " is missing or corrupt!");
            alert.setContentText("Old Theme will be used");
            alert.showAndWait();
            assets = lastTheme;
        }
    }

    // ---------- Rendering -----------------

    // For debugging, see render()
    private boolean renderDebug = false; //true;

    private void render() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);    // Clear everything
        fg.setFill(assets.colorFgText);
        fg.setFont(Font.font(18));
        fg.fillText("Points: " + pong.getPointsLeft(), 10, 20);
        fg.fillText("Points: " + pong.getPointsRight(), 500, 20);
        for (IPositionable d : pong.getPositionables()) {
            if (renderDebug) {
                fg.strokeRect(d.getX(), d.getY(), d.getWidth(), d.getHeight());
            } else {
                fg.drawImage(assets.get(d), d.getX(), d.getY(), d.getWidth(), d.getHeight());
            }
        }
    }

    private void renderBackground() {
        if (!renderDebug) {
            bg.drawImage(assets.getBackground(), 0, 0, GAME_WIDTH, GAME_HEIGHT);
        }
    }

    private void renderSplash() {
        fg.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        bg.drawImage(assets.splash, 0, 0, GAME_WIDTH, GAME_HEIGHT);
    }

    // -------------- Build Scene and start graphics ---------------

    private AnimationTimer timer;
    private GraphicsContext fg;
    private GraphicsContext bg;
    private PongMenu menu = new PongMenu(this::handleMenu, this::handleTheme, this::handleOptions);

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        root.setTop(menu);

        // Drawing areas
        Canvas background = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        bg = background.getGraphicsContext2D();
        Canvas foreground = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        fg = foreground.getGraphicsContext2D();

        Pane pane = new Pane(background, foreground);
        root.setCenter(pane);

        timer = new AnimationTimer() {
            public void handle(long now) {
                pong.update(now);
                render();
            }
        };

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::keyPressed);
        scene.setOnKeyReleased(this::keyReleased);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong");

        // Set assets, splash (order matters) and initial menu state
        assets = new Swag();
        menu.fixMenusKillGame();
        bg.drawImage(assets.splash, 0, 0, GAME_WIDTH, GAME_HEIGHT);

        EventBus.INSTANCE.register(this);

        // Show on screen
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
